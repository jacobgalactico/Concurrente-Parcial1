package com.example.parcial1;

import java.util.concurrent.BlockingQueue;

public class LineaDeEnsamblaje extends Thread {
    private BlockingQueue<Componente> buffer;

    public LineaDeEnsamblaje(BlockingQueue<Componente> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Componente componente = buffer.take(); // Toma del buffer
                System.out.println("Ensamblando " + componente.getNombre());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
