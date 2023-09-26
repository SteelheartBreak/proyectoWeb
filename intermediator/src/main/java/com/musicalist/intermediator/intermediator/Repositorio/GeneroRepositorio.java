package com.musicalist.intermediator.intermediator.Repositorio;
import com.musicalist.intermediator.intermediator.Modelo.Genero;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface GeneroRepositorio extends CrudRepository<Genero, Long>{
    boolean existsByNombre(String nombre);
    Optional<Genero> findByNombre(String nombre);
    Optional<Genero> findById(Long id);
    boolean existsById(Genero genero);
}