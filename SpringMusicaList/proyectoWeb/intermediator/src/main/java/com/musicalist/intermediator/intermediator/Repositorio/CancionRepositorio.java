package com.musicalist.intermediator.intermediator.Repositorio;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CancionRepositorio extends JpaRepository<Cancion, Long>{

    Cancion findByNombre(String nombre);
    @Query("SELECT c FROM Cancion c WHERE c.genero.id = :id")
    List<Cancion> findByGenero(@Param("id") Long id);
    Optional<Cancion> findById(Integer id);


}
