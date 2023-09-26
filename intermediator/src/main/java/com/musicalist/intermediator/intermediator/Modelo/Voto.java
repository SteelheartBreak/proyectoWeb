package com.musicalist.intermediator.intermediator.Modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.musicalist.intermediator.intermediator.Extra.VotoID;


@Entity
@Table(name = "voto")
public class Voto {
    
    @EmbeddedId
    private VotoID id;

    
    @ManyToOne
    @MapsId("cancionId")
    @JoinColumn(name = "cancion_id")
    private Cancion cancion;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Voto() {
        super();
    }

    public Voto(Cancion cancion, Usuario usuario) {
        super();
        this.cancion = cancion;
        this.usuario = usuario;
        this.id = new VotoID();
        this.id.setCancionId(cancion.getId());
        this.id.setUsuarioId(usuario.getId());
    }

    public VotoID getId() {
        return id;
    }

    public void setId(VotoID id) {
        this.id = id;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}

