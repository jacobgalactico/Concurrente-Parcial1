package com.example.parcial1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class FactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryApplication.class, args);

		// Crear buffer compartido con capacidad de 10 componentes
		BufferCompartido bufferCompartido = new BufferCompartido(10);
		BlockingQueue<Componente> buffer = bufferCompartido.getBuffer();

		// Crear estaciones de trabajo
		EstacionDeTrabajo estacion1 = new EstacionDeTrabajo("Estación 1", buffer, 5);
		EstacionDeTrabajo estacion2 = new EstacionDeTrabajo("Estación 2", buffer, 5);
		EstacionDeTrabajo estacion3 = new EstacionDeTrabajo("Estación 3", buffer, 5);

		// Lista de estaciones para el scheduler
		List<EstacionDeTrabajo> estaciones = Arrays.asList(estacion1, estacion2, estacion3);

		// Crear un CountDownLatch para sincronizar la inicialización de JavaFX
		CountDownLatch latch = new CountDownLatch(1);

		// Crear la instancia de VisualizacionDistribucion con los parámetros correctos
		VisualizacionDistribucion visualizacion = new VisualizacionDistribucion(10, 100);

		// Iniciar JavaFX en un hilo separado, asegurándonos de que los contenedores están inicializados correctamente
		new Thread(() -> VisualizacionDistribucion.iniciarVisualizacion(new String[] {}, latch)).start();

		try {
			// Esperar a que JavaFX se inicialice completamente
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Crear línea de ensamblaje con visualización (después de que JavaFX esté listo)
		LineaDeEnsamblaje lineaDeEnsamblaje = new LineaDeEnsamblaje(buffer, visualizacion);
		lineaDeEnsamblaje.start(); // Iniciar línea de ensamblaje en otro hilo

		// Crear y ejecutar el scheduler en un hilo separado
		Scheduler scheduler = new Scheduler(estaciones);
		new Thread(scheduler::startProduction).start();
	}
}
