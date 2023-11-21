package com.musicalist.intermediator.intermediator.Modelo;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Integer id;

    private String nombre;
    private String contrasenia;
    private String correo;
    private String rol;

    @ManyToMany(mappedBy = "votantes")
    @JsonIgnore
    private List<Cancion> cancionesLiked;

    public Usuario(String nombre, String contrasenia, String correo, String rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
    }

    public Integer getId() {
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

    public void setId(Integer id)
    {
        this.id=id;
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
