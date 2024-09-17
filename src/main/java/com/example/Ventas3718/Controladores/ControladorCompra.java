package com.example.Ventas3718.Controladores;

import com.example.Ventas3718.Clases.Carrito;
import com.example.Ventas3718.Clases.Compra;
import com.example.Ventas3718.Clases.CompraAux;
import com.example.Ventas3718.Clases.Compradetalle;
import com.example.Ventas3718.Clases.Mediopago;
import com.example.Ventas3718.Clases.Producto;
import com.example.Ventas3718.Clases.Proveedor;
import com.example.Ventas3718.Clases.Tipocomprobante;
import com.example.Ventas3718.Clases.Usuario;
import com.example.Ventas3718.Clases.VentadetalleAux;
import com.example.Ventas3718.Interfaces.ICompraService;
import com.example.Ventas3718.Interfaces.ICompradetalleService;
import com.example.Ventas3718.Interfaces.IMediopagoService;
import com.example.Ventas3718.Interfaces.IProductoService;
import com.example.Ventas3718.Interfaces.IProveedorService;
import com.example.Ventas3718.Interfaces.ITipocomprobanteService;

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

@RequestMapping("/compra/")
@Controller
public class ControladorCompra {

    String carpeta = "Compra/";

    @Autowired
    ICompraService service;

    @Autowired
    ICompradetalleService service_cd;

    @Autowired
    IProveedorService service_prov;

    @Autowired
    IMediopagoService service_mp;

    @Autowired
    ITipocomprobanteService service_tc;

    @Autowired
    IProductoService service_prod;

    ArrayList<Carrito> carrito = new ArrayList();

    @GetMapping("/") //localhost/
    public String ListarCompra(Model model) {

        ArrayList<CompraAux> lista_aux = new ArrayList();
        List<Compra> compras = service.Listar();
        ArrayList<VentadetalleAux> lista_detalle = new ArrayList();

        /*ArrayList<CompradetalleAux> lista_detalle = new ArrayList();*/
        List<Compradetalle> cdetalle = service_cd.Listar();

        for (int i = 0; i < compras.size(); i++) {
            int id_compra = compras.get(i).getId();
            Double soles = service_cd.SolesCompra(id_compra);
            Double cantidad = service_cd.CantidadCompra(id_compra);

            CompraAux aux = new CompraAux();
            aux.setId(id_compra);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNombre_proveedor(compras.get(i).getProveedor().getNombre());
            aux.setMediopago(compras.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(compras.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(compras.get(i).getUsuario().getNombre());
            aux.setApe_usuario(compras.get(i).getUsuario().getApellido());
            aux.setFecha(compras.get(i).getFecha());

            lista_aux.add(aux);
        }

        for (int i = 0; i < cdetalle.size(); i++) {
            int id_ventad = cdetalle.get(i).getId();
            VentadetalleAux c = new VentadetalleAux();
            c.setId(id_ventad);
            c.setProducto(cdetalle.get(i).getProducto().getNombre());
            c.setPrecio(cdetalle.get(i).getPrecio());
            c.setCantidad(cdetalle.get(i).getCantidad());
            c.setTotal(cdetalle.get(i).getTotal());

            lista_detalle.add(c);
        }

        model.addAttribute("compradetalle", lista_detalle);

        model.addAttribute("compras", lista_aux);
        return carpeta + "listaCompra"; //listaVenta.html
    }

    @GetMapping("/Ascendente") //localhost/
    public String ListarCompraAsc(Model model) {

        ArrayList<CompraAux> lista_aux = new ArrayList();
        List<Compra> compras = service.ListarOrdenASC();
        ArrayList<VentadetalleAux> lista_detalle = new ArrayList();

        /*ArrayList<CompradetalleAux> lista_detalle = new ArrayList();*/
        List<Compradetalle> cdetalle = service_cd.Listar();

        for (int i = 0; i < compras.size(); i++) {
            int id_compra = compras.get(i).getId();
            Double soles = service_cd.SolesCompra(id_compra);
            Double cantidad = service_cd.CantidadCompra(id_compra);

            CompraAux aux = new CompraAux();
            aux.setId(id_compra);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNombre_proveedor(compras.get(i).getProveedor().getNombre());
            aux.setMediopago(compras.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(compras.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(compras.get(i).getUsuario().getNombre());
            aux.setApe_usuario(compras.get(i).getUsuario().getApellido());
            aux.setFecha(compras.get(i).getFecha());

            lista_aux.add(aux);
        }

        for (int i = 0; i < cdetalle.size(); i++) {
            int id_ventad = cdetalle.get(i).getId();
            VentadetalleAux c = new VentadetalleAux();
            c.setId(id_ventad);
            c.setProducto(cdetalle.get(i).getProducto().getNombre());
            c.setPrecio(cdetalle.get(i).getPrecio());
            c.setCantidad(cdetalle.get(i).getCantidad());
            c.setTotal(cdetalle.get(i).getTotal());

            lista_detalle.add(c);
        }

        model.addAttribute("compradetalle", lista_detalle);

        model.addAttribute("compras", lista_aux);
        return carpeta + "listaCompraAsc"; //listaVenta.html
    }

    @GetMapping("/Descendente") //localhost/
    public String ListarCompraDesc(Model model) {

        ArrayList<CompraAux> lista_aux = new ArrayList();
        List<Compra> compras = service.ListarOrdenDES();
        ArrayList<VentadetalleAux> lista_detalle = new ArrayList();

        /*ArrayList<CompradetalleAux> lista_detalle = new ArrayList();*/
        List<Compradetalle> cdetalle = service_cd.Listar();

        for (int i = 0; i < compras.size(); i++) {
            int id_compra = compras.get(i).getId();
            Double soles = service_cd.SolesCompra(id_compra);
            Double cantidad = service_cd.CantidadCompra(id_compra);

            CompraAux aux = new CompraAux();
            aux.setId(id_compra);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNombre_proveedor(compras.get(i).getProveedor().getNombre());
            aux.setMediopago(compras.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(compras.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(compras.get(i).getUsuario().getNombre());
            aux.setApe_usuario(compras.get(i).getUsuario().getApellido());
            aux.setFecha(compras.get(i).getFecha());

            lista_aux.add(aux);
        }

        for (int i = 0; i < cdetalle.size(); i++) {
            int id_ventad = cdetalle.get(i).getId();
            VentadetalleAux c = new VentadetalleAux();
            c.setId(id_ventad);
            c.setProducto(cdetalle.get(i).getProducto().getNombre());
            c.setPrecio(cdetalle.get(i).getPrecio());
            c.setCantidad(cdetalle.get(i).getCantidad());
            c.setTotal(cdetalle.get(i).getTotal());

            lista_detalle.add(c);
        }

        model.addAttribute("compradetalle", lista_detalle);

        model.addAttribute("compras", lista_aux);
        return carpeta + "listaCompraDesc"; //listaVenta.html
    }

    @GetMapping("/nuevo") //localhost/venta/nuevo
    public String NuevoCompra(Model model) {
        model.addAttribute("proveedores", service_prov.Listar());
        model.addAttribute("mediopagos", service_mp.Listar());
        model.addAttribute("tipocomprobantes", service_tc.Listar());
        model.addAttribute("productos", service_prod.Listar());
        model.addAttribute("carrito", carrito);

        return carpeta + "nuevoCompra"; //nuevoVenta.html
    }

    @PostMapping("/agregar")
    public String AgregarCarrito(@RequestParam("producto_id") int id,
            @RequestParam("cant") Double cant,
            Model model) {
        Optional<Producto> producto = service_prod.ConsultarId(id);
        String nombre = producto.get().getNombre();
        Double precio = producto.get().getPrecioc();
        Double total = precio * cant;

        Carrito car = new Carrito();
        car.setId(id);
        car.setProducto(nombre);
        car.setPrecio(precio);
        car.setCantidad(cant);
        car.setTotal(total);

        carrito.add(car);

        return NuevoCompra(model);
    }

    @GetMapping("/quitar")
    public String QuitarCarrito(@RequestParam("cod") int cod,
            Model model) {
        carrito.remove(cod - 1);
        return NuevoCompra(model);
    }

    @PostMapping("/registrar")
    public String RegistrarCompra(@RequestParam("proveedor_id") Proveedor proveedor,
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

        Compra c = new Compra();
        c.setFecha(fecha);
        c.setProveedor(proveedor);
        c.setMediopago(mediopago);
        c.setTipocomprobante(tipocomprobante);
        c.setUsuario(usuario);

        service.Guardar(c);

        int id_compra = service.UltimoIdCompra();
        Compra comp = new Compra();
        comp.setId(id_compra);

        //Registrar compra detalle
        for (int i = 0; i < carrito.size(); i++) {
            int id_producto = carrito.get(i).getId();
            Optional<Producto> productoOptional = service_prod.ObtenerProductoPorId(id_producto);

            if (productoOptional.isPresent()) {
                Producto pr = productoOptional.get();
                Double cantidad = carrito.get(i).getCantidad();
                Double nuevoStock = pr.getStock() + cantidad;

                // Actualizar el stock del producto
                pr.setStock(nuevoStock);

                // Guardar el producto actualizado
                service_prod.Guardar(pr);
            }

            Producto p = new Producto();
            p.setId(id_producto);

            Double precio = carrito.get(i).getPrecio();
            Double cantidad = carrito.get(i).getCantidad();
            Double total = carrito.get(i).getTotal();

            Compradetalle cd = new Compradetalle();
            cd.setCompra(comp);
            cd.setProducto(p);
            cd.setPrecio(precio);
            cd.setCantidad(cantidad);
            cd.setTotal(total);

            //guardar la venta detalle
            service_cd.Guardar(cd);
        }

        carrito.clear();

        return ListarCompra(model);
    }

    @PostMapping("/buscar")
    public String Buscar(@RequestParam("desc") String desc,
            Model model) {

        ArrayList<CompraAux> lista_aux = new ArrayList();

        List<Compra> compras = service.Buscar(desc);
        for (int i = 0; i < compras.size(); i++) {
            int id_compra = compras.get(i).getId();
            Double soles = service_cd.SolesCompra(id_compra);
            Double cantidad = service_cd.CantidadCompra(id_compra);

            CompraAux aux = new CompraAux();
            aux.setId(id_compra);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNombre_proveedor(compras.get(i).getProveedor().getNombre());
            aux.setMediopago(compras.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(compras.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(compras.get(i).getUsuario().getNombre());
            aux.setApe_usuario(compras.get(i).getUsuario().getApellido());
            aux.setFecha(compras.get(i).getFecha());

            lista_aux.add(aux);
        }

        model.addAttribute("compras", lista_aux);
        return carpeta + "listaCompra";
    }

    @GetMapping("/cdetalle/{id}")
    public String Compradetalle(@PathVariable("id") int id, Model model) {
        Optional<Compra> verDetalle = service.ConsultarId(id);

        if (verDetalle.isPresent()) {
            Compra compra = verDetalle.get();
            List<Compradetalle> detalle = service_cd.BuscarPorIDCompra(id);

            model.addAttribute("compras", compra);
            model.addAttribute("detalles", detalle);
        }
        return carpeta + "compradetalle";
    }
    /*

    @GetMapping("/anular")
    public String anularVenta(@RequestParam("id") int idVenta) {
        // Lógica para anular la venta
        service.AnularVenta(idVenta);
        return "redirect:/venta/lista";
    }*/

}
