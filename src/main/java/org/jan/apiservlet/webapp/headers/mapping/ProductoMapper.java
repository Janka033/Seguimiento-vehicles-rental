package org.jan.apiservlet.webapp.headers.mapping;

import org.jan.apiservlet.webapp.headers.mapping.dto.ProductoDto;
import org.jan.apiservlet.webapp.headers.models.Producto;

public class ProductoMapper {
    public static ProductoDto mapFrom(Producto producto){
        return new ProductoDto(producto.getId(),producto.getTipo(), producto.getMarca(), producto.getModelo(), producto.getCategoria(),producto.getAnio(),producto.getPrecio(), producto.getDisponible());
    }
    public static Producto mapFromDto(ProductoDto productoDto){
        return Producto.builder()
                .id(productoDto.id())
                .tipo(productoDto.tipo())
                .marca(productoDto.marca())
                .modelo(productoDto.modelo())
                .categoria(productoDto.categoria())
                .anio(productoDto.anio())
                .precio(productoDto.precio())
                .disponible(productoDto.disponible())
                .build();
    }
}
