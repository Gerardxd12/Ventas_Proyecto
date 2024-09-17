package com.example.Ventas3718.Repositorios;

import com.example.Ventas3718.Clases.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends JpaRepository<Usuario,Integer> {
    
    public Optional<Usuario> findByUser(String username);
    
    @Query(value="SELECT * FROM usuario "
            + "WHERE nombre LIKE %?1% "
            + "OR apellido LIKE %?1% "
            + "OR dni LIKE %?1% "
            + "OR celular LIKE %?1% "
            + "OR email LIKE %?1% "
            + "OR direccion LIKE %?1% "
            + "OR user LIKE %?1%",nativeQuery=true)
    List<Usuario> findForAll(String desc);
    
    @Query(value="SELECT * FROM usuario "
            + "WHERE user = ?1 "
            + "AND password = ?2 ",nativeQuery=true)
    List<Usuario> findForAll(String user,String password);
    
}
