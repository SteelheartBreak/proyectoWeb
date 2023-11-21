package com.musicalist.intermediator.intermediator.Configuracion;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VotoID implements Serializable
{
    @Column(name = "cancion_id")
    private Integer cancionId;
    @Column(name = "usuario_id")
    private Integer usuarioId;

    public VotoID() {
    }

    public VotoID(Integer cancionId, Integer usuarioId) {
        this.cancionId = cancionId;
        this.usuarioId = usuarioId;
    }

    public Integer getCancionId() {
        return cancionId;
    }

    public void setCancionId(Integer cancionId) {
        this.cancionId = cancionId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
