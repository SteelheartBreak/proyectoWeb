package com.musicalist.intermediator.intermediator.Configuracion;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class VotoID implements Serializable
{
    private Long cancionId;
    private Long usuarioId;

    public VotoID() {
    }

    public VotoID(Long cancionId, Long usuarioId) {
        this.cancionId = cancionId;
        this.usuarioId = usuarioId;
    }

    public Long getCancionId() {
        return cancionId;
    }

    public void setCancionId(Long cancionId) {
        this.cancionId = cancionId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
