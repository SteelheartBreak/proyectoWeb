package com.musicalist.intermediator.intermediator.DTO;

public class CancionesLikeDTO {
    private Long cancionId;
    private Long usuarioId;

    public CancionesLikeDTO() {
    }

    public CancionesLikeDTO(Long cancionId, Long usuarioId) {
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

