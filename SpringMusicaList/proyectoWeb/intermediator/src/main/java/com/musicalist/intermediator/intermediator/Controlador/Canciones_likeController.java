package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicalist.intermediator.intermediator.Modelo.Canciones_like;
import com.musicalist.intermediator.intermediator.Repositorio.Canciones_likeRepositorio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/voto")
public class Canciones_likeController {

    @Autowired
    Canciones_likeRepositorio votoRepositorio;

    @DeleteMapping("/delete/User/{id}")
    @Operation(summary = "Borrar un voto por id")
    public ResponseEntity<String> deleteByUsuarioId(@PathVariable Long id) {
        List<Canciones_like> Votos=votoRepositorio.findByUsuarioId(id);
        votoRepositorio.deleteAll(Votos);
        return new ResponseEntity<>("Voto eliminado", HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/Cancion/{id}")
    @Operation(summary = "Borrar un voto por id")
    public ResponseEntity<String> deleteByCancionId(@PathVariable Long id) {
        List<Canciones_like> Votos=votoRepositorio.findByCancionId(id);
        votoRepositorio.deleteAll(Votos);
        return new ResponseEntity<>("Voto eliminado", HttpStatus.NO_CONTENT);
    }

}
