package co.edu.cue.proyectoc2.mapping.dto;

import co.edu.cue.proyectoc2.models.Category;

public record ProductDto(Long id, String type, String brand, String model, Category category, String year, int price, String available) {
}
