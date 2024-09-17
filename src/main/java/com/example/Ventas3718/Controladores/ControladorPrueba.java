/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Ventas3718.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/prueba/")
@Controller
public class ControladorPrueba {
    @GetMapping("/") //localhost/
    public String ListarPrueba(Model model) 
    {
        //model.addAttribute("clientes", lista);
        return "menu2"; //listaCliente.html
    }
}
