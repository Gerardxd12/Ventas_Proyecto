package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Ventadetalle;
import java.util.List;
import java.util.Optional;


public interface IVentadetalleService {
    public List<Ventadetalle> Listar();
    public Optional<Ventadetalle> ConsultarId(int id);
    public void Guardar(Ventadetalle c);
    public void Eliminar(int id);
    public Double SolesVenta(int id);
    public Double CantidadVenta(int id);
    public List<Ventadetalle> BuscarPorIDVenta(int id);
    public Double getTotalVentas();
}
