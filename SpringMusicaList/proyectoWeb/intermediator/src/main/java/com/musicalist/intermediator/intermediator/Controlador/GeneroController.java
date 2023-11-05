package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.musicalist.intermediator.intermediator.Modelo.Genero;
import com.musicalist.intermediator.intermediator.Repositorio.GeneroRepositorio;

@RestController
@RequestMapping("/genero")
@CrossOrigin(origins = "http://localhost:4200")
public class GeneroController {

    @Autowired
    GeneroRepositorio generoRepositorio;

    @GetMapping("/all")
    public ResponseEntity<List<Genero>> getAll() {
        List<Genero> generos = generoRepositorio.findAll();

        ResponseEntity<List<Genero>> response = new ResponseEntity<>(generos, HttpStatus.OK);
        return response;

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
        if (id != null) {
            Optional<Genero> generoEncontrado = generoRepositorio.findById(id);
            Genero genero = generoEncontrado.get();
            generoRepositorio.delete(genero);
            return new ResponseEntity<>("Genero eliminado", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("ID de genero nulo", HttpStatus.BAD_REQUEST);
        }
    }

}
