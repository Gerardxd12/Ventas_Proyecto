package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Producto;
import com.example.Ventas3718.Interfaces.IProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/producto/")
@Controller
public class ControladorProducto {

    ArrayList<Producto> lista = new ArrayList();
    String carpeta = "Producto/";
    
    @Autowired
    IProductoService service;
    
    @GetMapping("/nuevo") //localhost/producto/nuevo
    public String NuevoProducto() 
    {

        return carpeta+"nuevoProducto"; //nuevoProducto.html
    }

    @PostMapping("/registrar") //localhost/producto/registrar
    public String RegistrarProducto(
            @RequestParam("nom") String nom,
            @RequestParam("precc") Double precc,
            @RequestParam("precv") Double precv,
            @RequestParam("desc") String desc,
            Model model) 
    {
        //Aqui va el proceso de registrar
        Producto p = new Producto();
        //c.setId(cod);
        p.setNombre(nom);
        p.setPrecioc(precc);
        p.setPreciov(precv);
        p.setDescripcion(desc);
        p.setStock(0.0);

        //lista.add(c);
        service.Guardar(p);

        return ListarProducto(model);
    }

    @GetMapping("/") //localhost/
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String ListarProducto(Model model) 
    {
        //model.addAttribute("productos", lista);
        model.addAttribute("productos", service.Listar());
        return carpeta+"listaProducto"; //listaProducto.html
    }
    
    
    @GetMapping("/eliminar") //localhost/eliminar
    public String EliminarProducto(@RequestParam("cod") int cod,
                                  Model model)
    {
   
        service.Eliminar(cod);
        return ListarProducto(model);
    }
    
    @GetMapping("/editar") //localhost/editar
    public String EditarProducto(@RequestParam("cod") int cod,
                                  Model model)
    {
        Optional<Producto> prod = service.ConsultarId(cod);
        
        model.addAttribute("producto", prod);
        return carpeta+"editarProducto"; //editarProducto.html
    }
    
    @PostMapping("/actualizar") //localhost/actualizar
    public String ActualizarProducto(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("precioc") Double precc,
            @RequestParam("preciov") Double precv,
            @RequestParam("descripcion") String desc,
            Model model)
    {
     
        //Aqui va el proceso de registrar
        Producto p = new Producto();
        p.setId(cod);
        p.setNombre(nom);
        p.setPrecioc(precc);
        p.setPreciov(precv);
        p.setDescripcion(desc);
        
        service.Guardar(p); //Cuando se envia el ID -> Actualizar
        
        return ListarProducto(model);
    }
    
    @PostMapping("/buscar") //localhost/buscar
    public String BuscarProducto(@RequestParam("desc") String desc,
                                Model model)
    {
        List<Producto> productos = service.Buscar(desc);
        model.addAttribute("productos", productos);
        return carpeta+"listaProducto";
    }
      
}
