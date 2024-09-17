package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Carrito;
import com.example.Ventas3718.Clases.Cliente;
import com.example.Ventas3718.Clases.Mediopago;
import com.example.Ventas3718.Clases.Producto;
import com.example.Ventas3718.Clases.Tipocomprobante;
import com.example.Ventas3718.Clases.Usuario;
import com.example.Ventas3718.Clases.Venta;
import com.example.Ventas3718.Clases.VentaAux;
import com.example.Ventas3718.Clases.VentaEstado;
import com.example.Ventas3718.Clases.Ventadetalle;
import com.example.Ventas3718.Clases.VentadetalleAux;
import com.example.Ventas3718.Interfaces.IClienteService;
import com.example.Ventas3718.Interfaces.IMediopagoService;
import com.example.Ventas3718.Interfaces.IProductoService;
import com.example.Ventas3718.Interfaces.ITipocomprobanteService;
import com.example.Ventas3718.Interfaces.IVentaService;
import com.example.Ventas3718.Interfaces.IVentadetalleService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/venta/")
@Controller
public class ControladorVenta {

    String carpeta = "Venta/";

    @Autowired
    IVentaService service;

    @Autowired
    IVentadetalleService service_vd;

    @Autowired
    IClienteService service_cli;

    @Autowired
    IMediopagoService service_mp;

    @Autowired
    ITipocomprobanteService service_tc;

    @Autowired
    IProductoService service_pro;

    ArrayList<Carrito> carrito = new ArrayList();

    @GetMapping("/") //localhost/
    public String ListarVenta(Model model) {
        ArrayList<VentadetalleAux> lista_detalle = new ArrayList();
        ArrayList<VentaAux> lista_aux = new ArrayList();
        List<Venta> ventas = service.Listar();
        List<Ventadetalle> vdetalle = service_vd.Listar();

        for (int i = 0; i < ventas.size(); i++) {
            int id_venta = ventas.get(i).getId();
            Double soles = service_vd.SolesVenta(id_venta);
            Double cantidad = service_vd.CantidadVenta(id_venta);

            VentaAux aux = new VentaAux();
            aux.setId(id_venta);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_cliente(ventas.get(i).getCliente().getNombre());
            aux.setApe_cliente(ventas.get(i).getCliente().getApellido());
            aux.setMediopago(ventas.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(ventas.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(ventas.get(i).getUsuario().getNombre());
            aux.setApe_usuario(ventas.get(i).getUsuario().getApellido());
            aux.setFecha(ventas.get(i).getFecha());

            lista_aux.add(aux);
        }

        for (int i = 0; i < vdetalle.size(); i++) {
            int id_ventad = vdetalle.get(i).getId();
            VentadetalleAux c = new VentadetalleAux();
            c.setId(id_ventad);
            c.setProducto(vdetalle.get(i).getProducto().getNombre());
            c.setPrecio(vdetalle.get(i).getPrecio());
            c.setCantidad(vdetalle.get(i).getCantidad());
            c.setTotal(vdetalle.get(i).getTotal());

            lista_detalle.add(c);
        }

        model.addAttribute("ventadetalle", lista_detalle);

        model.addAttribute("ventas", lista_aux);
        return carpeta + "listaVenta"; //listaVenta.html
    }

    @GetMapping("/Ascendente") //localhost/
    public String ListarVentaAsc(Model model) {
        ArrayList<VentadetalleAux> lista_detalle = new ArrayList();
        ArrayList<VentaAux> lista_aux = new ArrayList();
        List<Venta> ventas = service.ListarOrdenASC();
        List<Ventadetalle> vdetalle = service_vd.Listar();

        for (int i = 0; i < ventas.size(); i++) {
            int id_venta = ventas.get(i).getId();
            Double soles = service_vd.SolesVenta(id_venta);
            Double cantidad = service_vd.CantidadVenta(id_venta);

            VentaAux aux = new VentaAux();
            aux.setId(id_venta);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_cliente(ventas.get(i).getCliente().getNombre());
            aux.setApe_cliente(ventas.get(i).getCliente().getApellido());
            aux.setMediopago(ventas.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(ventas.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(ventas.get(i).getUsuario().getNombre());
            aux.setApe_usuario(ventas.get(i).getUsuario().getApellido());
            aux.setFecha(ventas.get(i).getFecha());

            lista_aux.add(aux);
        }

        for (int i = 0; i < vdetalle.size(); i++) {
            int id_ventad = vdetalle.get(i).getId();
            VentadetalleAux c = new VentadetalleAux();
            c.setId(id_ventad);
            c.setProducto(vdetalle.get(i).getProducto().getNombre());
            c.setPrecio(vdetalle.get(i).getPrecio());
            c.setCantidad(vdetalle.get(i).getCantidad());
            c.setTotal(vdetalle.get(i).getTotal());

            lista_detalle.add(c);
        }

        model.addAttribute("ventadetalle", lista_detalle);

        model.addAttribute("ventas", lista_aux);
        return carpeta + "listaVentaAsc"; //listaVenta.html
    }

    @GetMapping("/Descendente") //localhost/
    public String ListarVentaDesc(Model model) {
        ArrayList<VentadetalleAux> lista_detalle = new ArrayList();
        ArrayList<VentaAux> lista_aux = new ArrayList();
        List<Venta> ventas = service.ListarOrdenDES();
        List<Ventadetalle> vdetalle = service_vd.Listar();

        for (int i = 0; i < ventas.size(); i++) {
            int id_venta = ventas.get(i).getId();
            Double soles = service_vd.SolesVenta(id_venta);
            Double cantidad = service_vd.CantidadVenta(id_venta);

            VentaAux aux = new VentaAux();
            aux.setId(id_venta);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_cliente(ventas.get(i).getCliente().getNombre());
            aux.setApe_cliente(ventas.get(i).getCliente().getApellido());
            aux.setMediopago(ventas.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(ventas.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(ventas.get(i).getUsuario().getNombre());
            aux.setApe_usuario(ventas.get(i).getUsuario().getApellido());
            aux.setFecha(ventas.get(i).getFecha());

            lista_aux.add(aux);
        }

        for (int i = 0; i < vdetalle.size(); i++) {
            int id_ventad = vdetalle.get(i).getId();
            VentadetalleAux c = new VentadetalleAux();
            c.setId(id_ventad);
            c.setProducto(vdetalle.get(i).getProducto().getNombre());
            c.setPrecio(vdetalle.get(i).getPrecio());
            c.setCantidad(vdetalle.get(i).getCantidad());
            c.setTotal(vdetalle.get(i).getTotal());

            lista_detalle.add(c);
        }

        model.addAttribute("ventadetalle", lista_detalle);

        model.addAttribute("ventas", lista_aux);
        return carpeta + "listaVentaDesc"; //listaVenta.html
    }

    @GetMapping("/nuevo") //localhost/venta/nuevo
    public String NuevoVenta(Model model) {
        model.addAttribute("clientes", service_cli.Listar());
        model.addAttribute("mediopagos", service_mp.Listar());
        model.addAttribute("tipocomprobantes", service_tc.Listar());
        model.addAttribute("productos", service_pro.Listar());
        model.addAttribute("carrito", carrito);

        return carpeta + "nuevoVenta"; //nuevoVenta.html
    }

    @PostMapping("/agregar")
    public String AgregarCarrito(@RequestParam("producto_id") int id,
            @RequestParam("cant") Double cant,
            Model model) {
        Optional<Producto> producto = service_pro.ConsultarId(id);
        String nombre = producto.get().getNombre();
        Double precio = producto.get().getPreciov();
        Double total = precio * cant;

        Carrito car = new Carrito();
        car.setId(id);
        car.setProducto(nombre);
        car.setPrecio(precio);
        car.setCantidad(cant);
        car.setTotal(total);

        carrito.add(car);

        return NuevoVenta(model);
    }

    @GetMapping("/quitar")
    public String QuitarCarrito(@RequestParam("cod") int cod,
            Model model) {
        carrito.remove(cod - 1);
        return NuevoVenta(model);
    }

    @PostMapping("/registrar")
    public String RegistrarVenta(@RequestParam("cliente_id") Cliente cliente,
            @RequestParam("tipocomprobante_id") Tipocomprobante tipocomprobante,
            @RequestParam("mediopago_id") Mediopago mediopago,
            @RequestParam("fec") String fec,
            Model model) throws ParseException {
        //2023-10-31T21:53
        String[] parts = fec.split("T");
        String part1 = parts[0]; //2023-10-31
        String part2 = parts[1]; //21:53
        String fec_ = part1 + " " + part2 + ":00";
        //2023-10-31 21:53:00

        SimpleDateFormat formateador_fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = formateador_fecha.parse(fec_);

        Usuario usuario = new Usuario();
        usuario.setId(1);

        Venta v = new Venta();
        v.setFecha(fecha);
        v.setCliente(cliente);
        v.setMediopago(mediopago);
        v.setTipocomprobante(tipocomprobante);
        v.setUsuario(usuario);
        
        v.setEstado(VentaEstado.ACTIVO);

        service.Guardar(v);

        int id_venta = service.UltimoIdVenta();
        Venta vent = new Venta();
        vent.setId(id_venta);

        //Registrar venta detalle
        for (int i = 0; i < carrito.size(); i++) {
            int id_producto = carrito.get(i).getId();
            Optional<Producto> productoOptional = service_pro.ObtenerProductoPorId(id_producto);

            if (productoOptional.isPresent()) {
                Producto pr = productoOptional.get();
                Double cantidad = carrito.get(i).getCantidad();
                Double nuevoStock = pr.getStock() - cantidad;
                pr.setStock(nuevoStock);
                service_pro.Guardar(pr);
            }
            

            Producto p = new Producto();
            p.setId(id_producto);

            Double precio = carrito.get(i).getPrecio();
            Double cantidad = carrito.get(i).getCantidad();
            Double total = carrito.get(i).getTotal();

            Ventadetalle vd = new Ventadetalle();
            vd.setVenta(vent);
            vd.setProducto(p);
            vd.setPrecio(precio);
            vd.setCantidad(cantidad);
            vd.setTotal(total);

            //guardar la venta detalle
            service_vd.Guardar(vd);
        }

        carrito.clear();

        return ListarVenta(model);
    }

    @PostMapping("/buscar")
    public String Buscar(@RequestParam("desc") String desc,
            Model model) {

        ArrayList<VentaAux> lista_aux = new ArrayList();

        List<Venta> ventas = service.Buscar(desc);
        for (int i = 0; i < ventas.size(); i++) {
            int id_venta = ventas.get(i).getId();
            Double soles = service_vd.SolesVenta(id_venta);
            Double cantidad = service_vd.CantidadVenta(id_venta);

            VentaAux aux = new VentaAux();
            aux.setId(id_venta);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_cliente(ventas.get(i).getCliente().getNombre());
            aux.setApe_cliente(ventas.get(i).getCliente().getApellido());
            aux.setMediopago(ventas.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(ventas.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(ventas.get(i).getUsuario().getNombre());
            aux.setApe_usuario(ventas.get(i).getUsuario().getApellido());
            aux.setFecha(ventas.get(i).getFecha());

            lista_aux.add(aux);
        }

        model.addAttribute("ventas", lista_aux);
        return carpeta + "listaVenta";
    }

    @GetMapping("/vdetalle/{id}")
    public String Ventadetalle(@PathVariable("id") int id, Model model) {
        Optional<Venta> verDetalle = service.ConsultarId(id);

        if (verDetalle.isPresent()) {
            Venta venta = verDetalle.get();
            List<Ventadetalle> detalle = service_vd.BuscarPorIDVenta(id);
            

            model.addAttribute("ventas", venta);
            model.addAttribute("detalles", detalle);
        }
        return carpeta + "ventadetalle";
    }
    
    @PostMapping("/anular")
    public String AnularVenta(@RequestParam("id") int id, Model model) {
        Optional<Venta> ventaOptional = service.ConsultarId(id);

        /*if (ventaOptional.isPresent()) { 
            Venta venta = ventaOptional.get();
            venta.setEstado(VentaEstado.ANULADO);
            service.Guardar(venta);
        }*/
        if (ventaOptional.isPresent()) { 
        Venta venta = ventaOptional.get();

        // Restaurar el stock de los productos vendidos en la venta anulada
        List<Ventadetalle> detalles = service_vd.BuscarPorIDVenta(id);
        for (Ventadetalle detalle : detalles) {
            Optional<Producto> productoOptional = service_pro.ConsultarId(detalle.getProducto().getId());

            if (productoOptional.isPresent()) {
                Producto producto = productoOptional.get();
                Double cantidad = detalle.getCantidad();
                Double nuevoStock = producto.getStock() + cantidad;
                producto.setStock(nuevoStock);
                service_pro.Guardar(producto);
            }
        }
        // Actualizar el estado de la venta a ANULADO
        venta.setEstado(VentaEstado.ANULADO);
        service.Guardar(venta);
    }
    return ListarVenta(model);
    }

    /*
    @GetMapping("/anular")
    public String anularVenta(@RequestParam("id") int idVenta) {
        // Lógica para anular la venta
        service.AnularVenta(idVenta);
        return "redirect:/venta/lista";
    }*/
}
