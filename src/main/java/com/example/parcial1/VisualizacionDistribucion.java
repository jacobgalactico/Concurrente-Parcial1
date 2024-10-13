package com.example.parcial1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class VisualizacionDistribucion extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static int[] contenedores;  // Array que representa los contenedores
    private static int numeroDeBolas;

    private Canvas canvas;

    // Instancia única para que pueda ser compartida entre hilos
    private static VisualizacionDistribucion instance;

    // Latch para sincronizar la inicialización de JavaFX
    private static CountDownLatch latch;

    public VisualizacionDistribucion() {
        instance = this; // Asignar la instancia en el constructor
    }

    // Constructor para inicializar los contenedores
    public VisualizacionDistribucion(int numeroDeContenedores, int numeroDeBolas) {
        if (contenedores == null) {
            contenedores = new int[numeroDeContenedores];  // Inicializar contenedores correctamente
        }
        VisualizacionDistribucion.numeroDeBolas = numeroDeBolas;
        instance = this;  // Asignar la instancia
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Distribución Normal en Tiempo Real");

        StackPane root = new StackPane();
        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Configurar un Timeline para actualizar la visualización periódicamente
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> actualizarVisualizacion()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir indefinidamente
        timeline.play();

        // Liberar el latch para que otros hilos puedan continuar
        latch.countDown();
    }

    // Método para agregar un componente al contenedor correcto (llamado por los hilos de producción)
    public synchronized void agregarComponente() {
        if (contenedores == null) {
            System.out.println("Los contenedores no están inicializados");
            return;
        }

        Random random = new Random();
        int posicion = 0;

        // Simular la caída del componente
        for (int i = 0; i < contenedores.length - 1; i++) {
            if (random.nextBoolean()) {
                posicion++;
            }
        }

        // Incrementar el contenedor donde cayó el componente
        contenedores[posicion]++;

        // Forzar la actualización de la interfaz gráfica en el hilo de JavaFX
        Platform.runLater(this::actualizarVisualizacion);
    }

    // Método para actualizar la visualización gráfica en el Canvas
    private void actualizarVisualizacion() {
        if (canvas == null || contenedores == null) {
            return; // Asegurarse de que el canvas y los contenedores no sean nulos
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH, HEIGHT); // Limpiar el canvas

        int width = WIDTH / contenedores.length;
        for (int i = 0; i < contenedores.length; i++) {
            int height = contenedores[i] * 10; // Escalar los valores de los contenedores
            gc.setFill(Color.BLUE);
            gc.fillRect(i * width, HEIGHT - height, width - 2, height); // Dibujar el rectángulo
        }
    }

    // Método para iniciar JavaFX y recibir el latch para sincronización
    public static void iniciarVisualizacion(String[] args, CountDownLatch latch) {
        VisualizacionDistribucion.latch = latch;
        launch(args); // Método de JavaFX que inicia el ciclo de vida de la aplicación
    }

    // Método para obtener la instancia de la visualización
    public static VisualizacionDistribucion getInstance() {
        return instance;
    }
}
