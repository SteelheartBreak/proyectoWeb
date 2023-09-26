package com.musicalist.intermediator.intermediator.Controlador;

import com.musicalist.intermediator.intermediator.Modelo.Genero;
import com.musicalist.intermediator.intermediator.Repositorio.GeneroRepositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class GeneroController {

    @Autowired
    private GeneroRepositorio generoRepositorio;

    @CrossOrigin
    @PostMapping(value = "/Genero", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertarGenero(@RequestBody Genero genero) {
        if (generoRepositorio.existsByNombre(genero.getNombre())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ese género ya existe en la base de datos.");
        }

        if (genero.getNombre() == null || genero.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del género no puede estar vacío.");
        }

        generoRepositorio.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body("Género agregado exitosamente.");
    }

    @CrossOrigin
    @GetMapping(value = "/Genero", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarGenero(@RequestBody Genero genero) {
        if (genero.getId() == null && genero.getNombre() == null) {
            return ResponseEntity.badRequest().body("Debe haber al menos un parametro de busqueda");
        }
        Optional<Genero> resultado;
        if (genero.getId() != null) {
            resultado = generoRepositorio.findById(genero.getId());
        } else {
            resultado = generoRepositorio.findByNombre(genero.getNombre());
        }
        if (resultado.isPresent()) {
            Genero finalGenero = resultado.get();

            if ((genero.getId() != null && !genero.getId().equals(finalGenero.getId())) ||
                    (genero.getNombre() != null && !genero.getNombre().equals(finalGenero.getNombre()))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado una condicion que satisfaga ambas condiciones");
            }
            return ResponseEntity.ok(finalGenero);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El género no ha sido encontrado.");
        }
    }

    @CrossOrigin
    @PutMapping(value = "/Genero", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarGenero(@RequestBody Genero genero) {
        if (genero.getId() == null || genero.getNombre() == null) {
            return ResponseEntity.badRequest().body("Debe proporcionar el ID a buscar y el nombre a cambiar");
        }
        Optional<Genero> resultado = generoRepositorio.findById(genero.getId());
        if (resultado.isPresent()) {
            Optional<Genero> verificar = generoRepositorio.findByNombre(genero.getNombre());
            if (!verificar.isPresent()) {
                Genero finalGenero = resultado.get();
                finalGenero.setNombre(genero.getNombre());
                generoRepositorio.save(finalGenero);
                return ResponseEntity.ok().body("Género actualizado exitosamente.");
            }
            return ResponseEntity.badRequest().body("El nombre ya existe en otro registro");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un registro con ese ID");
    }

    @CrossOrigin
    @DeleteMapping(value = "/Genero", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> borrarGenero(@RequestBody Genero genero) {
        if (genero.getId() == null && genero.getNombre() == null) {
            return ResponseEntity.badRequest().body("Debe haber al menos un parametro de busqueda");
        }
        Optional<Genero> resultado;
        if (genero.getId() != null) {
            resultado = generoRepositorio.findById(genero.getId());
        } else {
            resultado = generoRepositorio.findByNombre(genero.getNombre());
        }
        if (resultado.isPresent()) {
            Genero finalGenero = resultado.get();

            if ((genero.getId() != null && !genero.getId().equals(finalGenero.getId())) ||
                    (genero.getNombre() != null && !genero.getNombre().equals(finalGenero.getNombre()))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado una condicion que satisfaga ambas condiciones");
            }
            generoRepositorio.delete(finalGenero);
            return ResponseEntity.ok("Borrado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El género no ha sido encontrado.");
        }
    }
}
