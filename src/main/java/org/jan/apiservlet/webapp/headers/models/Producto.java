package org.jan.apiservlet.webapp.headers.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Producto {
    private Long id;
    private String tipo;
    private String marca;
    private String modelo;
    private String anio;
    private Categoria categoria;
    private int precio;
    private String disponible;

    public Producto(Long id,String tipoC, String tipo, String marca,String modelo, String anio, int precio, String disponible) {
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        Categoria categoria = new Categoria();
        categoria.setNombre(tipoC);
        this.categoria = categoria;
        this.precio = precio;
        this.disponible = disponible;
    }
}
