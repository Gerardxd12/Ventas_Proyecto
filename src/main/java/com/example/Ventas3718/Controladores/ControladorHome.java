package com.example.Ventas3718.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home/")
@Controller
public class ControladorHome {
    String carpeta = "Login/";

    @GetMapping("/") 
    public String MostrarHome() {
        return carpeta + "Home"; 
    }
    
}
