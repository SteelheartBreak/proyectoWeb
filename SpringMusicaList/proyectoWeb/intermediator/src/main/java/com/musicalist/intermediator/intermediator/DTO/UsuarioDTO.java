package com.musicalist.intermediator.intermediator.DTO;

import java.util.List;

public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String contrasenia;
    private String correo;
    private String rol;
    private List<CancionDTO> cancionesLiked;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id, String nombre, String contrasenia, String correo, String rol, List<CancionDTO> cancionesLiked) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
        this.cancionesLiked = cancionesLiked;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<CancionDTO> getCancionesLiked() {
        return cancionesLiked;
    }

    public void setCancionesLiked(List<CancionDTO> cancionesLiked) {
        this.cancionesLiked = cancionesLiked;
    }
}
