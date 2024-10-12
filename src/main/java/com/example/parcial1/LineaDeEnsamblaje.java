package com.example.parcial1;

import java.util.concurrent.BlockingQueue;

public class LineaDeEnsamblaje extends Thread {
    private BlockingQueue<Componente> buffer;
    private VisualizacionDistribucion visualizacion;

    public LineaDeEnsamblaje(BlockingQueue<Componente> buffer, VisualizacionDistribucion visualizacion) {
        this.buffer = buffer;
        this.visualizacion = visualizacion;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Componente componente = buffer.take(); // Toma un componente del buffer
                System.out.println("Ensamblando " + componente.getNombre());

                // Actualiza la visualización en tiempo real
                visualizacion.agregarComponente();

                Thread.sleep(500); // Simula el tiempo de ensamblaje de cada componente
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
