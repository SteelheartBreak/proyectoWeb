package com.musicalist.intermediator.intermediator.Modelo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Cancion {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String nombreArtista;

    private String nombreAlbum;

    private String imagenURL;

    @ManyToOne
    private Genero genero;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "canciones_like", joinColumns = @JoinColumn(name = "cancion_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> votantes = new ArrayList<>();

    public Cancion() {
    }

    public Cancion(String nombre, String nombreArtista, String nombreAlbum, String imagenURL) {
        this.nombre = nombre;
        this.nombreArtista = nombreArtista;
        this.nombreAlbum = nombreAlbum;
        this.imagenURL = imagenURL;
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

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Usuario> getVotantes() {
        return votantes;
    }

    public void setVotantes(List<Usuario> votantes) {
        this.votantes = votantes;
    }
}