package com.musicalist.intermediator.intermediator.DTO;

import java.util.List;

public class CancionDTO {
    private Integer id;
    private String nombre;
    private String nombreArtista;
    private String nombreAlbum;
    private String imagenURL;
    private GeneroDTO genero;
    private List<UsuarioDTO> votantes;

    public CancionDTO() {
    }

    public CancionDTO(Integer id, String nombre, String nombreArtista, String nombreAlbum, String imagenURL) {
        this.id = id;
        this.nombre = nombre;
        this.nombreArtista = nombreArtista;
        this.nombreAlbum = nombreAlbum;
        this.imagenURL = imagenURL;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public void setGenero(GeneroDTO genero) {
        this.genero = genero;
    }

    public void setVotantes(List<UsuarioDTO> votantes) {
        this.votantes = votantes;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public GeneroDTO getGenero() {
        return genero;
    }

    public List<UsuarioDTO> getVotantes() {
        return votantes;
    }
}
