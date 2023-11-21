package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.musicalist.intermediator.intermediator.DTO.CancionDTO;
import com.musicalist.intermediator.intermediator.Servicios.CancionService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cancion")
@CrossOrigin(origins = "http://localhost:4200")
public class CancionController {

    @Autowired
    CancionService cancionService;

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las canciones")
    public ResponseEntity<List<CancionDTO>> getAll() {
        List<CancionDTO> canciones = cancionService.Todos();
        return new ResponseEntity<>(canciones, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Obtener una canción por id")
    public ResponseEntity<CancionDTO> findById(@PathVariable Integer id) {
        CancionDTO cancionEncontrada = cancionService.BuscarID(id);
        return new ResponseEntity<>(cancionEncontrada, HttpStatus.OK);
    }

    @GetMapping("/findName/{nombre}")
    @Operation(summary = "Obtener una canción por Nombre")
    public ResponseEntity<CancionDTO> findByName(@PathVariable String nombre) {
        CancionDTO cancion =cancionService.BuscarNombre(nombre);
        return ResponseEntity.ok(cancion);
    }

    @GetMapping("/findGender/{id}")
    @Operation(summary = "Obtener canciones por género")
    public ResponseEntity<List<CancionDTO>> findByGender(@PathVariable Integer id) {
        List<CancionDTO> canciones = cancionService.BuscarGenero(id);
        return new ResponseEntity<>(canciones, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar una canción por id")
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
        if (id != null) {
            CancionDTO cancionEncontrada = cancionService.BuscarID(id);
            if (cancionEncontrada!=null) {
                cancionService.borrar(cancionEncontrada);
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
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody CancionDTO updatedCancion) {
        CancionDTO cancionEncontrada = cancionService.BuscarID(id);
        if (cancionEncontrada!=null) {
            cancionEncontrada.setNombre(updatedCancion.getNombre());
            cancionService.guardar(cancionEncontrada);
            return new ResponseEntity<>("Canción actualizada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Canción no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Agregar una canción")
    public ResponseEntity<CancionDTO> add(@RequestBody CancionDTO cancion) {
        if (cancion != null) {
            cancionService.guardar(cancion);
            return new ResponseEntity<>(cancion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(cancion, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<CancionDTO> modificar(@RequestBody CancionDTO cancion) {
        CancionDTO Final = cancionService.BuscarID(cancion.getId());
        Final.setNombre(cancion.getNombre());
        Final.setNombreAlbum(cancion.getNombreAlbum());
        Final.setNombreArtista(cancion.getNombreArtista());
        Final.setImagenURL(cancion.getImagenURL());
        Final.setGenero(cancion.getGenero());
        cancionService.guardar(Final);
        return ResponseEntity.ok(Final);
    }
}
