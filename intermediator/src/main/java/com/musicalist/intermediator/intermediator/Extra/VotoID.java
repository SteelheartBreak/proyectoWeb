package com.musicalist.intermediator.intermediator.Extra;

import java.io.Serializable;

public class VotoID implements Serializable {
    private Long cancionId;
    private Long usuarioId;

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
