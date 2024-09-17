package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Ventadetalle;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentadetalle extends CrudRepository<Ventadetalle,Integer> {
        
    @Query(value="SELECT SUM(total) as monto_total "
            + "FROM ventadetalle "
            + "WHERE venta_id = ?1",nativeQuery=true)
    public Double AllSales(int id);
    
    List<Ventadetalle> findByVentaId(int id);
    
    @Query(value="SELECT COUNT(*) as cant_total "
            + "FROM ventadetalle "
            + "WHERE venta_id = ?1",nativeQuery=true)
    public Double AllCount(int id);
    
    @Query(value = "SELECT SUM(vd.total) "
            + "FROM ventadetalle vd "
            + "JOIN venta v ON vd.venta_id = v.id "
            + "WHERE v.estado = 'ACTIVO'", nativeQuery = true)
    public Double getAllSalesForAllVentas();

}
