package com.musicalist.intermediator.intermediator.Servicios.Genero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicalist.intermediator.intermediator.Modelo.Genero;
import com.musicalist.intermediator.intermediator.Repositorio.GeneroRepositorio;

@Service
public class GeneroServicioImpl implements GeneroServicio {

    @Autowired
    GeneroRepositorio generoRepositorio;

    @Override
    public List<Genero> findAll() {
        return generoRepositorio.findAll();
    }
    
}
