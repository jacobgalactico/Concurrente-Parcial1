package com.example.parcial1;

import java.util.concurrent.BlockingQueue;

public class EstacionDeTrabajo extends Thread {
    private String nombre;
    private BlockingQueue<Componente> buffer;
    private int cantidadAProducir;

    public EstacionDeTrabajo(String nombre, BlockingQueue<Componente> buffer, int cantidadAProducir) {
        this.nombre = nombre;
        this.buffer = buffer;
        this.cantidadAProducir = cantidadAProducir;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < cantidadAProducir; i++) {
                Componente componente = new Componente(nombre + " Componente", "Tipo");
                buffer.put(componente); // Coloca en el buffer compartido
                System.out.println(nombre + " ha producido un " + componente.getNombre());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
