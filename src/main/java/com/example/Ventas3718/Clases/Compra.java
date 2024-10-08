package com.example.Ventas3718.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="compra")
@Data
public class Compra {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Date fecha;
    
    @ManyToOne()
    @JoinColumn(name="proveedor_id")
    private Proveedor proveedor;
    
    @ManyToOne()
    @JoinColumn(name="mediopago_id")
    private Mediopago mediopago;
    
    @ManyToOne()
    @JoinColumn(name="tipocomprobante_id")
    private Tipocomprobante tipocomprobante;
    
    @ManyToOne()
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    
}
