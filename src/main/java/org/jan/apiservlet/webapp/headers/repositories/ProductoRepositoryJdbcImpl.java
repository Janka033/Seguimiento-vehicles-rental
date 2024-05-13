package org.jan.apiservlet.webapp.headers.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jan.apiservlet.webapp.headers.mapping.ProductoMapper;
import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.models.Categoria;
import org.jan.apiservlet.webapp.headers.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductoRepositoryJdbcImpl implements Repository<ProductoDto> {
   @Inject
   @Named("conn")
    private Connection conn;
    @Override
    public List<ProductoDto> listar() throws SQLException {
        List<ProductoDto> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productojdbc as p " +
                     " INNER JOIN categoria as c ON (p.id_categoria=c.id) ORDER BY p.id ASC")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                ProductoDto productoDto = ProductoMapper.mapFrom(p);
                productos.add(productoDto);
            }
        }
        return productos;
    }

    @Override
    public ProductoDto porId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productojdbc as p " +
                " INNER JOIN categoria as c ON (p.id_categoria=c.id) WHERE p.id = ? ")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }


        }
        return ProductoMapper.mapFrom(producto);
    }

    @Override
    public void guardar(ProductoDto productoDto) throws SQLException {
        String sql;
        if (productoDto.id() != null && productoDto.id() > 0) {
            sql = "UPDATE productojdbc set tipo=?, marca=?, modelo=?, anio=?,id_categoria=?,precio=?, disponible=? where id=?";
        } else {
            sql = "INSERT INTO productojdbc (tipo, marca, modelo, anio,id_categoria,precio, disponible) VALUES(?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productoDto.tipo());
            stmt.setString(2, productoDto.marca());
            stmt.setString(3, productoDto.modelo());
            stmt.setString(4, productoDto.anio());
            stmt.setLong(5, productoDto.categoria().getId());
            stmt.setInt(6,productoDto.precio());
            stmt.setString(7, productoDto.disponible());


            if (productoDto.id() != null && productoDto.id() > 0) {
                stmt.setLong(8, productoDto.id());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
            String sql = "DELETE FROM productojdbc WHERE id =?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1,id);
                stmt.executeUpdate();
            }
        }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setTipo(rs.getString("tipo"));
        p.setMarca(rs.getString("marca"));
        p.setModelo(rs.getString("modelo"));
        p.setAnio(rs.getString("anio"));
        p.setPrecio(rs.getInt("precio"));
        p.setDisponible(rs.getString("disponible"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("id_categoria"));
        categoria.setNombre(rs.getString("categoria"));
        p.setCategoria(categoria);
        return p;
    }
}
