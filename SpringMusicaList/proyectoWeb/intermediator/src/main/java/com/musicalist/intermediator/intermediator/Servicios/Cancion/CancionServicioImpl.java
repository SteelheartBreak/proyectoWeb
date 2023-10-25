package com.musicalist.intermediator.intermediator.Servicios.Cancion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicalist.intermediator.intermediator.Modelo.Cancion;
import com.musicalist.intermediator.intermediator.Repositorio.CancionRepositorio;

@Service
public class CancionServicioImpl implements CancionServicio {

    @Autowired
    CancionRepositorio cancionRepositorio;

    @Override
    public List<Cancion> findAll() {
        return cancionRepositorio.findAll();
    }

    @Override
    public Cancion buscarCancion(Long id) {
        return cancionRepositorio.findById(id).get();
    }

    @Override
    public void borrarCancion(Long id) {
        cancionRepositorio.deleteById(id);
    }

    @Override
    public void actualizarCancion(Cancion cancion) {
        cancionRepositorio.save(cancion);
    }

    @Override
    public Cancion insertarCancion(Cancion cancion) {
        return cancionRepositorio.save(cancion); 
    }

    @Override
    public Cancion buscarCancionPorNombre(String nombre) {
        return cancionRepositorio.findByNombre(nombre);
    }
    
}
