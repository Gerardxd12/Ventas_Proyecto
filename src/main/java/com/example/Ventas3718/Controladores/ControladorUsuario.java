package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Usuario;
import com.example.Ventas3718.Interfaces.IUsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/usuario/")
@Controller
public class ControladorUsuario {
    
    BCryptPasswordEncoder bcript = new BCryptPasswordEncoder();
    ArrayList<Usuario> lista = new ArrayList();
    String carpeta = "Usuario/";
    
    @Autowired
    IUsuarioService service;
    
    @GetMapping("/nuevo") //localhost/usuario/nuevo
    public String NuevoUsuario() 
    {

        return carpeta+"nuevoUsuario"; //nuevoUsuario.html
    }

    @PostMapping("/registrar") //localhost/usuario/registrar
    public String RegistrarUsuario(
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("cel") String cel,
            @RequestParam("email") String email,
            @RequestParam("dir") String dir,
            @RequestParam("user") String user,
            @RequestParam("pass") String pass,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Usuario u = new Usuario();
        //c.setId(cod);
        u.setNombre(nom);
        u.setApellido(ape);
        u.setDni(dni);
        u.setCelular(cel);
        u.setEmail(email);
        u.setDireccion(dir);
        u.setUser(user);
        u.setPassword(bcript.encode(pass));
        u.setRoles("ROLE_USER");

        service.Guardar(u);

        return ListarUsuario(model);
    }

    @GetMapping("/") //localhost/
    public String ListarUsuario(Model model) 
    {
        //model.addAttribute("usuarios", lista);
        model.addAttribute("usuarios", service.Listar());
        return carpeta+"listaUsuario"; //listaUsuario.html
    }
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarUsuario(@RequestParam("cod") int cod,
                                  Model model)
    {
        
        service.Eliminar(cod);
        return ListarUsuario(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarUsuario(@RequestParam("cod") int cod,
                                  Model model)
    {
        

        Optional<Usuario> cli = service.ConsultarId(cod);
        
        model.addAttribute("usuario", cli);
        return carpeta+"editarUsuario"; //editarUsuario.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarUsuario(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("apellido") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("celular") String cel,
            @RequestParam("email") String email,
            @RequestParam("direccion") String dir,
            @RequestParam("user") String user,
            @RequestParam("password") String pass,
            Model model)
    {
         //Aqui va el proceso de registrar
        Usuario u = new Usuario();
        u.setId(cod);
        u.setNombre(nom);
        u.setApellido(ape);
        u.setDni(dni);
        u.setCelular(cel);
        u.setEmail(email);
        u.setDireccion(dir);
        u.setUser(user);
        u.setPassword(pass);
        
        service.Guardar(u); //Cuando se envia el ID -> Actualizar
        
        return ListarUsuario(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarUsuario(@RequestParam("desc") String desc,
                                Model model)
    {
        List<Usuario> usuarios = service.Buscar(desc);
        model.addAttribute("usuarios", usuarios);
        return carpeta+"listaUsuario";
    }
      
}
