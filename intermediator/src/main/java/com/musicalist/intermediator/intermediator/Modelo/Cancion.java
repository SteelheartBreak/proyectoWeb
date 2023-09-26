package com.musicalist.intermediator.intermediator.Modelo;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "cancion")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nombreArtista")
    private String nombreArtista;

    @ManyToOne
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToMany
    @JoinTable(
            name = "voto",
            joinColumns = @JoinColumn(name = "cancion_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> votantes;

    public Cancion() {
        super();
    }

    public Cancion(String nombre, String nombreArtista, Genero genero) {
        super();
        this.genero = genero;
        this.nombre = nombre;
        this.nombreArtista = nombreArtista;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public Genero getGenero() {
        return genero;
    }
    public List<Usuario> getVotantes()
    {
        return votantes;
    }
    
    public void setGenero(Genero genero) {
        this.genero = genero;
        genero.getGeneroCanciones().add(this);
    }
    public void setNombre(String Nombre)
    {
        this.nombre=Nombre;
    }
    public void setNombreArtista(String NombreArtista)
    {
        this.nombreArtista=NombreArtista;
    }
    public void addUsuario(Usuario usuario) {
        votantes.add(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        votantes.remove(usuario);
    }


}
