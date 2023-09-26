package com.musicalist.intermediator.intermediator.Repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.musicalist.intermediator.intermediator.Modelo.Voto;

public interface VotoRepositorio extends CrudRepository<Voto, Long> {

    Optional<Voto> findById(Long Id);

    Optional<Voto> findByCancionIdAndUsuarioId(Long cancionId, Long usuarioId);
}
