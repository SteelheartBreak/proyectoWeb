package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.musicalist.intermediator.intermediator.Modelo.Usuario;
import com.musicalist.intermediator.intermediator.Repositorio.UsuarioRepositorio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @CrossOrigin
    @GetMapping("/find/{correo}")
    @Operation(summary = "Obtener un usuario por correo")
    public ResponseEntity<Usuario> findById(@PathVariable String correo) {
        Optional<Usuario> usuarioEncontrada = usuarioRepositorio.findByCorreo(correo);

        if (usuarioEncontrada.isPresent()) {
            Usuario usuario = usuarioEncontrada.get();
            return ResponseEntity.ok(usuario);
        } else {
            Usuario usuarioVacio = new Usuario();
            return ResponseEntity.ok(usuarioVacio);
        }
    }

    @Operation(summary = "Agregar un Usuario")
    public ResponseEntity<Usuario> agregar(@RequestBody Usuario user) {
        if (user == null) {
            ResponseEntity<Usuario> response = new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
            return response;
        }
        usuarioRepositorio.save(user);
        ResponseEntity<Usuario> response = new ResponseEntity<>(user, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los usuarios")
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();

        ResponseEntity<List<Usuario>> response = new ResponseEntity<>(usuarios, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
        if (id != null) {
            Optional<Usuario> usuarioEncontrada = usuarioRepositorio.findById(id);
            Usuario usuario = usuarioEncontrada.get();
            usuarioRepositorio.delete(usuario);
            return new ResponseEntity<>("Usuario eliminado", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("ID de usuario nulo", HttpStatus.BAD_REQUEST);
        }
    }
}