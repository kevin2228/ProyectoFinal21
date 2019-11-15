package com.example.proyectofinal21;

public class Dto {

    int codigo;
    String autor;
    String letra;
    String genero;
    String nombre;

    public Dto() {
    }

    public Dto(int codigo, String autor, String letra, String genero, String nombre) {
        this.codigo = codigo;
        this.autor = autor;
        this.letra = letra;
        this.genero = genero;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
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
}
