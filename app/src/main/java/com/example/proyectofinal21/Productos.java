package com.example.proyectofinal21;

public class Productos {
    int codigo;
    String letra;
    String Actor;
    String genero;
    String nombre;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getActor() {
        return Actor;
    }

    public void setActor(String actor) {
        Actor = actor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Productos(int codigo, String letra, String actor, String genero, String nombre) {
        this.codigo = codigo;
        this.letra = letra;
        Actor = actor;
        this.genero = genero;
        this.nombre = nombre;
    }
}
