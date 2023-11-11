package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.musicalist.intermediator.intermediator.DTO.GeneroDTO;
import com.musicalist.intermediator.intermediator.Servicios.GeneroService;

@RestController
@RequestMapping("/genero")
@CrossOrigin(origins = "http://localhost:4200")
public class GeneroController {

    @Autowired
    GeneroService generoService;

    @GetMapping("/all")
    public ResponseEntity<List<GeneroDTO>> getAll() {
        List<GeneroDTO> generos = generoService.EncontrarTodos();
        ResponseEntity<List<GeneroDTO>> response = new ResponseEntity<>(generos, HttpStatus.OK);
        return response;

    }

    @GetMapping("/find/nombre/{nombre}")
    public ResponseEntity<GeneroDTO> getGeneroNombre(@PathVariable String nombre) {
        GeneroDTO genero = generoService.BuscarNombre(nombre);
        return ResponseEntity.ok(genero);

    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<GeneroDTO> getGeneroID(@PathVariable Integer id) {
        GeneroDTO genero = generoService.BuscarID(id);
        return ResponseEntity.ok(genero);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
        if (id != null) {
            GeneroDTO genero = generoService.BuscarID(id);
            generoService.borrarGenero(genero);
            return new ResponseEntity<>("Genero eliminado", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("ID de genero nulo", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<GeneroDTO> agregar(@RequestBody GeneroDTO genero) {
        if (genero == null) {
            ResponseEntity<GeneroDTO> response = new ResponseEntity<>(genero, HttpStatus.BAD_REQUEST);
            return response;
        }
        generoService.GuardarGenero(genero);
        ResponseEntity<GeneroDTO> response = new ResponseEntity<>(genero, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/modificar")
    public ResponseEntity<GeneroDTO> modificar(@RequestBody GeneroDTO genero) {
        GeneroDTO Final = generoService.BuscarID(genero.getId());
        Final.setNombre(genero.getNombre());
        generoService.GuardarGenero(Final);
        return ResponseEntity.ok(Final);
    }
}