package com.musicalist.intermediator.intermediator.Repositorio;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CancionRepositorio extends CrudRepository<Cancion, Long>{

    boolean existsByNombre(String nombre);

    Optional<Cancion> findByNombre(String nombre);
    Optional<Cancion> findByNombreArtista(String NombreArtista);

    

}
