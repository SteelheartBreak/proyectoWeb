package com.musicalist.intermediator.intermediator.Repositorio;
import com.musicalist.intermediator.intermediator.Modelo.Genero;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepositorio extends JpaRepository<Genero, Long>{

    void deleteById(int id);

    Optional<Genero> findById(Integer id);

    Genero findBynombre(String nombre);

}