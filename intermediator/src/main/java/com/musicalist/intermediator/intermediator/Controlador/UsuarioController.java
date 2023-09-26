package com.musicalist.intermediator.intermediator.Controlador;

import com.musicalist.intermediator.intermediator.Extra.VerificarCorreo;
import com.musicalist.intermediator.intermediator.Modelo.Usuario;
import com.musicalist.intermediator.intermediator.Repositorio.UsuarioRepositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

public class UsuarioController {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @CrossOrigin
    @PostMapping(value = "/Usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El Nombre del usuario no puede estar vacio");
        }
        if (usuario.getContrasenia() == null || usuario.getContrasenia().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("la contraseña no puede estar vacia");
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El usario debe tener un correo");
        }
        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El usario debe tener un Rol");
        }
        if (usuarioRepositorio.existsBycorreo(usuario.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un usuario con ese correo.");
        }
        if (!VerificarCorreo.isEmailValid(usuario.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El correo ingresado no es valido");
        }
        if (!usuario.getRol().toLowerCase().equals("admin") && !usuario.getRol().toLowerCase().equals("user")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El rol no es valido");
        }
        usuarioRepositorio.save(usuario);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario creado de manera exitosa");
    }

    @CrossOrigin
    @GetMapping(value = "/Usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getId() == null && (usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty())) {
            return ResponseEntity.badRequest().body("Debe haber al menos un parametro de busqueda(Id o correo)");
        }
        Optional<Usuario> resultado;
        if (usuario.getId() != null) {
            resultado = usuarioRepositorio.findById(usuario.getId());
        } else {
            resultado = usuarioRepositorio.findByCorreo(usuario.getCorreo());
        }
        if (resultado.isPresent()) {
            Usuario finalUsuario = resultado.get();

            if ((usuario.getId() != null && !usuario.getId().equals(finalUsuario.getId())) ||
                    (usuario.getCorreo() != null && !usuario.getCorreo().equals(finalUsuario.getCorreo()))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado una condicion que satisfaga ambas condiciones");
            }
            return ResponseEntity.ok(finalUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Usuario no ha sido encontrado.");
        }

    }

    @CrossOrigin
    @PutMapping(value = "/Usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getId() == null || (((usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()))
                && (usuario.getContrasenia() == null || usuario.getContrasenia().trim().isEmpty())
                && (usuario.getRol() == null || usuario.getRol().trim().isEmpty())
                && (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()))) {
            return ResponseEntity.badRequest()
                    .body("Debe proporcionar el ID del usuario a actualizar y al menos un parametro");
        }

        Optional<Usuario> resultado = usuarioRepositorio.findById(usuario.getId());
        if (resultado.isPresent()) {
            Usuario finalUsuario = resultado.get();

            if (usuario.getNombre() != null && !usuario.getNombre().trim().isEmpty()) {
                finalUsuario.setNombre(usuario.getNombre());
            }
            if (usuario.getContrasenia() != null && !usuario.getContrasenia().trim().isEmpty()) {
                finalUsuario.setContrasenia(usuario.getContrasenia());
            }
            if (usuario.getCorreo() != null && !usuario.getCorreo().trim().isEmpty()) {
                Optional <Usuario> validarcorreo=usuarioRepositorio.findByCorreo(usuario.getCorreo());
                if (VerificarCorreo.isEmailValid(usuario.getCorreo())) {
                    if(validarcorreo.isPresent())
                    {
                        Usuario mismo=validarcorreo.get();
                        if(mismo.getId()!=usuario.getId())
                        {
                            ResponseEntity.status(HttpStatus.CONFLICT).body("El Correo Ya esta registrado");
                        }
                    }
                    finalUsuario.setCorreo(usuario.getCorreo());
                } else {
                    ResponseEntity.status(HttpStatus.CONFLICT).body("El Correo no es válido");
                }
            }
            if (usuario.getRol() != null && !usuario.getRol().trim().isEmpty()) {
                if (usuario.getRol().toLowerCase().equals("admin") || usuario.getRol().toLowerCase().equals("user")) {
                    finalUsuario.setRol(usuario.getRol());
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El rol no es válido");
                }
            }

            usuarioRepositorio.save(finalUsuario);
            return ResponseEntity.ok("Usuario actualizado exitosamente.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un usuario con ese ID");
    }

    @CrossOrigin
    @DeleteMapping(value = "/Usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> borrarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getId() == null && usuario.getCorreo() == null) {
            return org.springframework.http.ResponseEntity.badRequest()
                    .body("Debe haber al menos un parametro de busqueda");
        }
        Optional<Usuario> resultado;
        if (usuario.getId() != null) {
            resultado = usuarioRepositorio.findById(usuario.getId());
        } else {
            resultado = usuarioRepositorio.findByCorreo(usuario.getCorreo());
        }
        if (resultado.isPresent()) {
            Usuario finalUsuario = resultado.get();

            if ((usuario.getId() != null && !usuario.getId().equals(finalUsuario.getId())) ||
                    (usuario.getCorreo() != null && !usuario.getCorreo().equals(finalUsuario.getCorreo()))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado una condicion que satisfaga ambas condiciones");
            }
            usuarioRepositorio.delete(finalUsuario);
            return ResponseEntity.ok("Borrado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El susuario no ha sido encontrado.");
        }

    }
}