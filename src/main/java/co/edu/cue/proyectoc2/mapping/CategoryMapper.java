package co.edu.cue.proyectoc2.mapping;

import co.edu.cue.proyectoc2.mapping.dto.CategoryDto;
import co.edu.cue.proyectoc2.models.Category;
import co.edu.cue.proyectoc2.models.Product;

public class CategoryMapper {
    public static CategoryDto mapFrom(Category category){
        return new CategoryDto(category.getId(), category.getName());
    }
    public static Product mapFromDto(CategoryDto categoryDto) {
        return Product.builder()
                .id(categoryDto.id())
                .model(categoryDto.name())
                .build();
    }
}
