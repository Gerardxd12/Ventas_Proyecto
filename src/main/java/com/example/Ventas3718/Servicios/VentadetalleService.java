package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Ventadetalle;
import com.example.Ventas3718.Interfaces.IVentadetalleService;
import com.example.Ventas3718.Repositorios.IVentadetalle;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentadetalleService implements IVentadetalleService {

    @Autowired
    private IVentadetalle data;

    @Override
    public List<Ventadetalle> Listar() {
        return (List<Ventadetalle>) data.findAll();
    }

    @Override
    public Optional<Ventadetalle> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Ventadetalle c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Double SolesVenta(int id) {
        return data.AllSales(id);
    }

    @Override
    public Double CantidadVenta(int id) {
        return data.AllCount(id);
    }

    @Override
    public List<Ventadetalle> BuscarPorIDVenta(int id) {
        return data.findByVentaId(id);
    }

    @Override
    public Double getTotalVentas() {
        return data.getAllSalesForAllVentas();
    }

}
