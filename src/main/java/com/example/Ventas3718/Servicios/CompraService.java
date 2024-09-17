package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Compra;
import com.example.Ventas3718.Interfaces.ICompraService;
import com.example.Ventas3718.Repositorios.ICompra;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private ICompra data;

    @Override
    public List<Compra> Listar() {
        return (List<Compra>) data.findAll();
    }

    @Override
    public Optional<Compra> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Compra c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Compra> Buscar(String desc) {
        return data.findForAll(desc);
    }

    @Override
    public int UltimoIdCompra() {
        return data.ConsultarIdCompra();
    }

    /*@Override
    public void AnularVenta(int id) {
        Optional<Venta> ventaOptional = data.findById(id);

        if (ventaOptional.isPresent()) {
            Venta venta = ventaOptional.get();
            venta.setAnulada(true);
            data.save(venta);
        }
    }*/
    @Override
    public List<Compra> ListarOrdenASC() {
        return (List<Compra>) data.findAll(Sort.by(Sort.Order.asc("fecha")));
    }

    @Override
    public List<Compra> ListarOrdenDES() {
        return (List<Compra>) data.findAll(Sort.by(Sort.Order.desc("fecha")));
    }

}
