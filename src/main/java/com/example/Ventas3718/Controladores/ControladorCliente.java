package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Cliente;
import com.example.Ventas3718.Interfaces.IClienteService;
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

@RequestMapping("/cliente/")
@Controller
public class ControladorCliente {

    ArrayList<Cliente> lista = new ArrayList();
    String carpeta = "Cliente/";
    
    @Autowired
    IClienteService service;
    
    @GetMapping("/nuevo") //localhost/cliente/nuevo
    public String NuevoCliente() 
    {

        return carpeta+"nuevoCliente"; //nuevoCliente.html
    }

    @PostMapping("/registrar") //localhost/cliente/registrar
    public String RegistrarCliente(
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("cel") String cel,
            @RequestParam("email") String email,
            @RequestParam("dir") String dir,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Cliente c = new Cliente();
        //c.setId(cod);
        c.setNombre(nom);
        c.setApellido(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);

        service.Guardar(c);

        return ListarCliente(model);
    }

    @GetMapping("/") //localhost/
    public String ListarCliente(Model model) 
    {
        //model.addAttribute("clientes", lista);
        model.addAttribute("clientes", service.Listar());
        return carpeta+"listaCliente"; //listaCliente.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarCliente(@RequestParam("cod") int cod,
                                  Model model)
    {
        
        service.Eliminar(cod);
        return ListarCliente(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarCliente(@RequestParam("cod") int cod,
                                  Model model)
    {
        

        Optional<Cliente> cli = service.ConsultarId(cod);
        
        model.addAttribute("cliente", cli);
        return carpeta+"editarCliente"; //editarCliente.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarCliente(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("apellido") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("celular") String cel,
            @RequestParam("email") String email,
            @RequestParam("direccion") String dir,
            Model model)
    {
         //Aqui va el proceso de registrar
        Cliente c = new Cliente();
        c.setId(cod);
        c.setNombre(nom);
        c.setApellido(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        
        service.Guardar(c); //Cuando se envia el ID -> Actualizar
        
        return ListarCliente(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarCliente(@RequestParam("desc") String desc,
                                Model model)
    {
        List<Cliente> clientes = service.Buscar(desc);
        model.addAttribute("clientes", clientes);
        return carpeta+"listaCliente";
    }
      
}
