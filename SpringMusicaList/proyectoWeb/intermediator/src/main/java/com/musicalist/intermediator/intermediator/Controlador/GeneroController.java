package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;

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
            generoRepositorio.deleteById(id);
            return new ResponseEntity<>("Usuario eliminado", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("ID de usuario nulo", HttpStatus.BAD_REQUEST);
        }
    }

}
