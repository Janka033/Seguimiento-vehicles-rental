package co.edu.cue.proyectoc2.services;

import co.edu.cue.proyectoc2.mapping.dto.CategoryDto;
import co.edu.cue.proyectoc2.mapping.dto.ProductDto;
import co.edu.cue.proyectoc2.mapping.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void saveUser(UserDto userDto);
    void deletUser(Long userDto);
    List<UserDto> listarUser();
    Optional<UserDto> porIdUser(Long id);
    void save(ProductDto producto);
    void delet(Long id);
    List<ProductDto> listarProduct();
    List<CategoryDto> listarCategory();
    Optional<CategoryDto> porIdCategory(Long id);
    Optional<ProductDto> porIdProduct(Long id);
}
