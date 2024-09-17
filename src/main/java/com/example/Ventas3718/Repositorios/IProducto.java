package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducto extends CrudRepository<Producto,Integer> {
    
    @Query(value="SELECT * FROM producto "
            + "WHERE nombre LIKE %?1% "
            + "OR precioc LIKE %?1% "
            + "OR preciov LIKE %?1% "
            + "OR descripcion LIKE %?1%"
            + "OR stock LIKE %?1% ",nativeQuery=true)
    List<Producto> findForAll(String desc);
    
    @Query(value="SELECT SUM(stock) as stock_total "
            + "FROM producto "
            + "WHERE producto_id = ?1",nativeQuery=true)
    public Double StockProd(int id);
    
    /*@Query("UPDATE producto p SET p.stock = p.stock + (SELECT c.cantidad FROM compradetalle c WHERE c.producto.id = productoId) WHERE p.id = productoId")
    void aumentarStockPorCompra(@Param("productoId") int productoId);*/
}
