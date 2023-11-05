package com.musicalist.intermediator.intermediator.Modelo;

import com.musicalist.intermediator.intermediator.Configuracion.VotoID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Canciones_like {

    @EmbeddedId
    private VotoID voto;

    @ManyToOne
    @MapsId("cancionId")
    @JoinColumn(name = "cancion_id")
    private Cancion cancion;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Canciones_like() {
        super();
    }

    public Canciones_like(Cancion cancion, Usuario usuario) {
        super();
        this.cancion = cancion;
        this.usuario = usuario;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
