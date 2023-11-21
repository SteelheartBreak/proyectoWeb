package com.musicalist.intermediator.intermediator.Servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicalist.intermediator.intermediator.DTO.GeneroDTO;
import com.musicalist.intermediator.intermediator.Modelo.Genero;
import com.musicalist.intermediator.intermediator.Repositorio.GeneroRepositorio;

@Service
public class GeneroService {

    private final GeneroRepositorio generoRepositorio;

    @Autowired
    public GeneroService(GeneroRepositorio generoRepositorio) {
        this.generoRepositorio = generoRepositorio;
    }

    public List<GeneroDTO> EncontrarTodos() {
        List<Genero> generos = generoRepositorio.findAll();
        return generos.stream()
                .map(this::convertiraDTO)
                .collect(Collectors.toList());
    }

    public GeneroDTO BuscarNombre(String nombre) {
        Genero genero=generoRepositorio.findBynombre(nombre);
        return convertiraDTO(genero);
    }

    public GeneroDTO BuscarID(Integer id) {
        Genero genero=generoRepositorio.findById(id);
        return convertiraDTO(genero);
    }

    public void borrarGenero(GeneroDTO genero) {
        Genero generoEliminar=convertiraEntidad(genero);
        generoRepositorio.delete(generoEliminar);
    }

    public void GuardarGenero(GeneroDTO genero) {
        Genero generoGuardar=convertiraEntidad(genero);
        generoRepositorio.save(generoGuardar);
    }

    public GeneroDTO convertiraDTO(Genero genero) {
        if(genero==null)
        {
            return null;

        }
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(genero.getId());
        generoDTO.setNombre(genero.getNombre());
        return generoDTO;
    }

    public Genero convertiraEntidad(GeneroDTO generoDTO) {
        if(generoDTO==null)
        {
            return null;

        }
        Genero genero = new Genero();
        genero.setNombre(generoDTO.getNombre());
        genero.setId(generoDTO.getId());
        return genero;
    }
}