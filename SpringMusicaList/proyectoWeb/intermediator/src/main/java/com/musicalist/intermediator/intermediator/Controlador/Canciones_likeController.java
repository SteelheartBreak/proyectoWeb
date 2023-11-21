package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.musicalist.intermediator.intermediator.DTO.CancionDTO;
import com.musicalist.intermediator.intermediator.DTO.CancionesLikeDTO;
import com.musicalist.intermediator.intermediator.DTO.UsuarioDTO;
import com.musicalist.intermediator.intermediator.Servicios.CancionService;
import com.musicalist.intermediator.intermediator.Servicios.Cancion_likeService;
import com.musicalist.intermediator.intermediator.Servicios.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/voto")
public class Canciones_likeController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    CancionService cancionService;
    @Autowired
    Cancion_likeService cancion_likeService;

    @DeleteMapping("/delete/User/{id}")
    @Operation(summary = "Borrar un voto por id")
    public ResponseEntity<String> deleteByUsuarioId(@PathVariable Integer id) {
        List<CancionesLikeDTO> Votos = cancion_likeService.VotosUsuario(id);
        cancion_likeService.BorrarTodos(Votos);
        return new ResponseEntity<>("Voto eliminado", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/Cancion/{id}")
    @Operation(summary = "Borrar un voto por id")
    public ResponseEntity<String> deleteByCancionId(@PathVariable Integer id) {
        List<CancionesLikeDTO> Votos = cancion_likeService.VotosCancion(id);
        cancion_likeService.BorrarTodos(Votos);
        return new ResponseEntity<>("Voto eliminado", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{cancionId}/{usuarioId}")
    public CancionesLikeDTO buscarVoto(@PathVariable Integer cancionId, @PathVariable Integer usuarioId) {
        List<CancionesLikeDTO> Votos = cancion_likeService.VotosUsuario(usuarioId);
        for (CancionesLikeDTO votico : Votos) {
            if (votico.getCancionId() == cancionId) {
                return votico;
            }
        }
        return null;
    }

    @PostMapping("/votar")
    public ResponseEntity<CancionesLikeDTO> votar(@RequestBody CancionesLikeDTO voto) {
        CancionesLikeDTO newVoto = new CancionesLikeDTO();

        UsuarioDTO user = usuarioService.BuscarID(voto.getUsuarioId());
        CancionDTO song = cancionService.BuscarID(voto.getCancionId());

        if (user != null && song != null) {

            newVoto.setUsuarioId(user.getId());
            newVoto.setCancionId(song.getId());
            cancion_likeService.guardar(newVoto);

            return new ResponseEntity<>(newVoto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/borrarVoto/{cancionId}/{usuarioId}")
    @Operation(summary = "Borrar un voto")
    public ResponseEntity<String> borrarVoto(@PathVariable Integer cancionId, @PathVariable Integer usuarioId) {
        List<CancionesLikeDTO> Votos = cancion_likeService.VotosUsuario(usuarioId);
        for (CancionesLikeDTO votico : Votos) {
            if (votico.getCancionId() == cancionId) {
                cancion_likeService.Borrar(votico);
                return new ResponseEntity<>("Voto eliminado", HttpStatus.NO_CONTENT);
            }
        }
        return null;
    }
}
