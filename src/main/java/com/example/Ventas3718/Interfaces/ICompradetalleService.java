package com.example.Ventas3718.Interfaces;

import com.example.Ventas3718.Clases.Compradetalle;
import java.util.List;
import java.util.Optional;

public interface ICompradetalleService {
    public List<Compradetalle> Listar();
    public Optional<Compradetalle> ConsultarId(int id);
    public void Guardar(Compradetalle c);
    public void Eliminar(int id);
    public Double SolesCompra(int id);
    public Double CantidadCompra(int id);
    public List<Compradetalle> BuscarPorIDCompra(int id);
    public Double getTotalCompras();
}
