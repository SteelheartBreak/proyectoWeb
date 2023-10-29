package com.musicalist.intermediator.intermediator.Repositorio;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepositorio extends JpaRepository<Cancion, Long>{

    Cancion findByNombre(String nombre);

}
