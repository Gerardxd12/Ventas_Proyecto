package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Tipocomprobante;
import com.example.Ventas3718.Interfaces.ITipocomprobanteService;
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

@RequestMapping("/tipocomprobante/")
@Controller
public class ControladorTipocomprobante {

    ArrayList<Tipocomprobante> lista = new ArrayList();
    String carpeta = "Tipocomprobante/";
    
    @Autowired
    ITipocomprobanteService service;
    
    @GetMapping("/nuevo") //localhost/tipocomprobante/nuevo
    public String NuevoTipocomprobante() 
    {

        return carpeta+"nuevoTipocomprobante"; //nuevoTipocomprobante.html
    }

    @PostMapping("/registrar") //localhost/tipocomprobante/registrar
    public String RegistrarTipocomprobante(
            @RequestParam("nom") String nom,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Tipocomprobante mp = new Tipocomprobante();
        //c.setId(cod);
        mp.setNombre(nom);

        service.Guardar(mp);

        return ListarTipocomprobante(model);
    }

    @GetMapping("/") //localhost/
    public String ListarTipocomprobante(Model model) 
    {
        //model.addAttribute("tipocomprobantes", lista);
        model.addAttribute("tipocomprobantes", service.Listar());
        return carpeta+"listaTipocomprobante"; //listaTipocomprobante.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarTipocomprobante(@RequestParam("cod") int cod,
                                  Model model)
    {
        
        service.Eliminar(cod);
        return ListarTipocomprobante(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarTipocomprobante(@RequestParam("cod") int cod,
                                  Model model)
    {
        

        Optional<Tipocomprobante> cli = service.ConsultarId(cod);
        
        model.addAttribute("tipocomprobante", cli);
        return carpeta+"editarTipocomprobante"; //editarTipocomprobante.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarTipocomprobante(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            Model model)
    {
         //Aqui va el proceso de registrar
        Tipocomprobante mp = new Tipocomprobante();
        mp.setId(cod);
        mp.setNombre(nom);
        
        service.Guardar(mp); //Cuando se envia el ID -> Actualizar
        
        return ListarTipocomprobante(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarTipocomprobante(@RequestParam("desc") String desc,
                                Model model)
    {
        List<Tipocomprobante> tipocomprobantes = service.Buscar(desc);
        model.addAttribute("tipocomprobantes", tipocomprobantes);
        return carpeta+"listaTipocomprobante";
    }
      
}
