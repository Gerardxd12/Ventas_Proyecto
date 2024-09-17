package com.example.Ventas3718.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="venta")
@Data
public class Venta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Date fecha;
    
    @Enumerated(EnumType.STRING)
    private VentaEstado estado;
    
    @ManyToOne()
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    
    @ManyToOne()
    @JoinColumn(name="mediopago_id")
    private Mediopago mediopago;
    
    @ManyToOne()
    @JoinColumn(name="tipocomprobante_id")
    private Tipocomprobante tipocomprobante;
    
    @ManyToOne()
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    
    /*private boolean anulada;

    // Constructor, getters y setters

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public void anular() {
        // Puedes realizar lógica adicional aquí si es necesario
        this.anulada = true;
    }*/
}
