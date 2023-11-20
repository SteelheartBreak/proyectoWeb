package com.musicalist.intermediator.intermediator.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musicalist.intermediator.intermediator.Modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    Usuario findById(Integer id);
}
