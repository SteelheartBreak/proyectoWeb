package com.musicalist.intermediator.intermediator.DTO;

public class CancionesLikeDTO {
    private Integer cancionId;
    private Integer usuarioId;

    public CancionesLikeDTO() {
    }

    public CancionesLikeDTO(Integer cancionId, Integer usuarioId) {
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

