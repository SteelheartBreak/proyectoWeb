package com.musicalist.intermediator.intermediator.DTO;

import java.util.List;

public class GeneroDTO {
    private Integer id;
    private String nombre;
    private List<CancionDTO> generoCanciones;

    public GeneroDTO() {
    }

    public GeneroDTO(Integer id, String nombre, List<CancionDTO> generoCanciones) {
        this.id = id;
        this.nombre = nombre;
        this.generoCanciones = generoCanciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CancionDTO> getGeneroCanciones() {
        return generoCanciones;
    }

    public void setGeneroCanciones(List<CancionDTO> generoCanciones) {
        this.generoCanciones = generoCanciones;
    }
}
