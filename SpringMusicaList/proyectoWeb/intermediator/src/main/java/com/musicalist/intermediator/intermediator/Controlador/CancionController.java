package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.musicalist.intermediator.intermediator.Modelo.Cancion;
import com.musicalist.intermediator.intermediator.Servicios.Cancion.CancionServicio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cancion")
@CrossOrigin(origins = "http://localhost:4200")
public class CancionController {

    @Autowired
    CancionServicio cancionServicio;

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las canciones")
    public ResponseEntity<List<Cancion>>  getAll(){
        List<Cancion> canciones = cancionServicio.findAll();

        ResponseEntity<List<Cancion>> response = new ResponseEntity<>(canciones, HttpStatus.OK);
        return response;
    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Obtener una cancion por id")
    public ResponseEntity<Cancion>  findById(@PathVariable Long id){
        Cancion cancion = cancionServicio.buscarCancion(id);

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
        Cancion cancion = cancionServicio.buscarCancionPorNombre(nombre);

        if(cancion == null){
            ResponseEntity<Cancion> response = new ResponseEntity<>(cancion, HttpStatus.NOT_FOUND);
            return response;
        }
        ResponseEntity<Cancion> response = new ResponseEntity<>(cancion, HttpStatus.OK);
            return response;
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Borrar una cancion por id")
    public ResponseEntity<String>  deleteById(@PathVariable Long id){
         cancionServicio.borrarCancion(id);
         return new ResponseEntity<>("Cliente eliminado", HttpStatus.NO_CONTENT);  
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar una cancion por id")
    public ResponseEntity<String>  updateById(@PathVariable Long id, @RequestBody Cancion cancion){
        cancionServicio.actualizarCancion(cancion);
        return new ResponseEntity<>("Cancion actualizada correctamente", HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(summary = "Agregar una cancion")
    public ResponseEntity<Cancion>  add(@RequestBody Cancion cancion){
        Cancion nuevaCancion = cancionServicio.insertarCancion(cancion);
        if(nuevaCancion == null){
            ResponseEntity<Cancion> response = new ResponseEntity<>(nuevaCancion, HttpStatus.BAD_REQUEST);
            return response;
        }
        ResponseEntity<Cancion> response = new ResponseEntity<>(nuevaCancion, HttpStatus.CREATED);
            return response;
    }
 
}
