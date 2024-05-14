package co.edu.cue.proyectoc2.services;

import co.edu.cue.proyectoc2.mapping.dto.UserDto;
import co.edu.cue.proyectoc2.models.User;
import co.edu.cue.proyectoc2.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import co.edu.cue.proyectoc2.mapping.dto.CategoryDto;
import co.edu.cue.proyectoc2.mapping.dto.ProductDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class ProductServiceJdbcImpl implements ProductService {
    @Inject
    private Repository<ProductDto> repositoryJdbc;
    @Inject
    private Repository<CategoryDto> repositoryJdbcCategpria;
    @Inject
    private Repository<UserDto> repositoryJdbcUser;

    @Override
    public List<ProductDto> listarProduct() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<ProductDto> porIdProduct(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public Optional<UserDto> porIdUser(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbcUser.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void saveUser(UserDto userDto) {
        try {
            repositoryJdbcUser.save(userDto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void deletUser(Long id) {
    //    try {
    //        repositoryJdbc.delet(id);
    //    } catch (SQLException throwables) {
    //        throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

    //    }
    }

    @Override
    public List<UserDto> listarUser() {

        try {
            return repositoryJdbcUser.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }


    @Override
    public void save(ProductDto product) {
        try {
            repositoryJdbc.save(product);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void delet(Long id) {
        try {
            repositoryJdbc.delet(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public List<CategoryDto> listarCategory() {
        try {
            return repositoryJdbcCategpria.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public Optional<CategoryDto> porIdCategory(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbcCategpria.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }
}
