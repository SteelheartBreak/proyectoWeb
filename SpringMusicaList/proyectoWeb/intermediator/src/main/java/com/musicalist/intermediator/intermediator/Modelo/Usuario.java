package com.musicalist.intermediator.intermediator.Modelo;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;



@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String contrasenia;
    private String correo;
    private String rol;

    @ManyToMany
    @JoinTable(name = "canciones_like", joinColumns = @JoinColumn(name = "cancion_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Cancion> cancionesLiked = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombre, String contrasenia, String correo, String rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia=contrasenia;
    }

    public void setCorreo(String correo) {
        this.correo=correo;
    }

    public void setRol(String rol) {
        this.rol=rol;
    }

    public List<Cancion> getCancionesLiked() {
        return cancionesLiked;
    }

    public void setCancionesLiked(List<Cancion> cancionesLiked) {
        this.cancionesLiked = cancionesLiked;
    }

    
}
