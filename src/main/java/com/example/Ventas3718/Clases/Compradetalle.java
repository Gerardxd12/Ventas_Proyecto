
package com.example.Ventas3718.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="compradetalle")
@Data
public class Compradetalle {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Double cantidad;
    private Double precio;
    private Double total;
    
    @ManyToOne()
    @JoinColumn(name = "compra_id", referencedColumnName = "id")
    private Compra compra;
    
    @ManyToOne()
    @JoinColumn(name="producto_id")
    private Producto producto;
}
