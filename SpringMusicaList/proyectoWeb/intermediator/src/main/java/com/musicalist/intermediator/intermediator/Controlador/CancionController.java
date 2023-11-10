package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;
import com.musicalist.intermediator.intermediator.Repositorio.CancionRepositorio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cancion")
@CrossOrigin(origins = "http://localhost:4200")
public class CancionController {

    @Autowired
    CancionRepositorio cancionRepositorio;

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las canciones")
    public ResponseEntity<List<Cancion>> getAll() {
        List<Cancion> canciones = cancionRepositorio.findAll();
        return new ResponseEntity<>(canciones, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Obtener una canción por id")
    public ResponseEntity<Cancion> findById(@PathVariable Long id) {
        Optional<Cancion> cancionEncontrada = cancionRepositorio.findById(id);
        if (cancionEncontrada.isPresent()) {
            return new ResponseEntity<>(cancionEncontrada.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findName/{nombre}")
    @Operation(summary = "Obtener una canción por Nombre")
    public ResponseEntity<Cancion> findByName(@PathVariable String nombre) {
        Cancion cancion = cancionRepositorio.findByNombre(nombre);
        return ResponseEntity.ok(cancion);
    }

    @GetMapping("/findGender/{id}")
    @Operation(summary = "Obtener canciones por género")
    public ResponseEntity<List<Cancion>> findByGender(@PathVariable Long id) {
        List<Cancion> canciones = cancionRepositorio.findByGenero(id);
        return new ResponseEntity<>(canciones, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar una canción por id")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        if (id != null) {
            Optional<Cancion> cancionEncontrada = cancionRepositorio.findById(id);
            if (cancionEncontrada.isPresent()) {
                cancionRepositorio.delete(cancionEncontrada.get());
                return new ResponseEntity<>("Canción eliminada", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Canción no encontrada", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("ID de Canción nulo", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar una canción por id")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Cancion updatedCancion) {
        Optional<Cancion> cancionEncontrada = cancionRepositorio.findById(id);
        if (cancionEncontrada.isPresent()) {
            Cancion cancion = cancionEncontrada.get();
            cancion.setNombre(updatedCancion.getNombre());
            cancionRepositorio.save(cancion);
            return new ResponseEntity<>("Canción actualizada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Canción no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Agregar una canción")
    public ResponseEntity<Cancion> add(@RequestBody Cancion cancion) {
        if (cancion != null) {
            cancionRepositorio.save(cancion);
            return new ResponseEntity<>(cancion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(cancion, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<Cancion> modificar (@RequestBody Cancion cancion)
    {
        Optional<Cancion> cancionEncontrada = cancionRepositorio.findById(cancion.getId());
        Cancion Final=cancionEncontrada.get();
        Final.setNombre(cancion.getNombre());
        Final.setNombreAlbum(cancion.getNombreAlbum());
        Final.setNombreArtista(cancion.getNombreArtista());
        Final.setImagenURL(cancion.getImagenURL());
        Final.setGenero(cancion.getGenero());
        cancionRepositorio.save(Final);
        return ResponseEntity.ok(Final);
    }
}
