package com.musicalist.intermediator.intermediator.Repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.musicalist.intermediator.intermediator.Modelo.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByCorreo(String nombre);
    boolean existsBycorreo(String correo);

}
