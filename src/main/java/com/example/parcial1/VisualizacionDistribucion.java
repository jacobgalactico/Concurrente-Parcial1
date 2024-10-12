package com.example.parcial1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class VisualizacionDistribucion extends JPanel {
    private int[] contenedores;
    private int numeroDeBolas;

    public VisualizacionDistribucion(int numeroDeContenedores, int numeroDeBolas) {
        this.contenedores = new int[numeroDeContenedores];
        this.numeroDeBolas = numeroDeBolas;
    }

    public void agregarBola() {
        Random random = new Random();
        int posicion = 0;

        // Simula la caída de la bola a través del tablero de Galton
        for (int i = 0; i < contenedores.length - 1; i++) {
            if (random.nextBoolean()) {
                posicion++;
            }
        }

        // Incrementa el contenedor donde cayó la bola
        contenedores[posicion]++;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja los contenedores
        int width = getWidth() / contenedores.length;
        for (int i = 0; i < contenedores.length; i++) {
            int height = contenedores[i] * 10; // Escalar el número de bolas
            g.fillRect(i * width, getHeight() - height, width - 2, height);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Visualización Distribución Normal");
        VisualizacionDistribucion panel = new VisualizacionDistribucion(10, 100);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(panel);
        frame.setVisible(true);

        // Simular la caída de bolas
        for (int i = 0; i < 100; i++) {
            panel.agregarBola();
            try {
                Thread.sleep(100); // Simula el tiempo entre caídas de bolas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
