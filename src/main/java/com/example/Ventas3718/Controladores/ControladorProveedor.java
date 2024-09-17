package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Proveedor;
import com.example.Ventas3718.Interfaces.IProveedorService;
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

@RequestMapping("/proveedor/")
@Controller
public class ControladorProveedor {

    ArrayList<Proveedor> lista = new ArrayList();
    String carpeta = "Proveedor/";
    
    @Autowired
    IProveedorService service;
    
    @GetMapping("/nuevo") //localhost/proveedor/nuevo
    public String NuevoProveedor() 
    {

        return carpeta+"nuevoProveedor"; //nuevoProveedor.html
    }

    @PostMapping("/registrar") //localhost/proveedor/registrar
    public String RegistrarProveedor(
            @RequestParam("nom") String nom,
            @RequestParam("ruc") String ruc,
            @RequestParam("cel") String cel,
            @RequestParam("email") String email,
            @RequestParam("dir") String dir,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Proveedor c = new Proveedor();
        //c.setId(cod);
        c.setNombre(nom);
        c.setRuc(ruc);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);

        service.Guardar(c);

        return ListarProveedor(model);
    }

    @GetMapping("/") //localhost/
    public String ListarProveedor(Model model) 
    {
        //model.addAttribute("proveedors", lista);
        model.addAttribute("proveedors", service.Listar());
        return carpeta+"listaProveedor"; //listaProveedor.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarProveedor(@RequestParam("cod") int cod,
                                  Model model)
    {
        
        service.Eliminar(cod);
        return ListarProveedor(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarProveedor(@RequestParam("cod") int cod,
                                  Model model)
    {
        

        Optional<Proveedor> cli = service.ConsultarId(cod);
        
        model.addAttribute("proveedor", cli);
        return carpeta+"editarProveedor"; //editarProveedor.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarProveedor(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("ruc") String ruc,
            @RequestParam("celular") String cel,
            @RequestParam("email") String email,
            @RequestParam("direccion") String dir,
            Model model)
    {
         //Aqui va el proceso de registrar
        Proveedor c = new Proveedor();
        c.setId(cod);
        c.setNombre(nom);
        c.setRuc(ruc);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        
        service.Guardar(c); //Cuando se envia el ID -> Actualizar
        
        return ListarProveedor(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarProveedor(@RequestParam("desc") String desc,
                                Model model)
    {
        List<Proveedor> proveedors = service.Buscar(desc);
        model.addAttribute("proveedors", proveedors);
        return carpeta+"listaProveedor";
    }
      
}
