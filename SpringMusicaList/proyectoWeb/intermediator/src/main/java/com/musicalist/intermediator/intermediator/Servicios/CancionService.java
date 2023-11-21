package com.musicalist.intermediator.intermediator.Servicios;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musicalist.intermediator.intermediator.DTO.CancionDTO;
import com.musicalist.intermediator.intermediator.Modelo.Cancion;
import com.musicalist.intermediator.intermediator.Repositorio.CancionRepositorio;

@Service
public class CancionService{

    private final GeneroService generoService;
    private final CancionRepositorio cancionRepositorio;
    

    @Autowired
    public CancionService(GeneroService generoService,CancionRepositorio cancionRepositorio) {
        this.generoService = generoService;
        this.cancionRepositorio=cancionRepositorio;
    }

    public List<CancionDTO> Todos() {
        List<CancionDTO> Canciones = new ArrayList<>();
        for (Cancion cancion : cancionRepositorio.findAll()) {
            Canciones.add(convertirCancionDTO(cancion));
        }
        return Canciones;
    }

    public CancionDTO BuscarID(Integer id) {
        Cancion encontrado = cancionRepositorio.findById(id);
        return convertirCancionDTO(encontrado);
    }
    public void guardar(CancionDTO cancion)
    {
        cancionRepositorio.save(convertirEntidad(cancion));
    }
    public void borrar(CancionDTO cancion)
    {
        cancionRepositorio.delete(convertirEntidad(cancion));
    }
    public List<CancionDTO> BuscarGenero(Integer id) {
        List<CancionDTO> encontrado = new ArrayList<>();
        for(Cancion cancion:cancionRepositorio.findByGenero(id))
        {
            encontrado.add(convertirCancionDTO(cancion));
        }
        return encontrado;
    }
    public CancionDTO BuscarNombre(String nombre) {
        Cancion encontrado = cancionRepositorio.findByNombre(nombre);
        return convertirCancionDTO(encontrado);
    }

    public CancionDTO convertirCancionDTO(Cancion cancion) {
        if(cancion==null)
        {
            return null;
        }
        CancionDTO canciondto=new CancionDTO();
        canciondto.setId(cancion.getId());
        canciondto.setNombre(cancion.getNombre());
        canciondto.setNombreAlbum(cancion.getNombreAlbum());
        canciondto.setNombreArtista(cancion.getNombreArtista());
        canciondto.setImagenURL(cancion.getImagenURL());
        canciondto.setGenero(generoService.convertiraDTO(cancion.getGenero()));
        return canciondto;
    }

    public Cancion convertirEntidad(CancionDTO DTO) {
        if(DTO==null)
        {
            return null;
        }
        Cancion cancion=new Cancion();
        cancion.setId(DTO.getId());
        cancion.setNombre(DTO.getNombre());
        cancion.setNombreAlbum(DTO.getNombreAlbum());
        cancion.setNombreArtista(DTO.getNombreArtista());
        cancion.setImagenURL(DTO.getImagenURL());
        cancion.setGenero(generoService.convertiraEntidad(DTO.getGenero()));
        return cancion;
    }
    
}
