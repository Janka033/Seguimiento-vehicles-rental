package co.edu.cue.proyectoc2.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import co.edu.cue.proyectoc2.mapping.ProductMapper;
import co.edu.cue.proyectoc2.mapping.dto.ProductDto;
import co.edu.cue.proyectoc2.models.Category;
import co.edu.cue.proyectoc2.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del repositorio de productos.
 */
@ApplicationScoped
public class ProductRepositoryJdbcImpl implements Repository<ProductDto> {

    @Inject
    @Named("conn")
    private Connection conn;

    /**
     * Obtiene una lista de todos los productos.
     *
     * @return una lista de objetos ProductDto representando los productos
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public List<ProductDto> listar() throws SQLException {
        List<ProductDto> products = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.name as category FROM productjdbc as p " +
                     " INNER JOIN category as c ON (p.id_category=c.id) ORDER BY p.id ASC")) {
            while (rs.next()) {
                Product p = getProduct(rs);
                ProductDto productDto = ProductMapper.mapFrom(p);
                products.add(productDto);
            }
        }
        return products;
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id el ID del producto a buscar
     * @return un objeto ProductDto representando el producto encontrado, o null si no se encuentra
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public ProductDto porId(Long id) throws SQLException {
        Product product = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.name as category FROM productjdbc as p " +
                " INNER JOIN category as c ON (p.id_category=c.id) WHERE p.id = ? ")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = getProduct(rs);
                }
            }
        }
        return ProductMapper.mapFrom(product);
    }

    /**
     * Guarda un producto en la base de datos.
     *
     * @param productDto el objeto ProductDto que representa el producto a guardar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public void save(ProductDto productDto) throws SQLException {
        String sql;
        if (productDto.id() != null && productDto.id() > 0) {
            sql = "UPDATE productojdbc set tipo=?, marca=?, modelo=?, anio=?,id_categoria=?,precio=?, disponible=? where id=?";
        } else {
            sql = "INSERT INTO productojdbc (tipo, marca, modelo, anio,id_categoria,precio, disponible) VALUES(?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productDto.type());
            stmt.setString(2, productDto.brand());
            stmt.setString(3, productDto.model());
            stmt.setString(4, productDto.year());
            stmt.setLong(5, productDto.category().getId());
            stmt.setInt(6, productDto.price());
            stmt.setString(7, productDto.available());

            if (productDto.id() != null && productDto.id() > 0) {
                stmt.setLong(8, productDto.id());
            }
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     *
     * @param id el ID del producto a eliminar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public void delet(Long id) throws SQLException {
        String sql = "DELETE FROM productjdbc WHERE id =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Product getProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getLong("id"));
        p.setType(rs.getString("type"));
        p.setBrand(rs.getString("brand"));
        p.setModel(rs.getString("model"));
        p.setYear(rs.getString("year"));
        p.setPrice(rs.getInt("price"));
        p.setAvailable(rs.getString("available"));
        Category category = new Category();
        category.setId(rs.getLong("id_category"));
        category.setName(rs.getString("category"));
        p.setCategory(category);
        return p;
    }
}
