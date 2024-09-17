package com.example.Ventas3718.Clases;

import lombok.Data;

@Data
public class Carrito {
    private int id; //id del producto
    private String producto; //nombre del producto
    private Double precio;
    private Double cantidad;
    private Double total;
}
