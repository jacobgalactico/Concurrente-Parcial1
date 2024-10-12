package com.example.parcial1;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Scheduler {
    private List<EstacionDeTrabajo> estaciones;
    private int index = 0; // Para realizar el round-robin

    public Scheduler(List<EstacionDeTrabajo> estaciones) {
        this.estaciones = estaciones;
    }

    // Método para iniciar la producción en round-robin
    public void startProduction() {
        while (true) {
            EstacionDeTrabajo estacion = estaciones.get(index);
            estacion.run();  // Ejecuta la estación de trabajo seleccionada
            index = (index + 1) % estaciones.size(); // Avanza al siguiente en forma circular
            try {
                Thread.sleep(1000); // Simula un tiempo de espera entre asignaciones
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
