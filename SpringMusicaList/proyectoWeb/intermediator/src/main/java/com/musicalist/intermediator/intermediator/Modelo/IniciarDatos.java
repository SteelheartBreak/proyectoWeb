package com.musicalist.intermediator.intermediator.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import com.musicalist.intermediator.intermediator.Repositorio.CancionRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.GeneroRepositorio;
import com.musicalist.intermediator.intermediator.Repositorio.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class IniciarDatos implements ApplicationRunner{

    @Autowired
    CancionRepositorio cancionRepositorio;

    @Autowired
    GeneroRepositorio generoRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //generos
        generoRepositorio.save(new Genero("Rock"));
        generoRepositorio.save(new Genero("Pop"));
        generoRepositorio.save(new Genero("Reggaeton"));
        generoRepositorio.save(new Genero("Rap"));
        generoRepositorio.save(new Genero("Salsa"));
        generoRepositorio.save(new Genero("Trap"));
        
        //canciones
        cancionRepositorio.save(new Cancion("Un Verano Sin Ti", "Bad Bunny","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("CanciónA", "Artista X","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Mi Melodía", "Grupo Y","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Sueños en el Viento", "Cantante Z","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Noche Estrellada", "Banda W","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Algo Mágico", "Solista V","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Ritmo del Corazón", "Dúo U","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Bailando Bajo la Luna", "Cantante T","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Historia de Amor", "Banda S","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Versos Perdidos", "Grupo R","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));
        cancionRepositorio.save(new Cancion("Amanecer Contigo", "Artista Q","ALBUM","https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72"));

        //usuarios
        usuarioRepositorio.save(new Usuario("Juan", "123", "a@a.com", "Administrador"));
        usuarioRepositorio.save(new Usuario("Pedro", "123", "b@b.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Maria", "123", "c@c.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Luis", "123", "d@d.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Carlos", "123", "e@e.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Ana", "123", "f@f.com", "Usuario"));
        usuarioRepositorio.save(new Usuario("Jose", "123", "g@g.com", "Usuario"));

        //Se genera una semilla random para las asociaciones
        Random random = new Random(123);

        //crear asociaciones
        List<Genero> generos = generoRepositorio.findAll(); //Guarda en una lista todos los generos
        List<Cancion> canciones = cancionRepositorio.findAll(); //Guarda en una lista todos los clientes

        for(int i = 0; i < canciones.size(); i++){
            Cancion cancion = canciones.get(i);
            Genero genero = generos.get(random.nextInt(generos.size()));
            cancion.setGenero(genero);
            cancionRepositorio.save(cancion);
            
        }

        List<Usuario> usuarios = usuarioRepositorio.findAll(); //Guarda en una lista todos los clientes

        for (int i = 0; i < canciones.size(); i++) {
            Cancion cancion = canciones.get(i);
            List<Usuario> votantesLISTA = new ArrayList<>();
            Usuario usuario1 = usuarios.get(random.nextInt(usuarios.size()));
            Usuario usuario2 = usuarios.get(random.nextInt(usuarios.size()));
            while(usuario1.getId()==usuario2.getId())
            {
             usuario2 = usuarios.get(random.nextInt(usuarios.size()));
            }
            votantesLISTA.add(usuario1);
            votantesLISTA.add(usuario2);
            cancion.setVotantes(votantesLISTA);
            cancionRepositorio.save(cancion);
        }
    }
}