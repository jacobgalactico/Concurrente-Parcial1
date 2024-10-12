package com.example.parcial1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class VisualizacionDistribucion extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static int[] contenedores;
    private static int numeroDeBolas;

    private Canvas canvas;

    public VisualizacionDistribucion() {
        // Constructor vacío requerido por JavaFX
    }

    public VisualizacionDistribucion(int numeroDeContenedores, int numeroDeBolas) {
        contenedores = new int[numeroDeContenedores];
        VisualizacionDistribucion.numeroDeBolas = numeroDeBolas;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Distribución Normal en Tiempo Real");
        StackPane root = new StackPane();
        canvas = new Canvas(WIDTH, HEIGHT);  // Crear el Canvas para dibujar
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para agregar una bola (o componente) al contenedor correspondiente
    public synchronized void agregarComponente() {
        Random random = new Random();
        int posicion = 0;

        // Simula la caída de un componente por el tablero de Galton
        for (int i = 0; i < contenedores.length - 1; i++) {
            if (random.nextBoolean()) {
                posicion++;
            }
        }

        // Incrementa el contenedor donde "cayó" el componente
        contenedores[posicion]++;

        // Asegura que la actualización de la interfaz gráfica ocurra en el hilo de JavaFX
        Platform.runLater(this::actualizarVisualizacion);
    }

    // Método para actualizar la visualización gráfica en tiempo real
    private void actualizarVisualizacion() {
        if (canvas == null) return;  // Asegurarse de que el canvas no sea nulo

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH, HEIGHT); // Limpiar el canvas

        int width = WIDTH / contenedores.length;
        for (int i = 0; i < contenedores.length; i++) {
            int height = contenedores[i] * 10; // Escalar el número de componentes
            gc.setFill(Color.BLUE);
            gc.fillRect(i * width, HEIGHT - height, width - 2, height); // Dibujar el rectángulo
        }
    }

    // Método para iniciar la visualización en JavaFX
    public static void iniciarVisualizacion(String[] args) {
        launch(args); // Llama al método "start" de JavaFX
    }
}
