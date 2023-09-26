package com.musicalist.intermediator.intermediator.Controlador;

import com.musicalist.intermediator.intermediator.Modelo.Cancion;
import com.musicalist.intermediator.intermediator.Modelo.Genero;
import com.musicalist.intermediator.intermediator.Repositorio.CancionRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.GeneroRepositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

public class CancionController {
    @Autowired
    private CancionRepositorio cancionRepositorio;
    @Autowired
    private GeneroRepositorio generoRepositorio;

    @CrossOrigin
    @PostMapping(value = "/Cancion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertarCancion(@RequestBody Cancion cancion) {
        if (cancion.getNombre() == null || cancion.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El Nombre de la Cancion no puede estar vacio");
        }
        if (cancion.getNombreArtista() == null || cancion.getNombreArtista().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El Nombre del artista no puede estar vacio");
        }
        if (cancion.getGenero() == null) {
            return ResponseEntity.badRequest().body("La cancion debe tener un genero");
        }
        if (cancionRepositorio.existsByNombre(cancion.getNombre())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ese Nombre de cancion ya existe en la base de datos.");
        }
        if (generoRepositorio.existsById(cancion.getGenero().getId())) {
            cancionRepositorio.save(cancion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Canción agregada exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe el genero de la canción que busca agregar");
    }

    @CrossOrigin
    @GetMapping(value = "/Cancion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarCancion(@RequestBody Cancion cancion) {
        if (cancion.getId() == null && (cancion.getNombre() == null || cancion.getNombre().trim().isEmpty())) {
            return ResponseEntity.badRequest().body("Debe haber al menos un parametro de busqueda(Id o Nombre)");
        }
        Optional<Cancion> resultado;
        if (cancion.getId() != null) {
            resultado = cancionRepositorio.findById(cancion.getId());
        } else {
            resultado = cancionRepositorio.findByNombre(cancion.getNombre());
        }
        if (resultado.isPresent()) {
            
            Cancion finalCancion = resultado.get();


            if ((cancion.getId() != null && !cancion.getId().equals(finalCancion.getId())) ||
                    (cancion.getNombre() != null && !cancion.getNombre().equals(finalCancion.getNombre()))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado una condicion que satisfaga ambas condiciones");
            }
            return ResponseEntity.ok(finalCancion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El género no ha sido encontrado.");
        }

    }

    @CrossOrigin
    @PutMapping(value = "/Cancion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizarCancion(@RequestBody Cancion cancion) {
        if (cancion.getId() == null
                && (cancion.getGenero() == null || (cancion.getNombre() == null || cancion.getNombre().trim().isEmpty())
                        || (cancion.getNombreArtista() != null || cancion.getNombre().trim().isEmpty()))) {
            return ResponseEntity.badRequest().body(
                    "Debe proporcionar el ID y al menos un parámetro a cambiar (Nombre, generoID o Nombre del artista)");
        }
        Optional<Cancion> resultado = cancionRepositorio.findById(cancion.getId());
        if (resultado.isPresent()) {
            Cancion finalCancion = resultado.get();
            if (cancion.getNombre() != null) {
                if (!cancion.getNombre().trim().isEmpty()) {
                    Optional<Cancion> verificar = cancionRepositorio.findByNombre(cancion.getNombre());
                    if (verificar.isPresent()) {
                        return ResponseEntity.badRequest().body("El nombre ya existe en otro registro");
                    }
                    finalCancion.setNombre(cancion.getNombre());
                }
            }
            if (cancion.getGenero().getId() != null) {
                Optional<Genero> verificarGenero = generoRepositorio.findById(cancion.getGenero().getId());
                if (!verificarGenero.isPresent()) {
                    return ResponseEntity.badRequest().body("No hay genero asociado a ese id");
                }
                Genero finalGenero = verificarGenero.get();
                finalCancion.setGenero(finalGenero);

            }
            if (cancion.getNombreArtista() != null) {
                if(!cancion.getNombreArtista().trim().isEmpty())
                {
                finalCancion.setNombreArtista(cancion.getNombreArtista());
                }
            }
            cancionRepositorio.save(finalCancion);
            return ResponseEntity.ok().body("Canción actualizada exitosamente.");

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un registro con ese ID");

    }

    @CrossOrigin
    @DeleteMapping(value = "/Cancion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> borrarCancion(@RequestBody Cancion cancion) {
        if (cancion.getId() == null && cancion.getNombre() == null) {
            return ResponseEntity.badRequest().body("Debe haber al menos un parametro de busqueda");
        }
        Optional<Cancion> resultado;
        if (cancion.getId() != null) {
            resultado = cancionRepositorio.findById(cancion.getId());
        } else {
            resultado = cancionRepositorio.findByNombre(cancion.getNombre());
        }
        if (resultado.isPresent()) {
            Cancion finalCancion = resultado.get();

            if ((cancion.getId() != null && !cancion.getId().equals(finalCancion.getId())) ||
                    (cancion.getNombre() != null && !cancion.getNombre().equals(finalCancion.getNombre()))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado una condicion que satisfaga ambas condiciones");
            }
            cancionRepositorio.delete(finalCancion);
            return ResponseEntity.ok("Borrado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La canción no ha sido encontrada.");
        }

    }
}
