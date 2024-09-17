package com.example.Ventas3718.Repositorios;
import com.example.Ventas3718.Clases.Compradetalle;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompradetalle extends CrudRepository<Compradetalle,Integer> {
        
    @Query(value="SELECT SUM(total) as monto_total "
            + "FROM compradetalle "
            + "WHERE compra_id = ?1",nativeQuery=true)
    public Double AllSales(int id);
    
    List<Compradetalle> findByCompraId(int id);
    
    @Query(value="SELECT COUNT(*) as cant_total "
            + "FROM compradetalle "
            + "WHERE compra_id = ?1",nativeQuery=true)
    public Double AllCount(int id);
    
    @Query(value = "SELECT SUM(vd.total) "
            + "FROM compradetalle vd "
            + "JOIN compra v ON vd.compra_id = v.id ", nativeQuery = true)
    public Double getAllSalesForAllCompras();

}
