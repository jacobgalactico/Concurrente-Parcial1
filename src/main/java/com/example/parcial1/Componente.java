package com.example.parcial1;

public class Componente {
    private String nombre;
    private String tipo;

    public Componente(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }
}
