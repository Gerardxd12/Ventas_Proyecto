/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Cliente;
import com.example.Ventas3718.Clases.Mediopago;
import com.example.Ventas3718.Clases.Producto;
import com.example.Ventas3718.Interfaces.IClienteService;
import com.example.Ventas3718.Interfaces.ICompradetalleService;
import com.example.Ventas3718.Interfaces.IMediopagoService;
import com.example.Ventas3718.Interfaces.IProductoService;
import com.example.Ventas3718.Interfaces.IVentadetalleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reporte/")
@Controller
public class ControladorReporte {
    
    @Autowired
    IVentadetalleService service_dV;
    
    @Autowired
    ICompradetalleService service_dC;
      
    @Autowired
    IClienteService service_cli;
    
    @Autowired
    IProductoService service_prod;
    
    @Autowired
    IMediopagoService service_mp;

    @GetMapping("/mostrar")
    public String mostrarReporte(Model model) {
        
        List<Cliente> cliente = service_cli.Listar();
        List<Producto> producto = service_prod.Listar();
        Double totalVentas = service_dV.getTotalVentas();
        Double totalCompras = service_dC.getTotalCompras();
        List<Mediopago> mp= service_mp.Listar();
       
       

        
        Double ganancia =  (service_dV.getTotalVentas())-(service_dC.getTotalCompras());

        // Agregar los valores al modelo
        model.addAttribute("mps",mp);
        model.addAttribute("productos", producto);
        model.addAttribute("clientes", cliente);
        model.addAttribute("totalVentas", totalVentas);
        model.addAttribute("totalCompras", totalCompras);
        model.addAttribute("ganancia", ganancia);

        // Retornar la vista
        return "reporte/Reporte";
    }
}
