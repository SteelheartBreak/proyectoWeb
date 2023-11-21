package com.musicalist.intermediator.intermediator.Servicios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musicalist.intermediator.intermediator.DTO.UsuarioDTO;
import com.musicalist.intermediator.intermediator.Modelo.Usuario;
import com.musicalist.intermediator.intermediator.Repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {
    private final UsuarioRepositorio usuarioRepositorio;
    

    @Autowired
    public UsuarioService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public UsuarioDTO BuscarCorreo(String correo) {
        Usuario encontrado = usuarioRepositorio.findByCorreo(correo);
        return convertiraDTO(encontrado);
    }

    public UsuarioDTO BuscarID(Integer id) {
        Usuario encontrado = usuarioRepositorio.findById(id);
        return convertiraDTO(encontrado);
    }

    public void GuardarUsuario(UsuarioDTO user) {
        usuarioRepositorio.save(convertiraEntidad(user));
    }

    public List<UsuarioDTO> Todos() {
        List<UsuarioDTO> Usuarios = new ArrayList<>();
        for (Usuario user : usuarioRepositorio.findAll()) {
            Usuarios.add(convertiraDTO(user));
        }
        return Usuarios;
    }

    public void borrar(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertiraEntidad(usuarioDTO);
        usuarioRepositorio.delete(usuario);
    }

    public UsuarioDTO convertiraDTO(Usuario user) {
        if (user == null) {
            return null;
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(user.getId());
        usuarioDTO.setNombre(user.getNombre());
        usuarioDTO.setCorreo(user.getCorreo());
        usuarioDTO.setRol(user.getRol());
        usuarioDTO.setContrasenia(user.getContrasenia());
        return usuarioDTO;
    }

    public Usuario convertiraEntidad(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;

        }
        Usuario user = new Usuario();
        user.setId(usuarioDTO.getId());
        user.setNombre(usuarioDTO.getNombre());
        user.setRol(usuarioDTO.getRol());
        user.setContrasenia(usuarioDTO.getContrasenia());
        user.setCorreo(usuarioDTO.getCorreo());
        return user;
    }
}