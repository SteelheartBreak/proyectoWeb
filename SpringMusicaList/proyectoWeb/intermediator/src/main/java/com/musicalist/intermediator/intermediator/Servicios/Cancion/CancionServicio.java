package com.musicalist.intermediator.intermediator.Servicios.Cancion;

import java.util.List;

import com.musicalist.intermediator.intermediator.Modelo.Cancion;

public interface CancionServicio {

    //metodos
    public List<Cancion> findAll();

    public Cancion buscarCancion(Long id);

    public void borrarCancion(Long id);

    public void actualizarCancion(Cancion cancion);

    public Cancion insertarCancion(Cancion cancion);

    public Cancion buscarCancionPorNombre(String nombre);


    
}
