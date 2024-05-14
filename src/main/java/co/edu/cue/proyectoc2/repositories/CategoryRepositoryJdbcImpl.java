package co.edu.cue.proyectoc2.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import co.edu.cue.proyectoc2.mapping.CategoryMapper;
import co.edu.cue.proyectoc2.mapping.dto.CategoryDto;
import co.edu.cue.proyectoc2.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC del repositorio de categorías.
 */
@ApplicationScoped
public class CategoryRepositoryJdbcImpl implements Repository<CategoryDto> {

    private Connection conn;

    @Inject
    public CategoryRepositoryJdbcImpl(@Named("conn") Connection conn) {
        this.conn = conn;
    }

    /**
     * Obtiene una lista de todas las categorías.
     *
     * @return una lista de objetos CategoryDto representando las categorías
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public List<CategoryDto> listar() throws SQLException {
        List<CategoryDto> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM category")) {
            while (rs.next()) {
                Category category = getCategoria(rs);
                CategoryDto categoryDto = CategoryMapper.mapFrom(category);
                categorias.add(categoryDto);
            }
        }
        return categorias;
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id el ID de la categoría a buscar
     * @return un objeto CategoryDto representando la categoría encontrada, o null si no se encuentra
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public CategoryDto porId(Long id) throws SQLException {
        Category category = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category as c WHERE c.id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    category = getCategoria(rs);
                }
            }
            return CategoryMapper.mapFrom(category);
        }
    }

    /**
     * Guarda una categoría en la base de datos.
     *
     * @param categoryDto el objeto CategoryDto que representa la categoría a guardar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public void save(CategoryDto categoryDto) throws SQLException {
        String sql;
        if (categoryDto.id() != null && categoryDto.id() > 0) {
            sql = "UPDATE category set name=? where id=?";
        } else {
            sql = "INSERT INTO category (name) VALUES(?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoryDto.name());

            if (categoryDto.id() != null && categoryDto.id() > 0) {
                stmt.setLong(2, categoryDto.id());
            }
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina una categoría de la base de datos por su ID.
     *
     * @param id el ID de la categoría a eliminar
     * @throws SQLException si ocurre un error de SQL
     */
    @Override
    public void delet(Long id) throws SQLException {
        String sql = "DELETE FROM category WHERE id =?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Category getCategoria(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        return category;
    }
}
