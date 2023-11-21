package com.musicalist.intermediator.intermediator.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicalist.intermediator.intermediator.Configuracion.VotoID;
import com.musicalist.intermediator.intermediator.DTO.CancionesLikeDTO;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;
import com.musicalist.intermediator.intermediator.Modelo.Canciones_like;
import com.musicalist.intermediator.intermediator.Modelo.Usuario;
import com.musicalist.intermediator.intermediator.Repositorio.Canciones_likeRepositorio;

@Service
public class Cancion_likeService {

    @Autowired
    CancionService cancionService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    Canciones_likeRepositorio votoRepositorio;

    public Canciones_like ConvertirEntidad(CancionesLikeDTO dto) {
        VotoID votoID = new VotoID(dto.getCancionId(), dto.getUsuarioId());
        Cancion cancion = cancionService.convertirEntidad(cancionService.BuscarID(dto.getCancionId()));
        Usuario usuario = usuarioService.convertiraEntidad(usuarioService.BuscarID(dto.getUsuarioId()));

        Canciones_like cancionesLike = new Canciones_like(cancion, usuario);
        cancionesLike.setvoto(votoID);

        return cancionesLike;
    }

    public CancionesLikeDTO ConvertirDTO(Canciones_like like) {
        CancionesLikeDTO dto = new CancionesLikeDTO(like.getCancion().getId(), like.getUsuario().getId());
        return dto;
    }

    public void guardar(CancionesLikeDTO voto) {
        votoRepositorio.save(ConvertirEntidad(voto));

    }

    public List<CancionesLikeDTO> VotosUsuario(Integer id) {
        List<CancionesLikeDTO> Votos = new ArrayList<>();
        for (Canciones_like voto : votoRepositorio.findByUsuarioId(id)) {
            Votos.add(ConvertirDTO(voto));
        }
        return Votos;
    }

    public List<CancionesLikeDTO> VotosCancion(Integer id) {
        List<CancionesLikeDTO> Votos = new ArrayList<>();
        for (Canciones_like voto : votoRepositorio.findByCancionId(id)) {
            Votos.add(ConvertirDTO(voto));
        }
        return Votos;
    }

    public void BorrarTodos(List<CancionesLikeDTO> Votos) {
        List<Canciones_like> votos = new ArrayList<>();
        for (CancionesLikeDTO voto : Votos) {
            votos.add(ConvertirEntidad(voto));
        }
        votoRepositorio.deleteAll(votos);

    }

    public void Borrar(CancionesLikeDTO Voto) {
        votoRepositorio.delete(ConvertirEntidad(Voto));
    }

}
