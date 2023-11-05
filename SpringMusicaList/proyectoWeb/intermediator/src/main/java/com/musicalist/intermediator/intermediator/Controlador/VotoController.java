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
import com.musicalist.intermediator.intermediator.Repositorio.VotoRepositorio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    VotoRepositorio votoRepositorio;

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Borrar una cancion por id")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        List<Canciones_like> Votos=votoRepositorio.findByUsuarioId(id);
        votoRepositorio.deleteAll(Votos);
        return new ResponseEntity<>("Cliente eliminado", HttpStatus.NO_CONTENT);
    }

}
