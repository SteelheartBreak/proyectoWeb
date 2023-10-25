package com.musicalist.intermediator.intermediator.Modelo;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genero {

    @Id
    @GeneratedValue
    private Long id;

    
    private String nombre;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Cancion> generoCanciones = new ArrayList<>();

    public Genero() {
    }

    public Genero(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getGeneroCanciones() {
        return generoCanciones;
    }

    public void setGeneroCanciones(List<Cancion> generoCanciones) {
        this.generoCanciones = generoCanciones;
    }

    
}
