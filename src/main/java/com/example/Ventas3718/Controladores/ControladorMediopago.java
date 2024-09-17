package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Mediopago;
import com.example.Ventas3718.Interfaces.IMediopagoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/mediopago/")
@Controller
public class ControladorMediopago {

    ArrayList<Mediopago> lista = new ArrayList();
    String carpeta = "Mediopago/";
    
    @Autowired
    IMediopagoService service;
    
    @GetMapping("/nuevo") //localhost/mediopago/nuevo
    public String NuevoMediopago() 
    {

        return carpeta+"nuevoMediopago"; //nuevoMediopago.html
    }

    @PostMapping("/registrar") //localhost/mediopago/registrar
    public String RegistrarMediopago(
            @RequestParam("nom") String nom,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Mediopago mp = new Mediopago();
        //c.setId(cod);
        mp.setNombre(nom);

        service.Guardar(mp);

        return ListarMediopago(model);
    }

    @GetMapping("/") //localhost/
    public String ListarMediopago(Model model) 
    {
        //model.addAttribute("mediopagos", lista);
        model.addAttribute("mediopagos", service.Listar());
        return carpeta+"listaMediopago"; //listaMediopago.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarMediopago(@RequestParam("cod") int cod,
                                  Model model)
    {
        
        service.Eliminar(cod);
        return ListarMediopago(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarMediopago(@RequestParam("cod") int cod,
                                  Model model)
    {
        

        Optional<Mediopago> cli = service.ConsultarId(cod);
        
        model.addAttribute("mediopago", cli);
        return carpeta+"editarMediopago"; //editarMediopago.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarMediopago(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            Model model)
    {
         //Aqui va el proceso de registrar
        Mediopago mp = new Mediopago();
        mp.setId(cod);
        mp.setNombre(nom);
        
        service.Guardar(mp); //Cuando se envia el ID -> Actualizar
        
        return ListarMediopago(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarMediopago(@RequestParam("desc") String desc,
                                Model model)
    {
        List<Mediopago> mediopagos = service.Buscar(desc);
        model.addAttribute("mediopagos", mediopagos);
        return carpeta+"listaMediopago";
    }
      
}
