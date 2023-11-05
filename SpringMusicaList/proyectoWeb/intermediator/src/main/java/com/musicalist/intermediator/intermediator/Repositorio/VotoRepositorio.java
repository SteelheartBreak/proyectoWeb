package com.musicalist.intermediator.intermediator.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicalist.intermediator.intermediator.Modelo.Canciones_like;

public interface VotoRepositorio extends JpaRepository<Canciones_like, Long> {

    List<Canciones_like> findByUsuarioId(Long id);

}
