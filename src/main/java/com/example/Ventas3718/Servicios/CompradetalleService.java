/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Ventas3718.Servicios;

import com.example.Ventas3718.Clases.Compradetalle;
import com.example.Ventas3718.Interfaces.ICompradetalleService;
import com.example.Ventas3718.Repositorios.ICompradetalle;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompradetalleService implements ICompradetalleService {

    @Autowired
    private ICompradetalle data;

    @Override
    public List<Compradetalle> Listar() {
        return (List<Compradetalle>) data.findAll();
    }

    @Override
    public Optional<Compradetalle> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Compradetalle c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Double SolesCompra(int id) {
        return data.AllSales(id);
    }

    @Override
    public Double CantidadCompra(int id) {
        return data.AllCount(id);
    }

    @Override
    public List<Compradetalle> BuscarPorIDCompra(int id) {
        return data.findByCompraId(id);
    }

    @Override
    public Double getTotalCompras() {
        return data.getAllSalesForAllCompras();
    }


}
