package com.musicalist.intermediator.intermediator.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import com.musicalist.intermediator.intermediator.Repositorio.CancionRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.GeneroRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class IniciarDatos implements ApplicationRunner {
        @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CancionRepositorio cancionRepositorio;

    @Autowired
    GeneroRepositorio generoRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generoRepositorio.save(new Genero("Rock"));
        generoRepositorio.save(new Genero("Pop"));
        generoRepositorio.save(new Genero("Reggaeton"));
        generoRepositorio.save(new Genero("Rap"));
        generoRepositorio.save(new Genero("Salsa"));
        generoRepositorio.save(new Genero("Trap"));

        cancionRepositorio.save(new Cancion("un verano sin ti", "bad bunny", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("cancióna", "artista x", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("mi melodía", "grupo y", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("sueños en el viento", "cantante z", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("noche estrellada", "banda w", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("algo mágico", "solista v", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("ritmo del corazón", "dúo u", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("bailando bajo la luna", "cantante t", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("historia de amor", "banda s", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("versos perdidos", "grupo r", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("amanecer contigo", "artista q", "album",
                "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));

        usuarioRepositorio.save(new Usuario("Juan", passwordEncoder.encode("123"), "a@a.com", "Administrador"));
        usuarioRepositorio.save(new Usuario("Pedro", passwordEncoder.encode("123"), "b@b.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Maria", passwordEncoder.encode("123"), "c@c.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Luis", passwordEncoder.encode("123"), "d@d.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Carlos", passwordEncoder.encode("123"), "e@e.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Ana", passwordEncoder.encode("123"), "f@f.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Jose", passwordEncoder.encode("123"), "g@g.com", "Usuario"));

        Random random = new Random(123);

        List<Genero> generos = generoRepositorio.findAll();
        List<Cancion> canciones = cancionRepositorio.findAll();

        for (int i = 0; i < canciones.size(); i++) {
            Cancion cancion = canciones.get(i);
            Genero genero = generos.get(random.nextInt(generos.size()));
            cancion.setGenero(genero);
            cancionRepositorio.save(cancion);
        }

        List<Usuario> usuarios = usuarioRepositorio.findAll();

        for (int i = 0; i < canciones.size(); i++) {
            Cancion cancion = canciones.get(i);
            List<Usuario> votantesLISTA = new ArrayList<>();
            Usuario usuario1 = usuarios.get(random.nextInt(usuarios.size()));
            Usuario usuario2 = usuarios.get(random.nextInt(usuarios.size()));
            while (usuario1.getId() == usuario2.getId()) {
                usuario2 = usuarios.get(random.nextInt(usuarios.size()));
            }
            votantesLISTA.add(usuario1);
            votantesLISTA.add(usuario2);
            cancion.setVotantes(votantesLISTA);
            cancionRepositorio.save(cancion);
        }
    }
}