package org.jan.apiservlet.webapp.headers.mapping.dto;

import org.jan.apiservlet.webapp.headers.models.Categoria;

import java.time.LocalDate;

public record ProductoDto(Long id,String tipo,String marca,String modelo,Categoria categoria,String anio,int precio,String disponible) {
}
