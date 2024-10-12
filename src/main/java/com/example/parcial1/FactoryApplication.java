package com.example.parcial1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@SpringBootApplication
public class FactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryApplication.class, args);

		BufferCompartido bufferCompartido = new BufferCompartido(10); // Buffer con capacidad 10
		BlockingQueue<Componente> buffer = bufferCompartido.getBuffer();

		// Crear estaciones de trabajo
		EstacionDeTrabajo estacion1 = new EstacionDeTrabajo("Estación 1", buffer, 5);
		EstacionDeTrabajo estacion2 = new EstacionDeTrabajo("Estación 2", buffer, 5);
		EstacionDeTrabajo estacion3 = new EstacionDeTrabajo("Estación 3", buffer, 5);

		// Crear una lista de estaciones de trabajo para el scheduler
		List<EstacionDeTrabajo> estaciones = Arrays.asList(estacion1, estacion2, estacion3);

		// Crear y ejecutar el scheduler
		Scheduler scheduler = new Scheduler(estaciones);
		new Thread(scheduler::startProduction).start(); // Ejecutar el scheduler en un nuevo hilo

		// Iniciar la visualización de la distribución de componentes con JavaFX
		VisualizacionDistribucion visualizacion = new VisualizacionDistribucion(10, 100);
		VisualizacionDistribucion.iniciarVisualizacion(args); // Iniciar JavaFX

		// Crear línea de ensamblaje con visualización
		LineaDeEnsamblaje lineaDeEnsamblaje = new LineaDeEnsamblaje(buffer, visualizacion);
		lineaDeEnsamblaje.start(); // Iniciar la línea de ensamblaje en un hilo separado
	}
}
