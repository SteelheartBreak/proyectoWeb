package com.musicalist.intermediator.intermediator.Repositorio;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CancionRepositorio extends JpaRepository<Cancion, Long>{

    Cancion findByNombre(String nombre);
    // busca canciones por su GENERO_ID
    @Query("SELECT c FROM Cancion c WHERE c.genero.id = :id")
    List<Cancion> findByGenero(@Param("id") Long id);


    void deleteById(Integer id);


}
