package com.example.parcial1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BufferCompartido {
    private BlockingQueue<Componente> buffer;

    public BufferCompartido(int capacidad) {
        buffer = new ArrayBlockingQueue<>(capacidad); // Un buffer con capacidad limitada
    }

    public BlockingQueue<Componente> getBuffer() {
        return buffer;
    }
}
