package com.musicalist.intermediator.intermediator.Modelo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
     @JsonIgnore
    private List<Cancion> generoCanciones = new ArrayList<>();

    public Genero() {
        super();
    }

    public Genero(String nombre) {
        super();
        this.nombre = nombre;
    }

    
    public void setId(long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre=nombre;

    }

    public List<Cancion> getGeneroCanciones() {
        return generoCanciones;
    }

    public void addCancion(Cancion cancion) {
        generoCanciones.add(cancion);
        cancion.setGenero(this);
    }
}
