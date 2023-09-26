package com.musicalist.intermediator.intermediator.Controlador;

import com.musicalist.intermediator.intermediator.Modelo.*;
import com.musicalist.intermediator.intermediator.Repositorio.CancionRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.UsuarioRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.VotoRepositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class VotoController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CancionRepositorio cancionRepositorio;
    @Autowired
    private VotoRepositorio votoRepositorio;

    @CrossOrigin
    @PostMapping(value = "/Voto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> votarCancion(@RequestBody Voto voto) {
        System.out.println(voto.getCancion());
        if (voto.getCancion() == null || voto.getUsuario() == null) {
            return ResponseEntity.badRequest().body("Debe incluir ambos ID");
        }

        Optional<Cancion> cancion = cancionRepositorio.findById(voto.getCancion().getId());
        Optional<Usuario> usuario = usuarioRepositorio.findById(voto.getUsuario().getId());

        if (cancion.isPresent() && usuario.isPresent()) {
            Cancion guardarCancion = cancion.get();
            Usuario guardarUsuario = usuario.get();
            
            guardarCancion.addUsuario(guardarUsuario);
            cancionRepositorio.save(guardarCancion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Voto Agregado de manera exitosa");
        } else {
            return ResponseEntity.badRequest()
                    .body("El voto no es valido, no existe uno de los dos parametros");
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/Voto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> BorrarVoto(@RequestBody Voto voto) {
        if (voto.getCancion() == null || voto.getUsuario()==null) {
            return ResponseEntity.badRequest().body("Debe incluir una cancion y un usuario");
        }
        if(voto.getCancion().getId()==null||voto.getUsuario().getId()==null)
        {
           return ResponseEntity.badRequest().body("Debe incluir el ID de la cancion y el usuario"); 
        }
        Optional<Voto> validarVoto = votoRepositorio.findByCancionIdAndUsuarioId(voto.getCancion().getId(),voto.getUsuario().getId());
        if (validarVoto.isPresent()) {
            Voto votico = validarVoto.get();
            Optional<Cancion> cancion = cancionRepositorio.findById(votico.getCancion().getId());
            Optional<Usuario> usuario = usuarioRepositorio.findById(votico.getUsuario().getId());
            Cancion guardarCancion = cancion.get();
            Usuario guardarUsuario = usuario.get();
            guardarCancion.removeUsuario(guardarUsuario);
            cancionRepositorio.save(guardarCancion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Voto Borrado de manera exitosa");
        }
        return ResponseEntity.badRequest().body("No se encontro el Voto");
    }
}
