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
    @GetMapping("/find/nombre/{nombre}")
    public ResponseEntity<Genero> getGeneroNombre(@PathVariable String nombre) {
        Genero genero=generoRepositorio.findBynombre(nombre);
        return ResponseEntity.ok(genero);

    }
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Genero> getGeneroID(@PathVariable Integer id) {
         Genero genero=generoRepositorio.findById(id);
        return ResponseEntity.ok(genero);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
        if (id != null) {
            Genero genero = generoRepositorio.findById(id);
            generoRepositorio.delete(genero);
            return new ResponseEntity<>("Genero eliminado", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("ID de genero nulo", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Genero> agregar(@RequestBody Genero genero) {
        if (genero == null) {
            ResponseEntity<Genero> response = new ResponseEntity<>(genero, HttpStatus.BAD_REQUEST);
            return response;
        }
        generoRepositorio.save(genero);
        ResponseEntity<Genero> response = new ResponseEntity<>(genero, HttpStatus.CREATED);
        return response;
    }
    @PutMapping("/modificar")
    public ResponseEntity<Genero> modificar (@RequestBody Genero genero)
    {
        Optional<Genero> generoEncontrada = generoRepositorio.findById(genero.getId());
        Genero Final=generoEncontrada.get();
        Final.setNombre(genero.getNombre());
        generoRepositorio.save(Final);
        return ResponseEntity.ok(Final);
    }
}
