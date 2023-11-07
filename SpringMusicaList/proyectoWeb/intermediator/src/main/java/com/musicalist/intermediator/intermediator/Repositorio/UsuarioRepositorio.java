package com.musicalist.intermediator.intermediator.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.musicalist.intermediator.intermediator.Modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Usuario findByCorreo(String correo);

    Usuario findById(Integer id);
}
