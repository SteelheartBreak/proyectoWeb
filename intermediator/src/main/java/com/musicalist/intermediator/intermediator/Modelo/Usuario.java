package com.musicalist.intermediator.intermediator.Modelo;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String contrasenia;
    private String correo;
    private String rol;

    @ManyToMany(mappedBy = "votantes")
     @JsonIgnore
    private List<Cancion> cancionesVotadas;


    public List<Cancion> getCancionesVotadas() {
        return cancionesVotadas;
    }

    public void setCancionesVotadas(List<Cancion> cancionesVotadas) {
        this.cancionesVotadas = cancionesVotadas;
    }

    public Usuario() {
        super();
    }

    public Usuario(String nombre, String contrasenia, String correo, String rol) {
        super();
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
}
