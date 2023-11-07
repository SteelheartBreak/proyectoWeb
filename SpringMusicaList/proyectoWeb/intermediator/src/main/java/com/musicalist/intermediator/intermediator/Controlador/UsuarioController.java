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
    @Autowired
    Canciones_likeController voto;

    @CrossOrigin
    @GetMapping("/find/correo/{correo}")
    @Operation(summary = "Obtener un usuario por correo")
    public ResponseEntity<Usuario> findByCorreo(@PathVariable String correo) {
        Usuario usuarioEncontrada = usuarioRepositorio.findByCorreo(correo);
        return ResponseEntity.ok(usuarioEncontrada);
    }

    @Operation(summary = "Agregar un Usuario")
    @PostMapping("/add")
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
            Usuario usuario = usuarioRepositorio.findById(id);
            voto.deleteByUsuarioId(usuario.getId());
            usuarioRepositorio.delete(usuario);
            return new ResponseEntity<>("Usuario eliminado", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("ID de usuario nulo", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/id/{id}")
    @Operation(summary = "Obtener un usuario por id")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        Usuario usuarioEncontrada = usuarioRepositorio.findById(id);
        return ResponseEntity.ok(usuarioEncontrada);
    }

    @PutMapping("/modificar")
    public ResponseEntity<Usuario> modificar (@RequestBody Usuario user)
    {
        Optional<Usuario> usuarioEncontrada = usuarioRepositorio.findById(user.getId());
        Usuario Final=usuarioEncontrada.get();
        Final.setNombre(user.getNombre());
        Final.setCorreo(user.getCorreo());
        Final.setContrasenia(user.getContrasenia());
        Final.setRol(user.getRol());
        usuarioRepositorio.save(Final);
        return ResponseEntity.ok(Final);
    }
}