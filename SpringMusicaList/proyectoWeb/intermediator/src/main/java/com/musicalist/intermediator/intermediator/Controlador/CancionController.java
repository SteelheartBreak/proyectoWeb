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
    public ResponseEntity<List<Cancion>>  getAll(){
        List<Cancion> canciones = cancionRepositorio.findAll();

        ResponseEntity<List<Cancion>> response = new ResponseEntity<>(canciones, HttpStatus.OK);
        return response;
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Obtener una cancion por id")
    public ResponseEntity<Cancion>  findById(@PathVariable Long id){
        Optional<Cancion> cancionEncontrada = cancionRepositorio.findById(id);
        Cancion cancion=cancionEncontrada.get();
        if(cancion == null){
            ResponseEntity<Cancion> response = new ResponseEntity<>(cancion, HttpStatus.NOT_FOUND);
            return response;
        }
        ResponseEntity<Cancion> response = new ResponseEntity<>(cancion, HttpStatus.OK);
            return response;
    }

    @GetMapping("/findName/{nombre}")
    @Operation(summary = "Obtener una cancion por Nombre")
    public ResponseEntity<Cancion>  findByName(@PathVariable String nombre){
        Cancion cancion =cancionRepositorio.findByNombre(nombre);

        if(cancion == null){
            ResponseEntity<Cancion> response = new ResponseEntity<>(cancion, HttpStatus.NOT_FOUND);
            return response;
        }
        ResponseEntity<Cancion> response = new ResponseEntity<>(cancion, HttpStatus.OK);
            return response;
    }

    @GetMapping("/findGender/{id}")
    @Operation(summary = "Obtener una cancion por genero")
    public ResponseEntity<List<Cancion>>  findByGender(@PathVariable Long id){
        List<Cancion> canciones =cancionRepositorio.findByGenero(id);

        ResponseEntity<List<Cancion>> response = new ResponseEntity<>(canciones, HttpStatus.OK);
        return response;
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Borrar una cancion por id")
    public ResponseEntity<String>  deleteById(@PathVariable Long id){
         cancionRepositorio.deleteById(id);
         return new ResponseEntity<>("Cliente eliminado", HttpStatus.NO_CONTENT);  
    }

    /*
    //esta debe buscar si el genero existe
    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar una cancion por id")
    public ResponseEntity<String>  updateById(@PathVariable Long id, @RequestBody Cancion cancion){
        
        return new ResponseEntity<>("Cancion actualizada correctamente", HttpStatus.OK);
    }*/

    /*
    esta tambien
    @PostMapping("/add")
    @Operation(summary = "Agregar una cancion")
    public ResponseEntity<Cancion>  add(@RequestBody Cancion cancion){
        if(cancion == null){
            ResponseEntity<Cancion> response = new ResponseEntity<>(cancion, HttpStatus.BAD_REQUEST);
            return response;
        }
        ResponseEntity<Cancion> response = new ResponseEntity<>(cancion, HttpStatus.CREATED);
            return response;
    }*/
 
}
