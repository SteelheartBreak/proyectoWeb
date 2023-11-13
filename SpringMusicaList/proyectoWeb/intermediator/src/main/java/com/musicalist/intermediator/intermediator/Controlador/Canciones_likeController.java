package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;
import java.util.Optional;

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

import com.musicalist.intermediator.intermediator.Configuracion.VotoID;
import com.musicalist.intermediator.intermediator.DTO.CancionesLikeDTO;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;
import com.musicalist.intermediator.intermediator.Modelo.Canciones_like;
import com.musicalist.intermediator.intermediator.Modelo.Usuario;
import com.musicalist.intermediator.intermediator.Repositorio.CancionRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.Canciones_likeRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.UsuarioRepositorio;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/voto")
public class Canciones_likeController {

    @Autowired
    Canciones_likeRepositorio votoRepositorio;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    CancionRepositorio cancionRepositorio;

    @DeleteMapping("/delete/User/{id}")
    @Operation(summary = "Borrar un voto por id")
    public ResponseEntity<String> deleteByUsuarioId(@PathVariable Long id) {
        List<Canciones_like> Votos = votoRepositorio.findByUsuarioId(id);
        votoRepositorio.deleteAll(Votos);
        return new ResponseEntity<>("Voto eliminado", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/Cancion/{id}")
    @Operation(summary = "Borrar un voto por id")
    public ResponseEntity<String> deleteByCancionId(@PathVariable Long id) {
        List<Canciones_like> Votos = votoRepositorio.findByCancionId(id);
        votoRepositorio.deleteAll(Votos);
        return new ResponseEntity<>("Voto eliminado", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{cancionId}/{usuarioId}")
    public Canciones_like buscarVoto(@PathVariable Long cancionId, @PathVariable Long usuarioId) {
        List<Canciones_like> Votos = votoRepositorio.findByUsuarioId(usuarioId);
        for (Canciones_like votico : Votos) {
            if (votico.getCancion().getId() == cancionId) {
                return votico;
            }
        }
        return null;
    }

    @PostMapping("/votar")
    public ResponseEntity<Canciones_like> votar(@RequestBody CancionesLikeDTO voto) {
        Canciones_like newVoto = new Canciones_like();

        Optional<Usuario> usuario = usuarioRepositorio.findById(voto.getUsuarioId());
        Optional<Cancion> cancion = cancionRepositorio.findById(voto.getCancionId());

        if (usuario.isPresent() && cancion.isPresent()) {

            Usuario user = usuario.get();
            Cancion song = cancion.get();
            newVoto.setUsuario(user);
            newVoto.setCancion(song);
            VotoID id=new VotoID();
            id.setCancionId(song.getId());
            id.setUsuarioId(user.getId());
            newVoto.setvoto(id);

            votoRepositorio.save(newVoto);

            return new ResponseEntity<>(newVoto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
