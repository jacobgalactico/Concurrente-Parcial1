Fábrica de Campanas de Gauss: Simulación de Producción Concurrente
Descripción del Proyecto
Este proyecto simula la operación de la fábrica ficticia "Campanas de Gauss", inspirada en el tablero de Galton, un dispositivo que demuestra la distribución normal o Gaussiana. En esta fábrica, múltiples estaciones de trabajo producen componentes de forma concurrente, y una línea de ensamblaje final consume estos componentes para ensamblar las máquinas de distribución normal.

La simulación está diseñada como un sistema concurrente que emplea múltiples hilos para modelar las estaciones de trabajo, la línea de ensamblaje y la visualización de los resultados en tiempo real.

Características Principales
Producción Concurrente: Varias estaciones de trabajo producen componentes en paralelo utilizando hilos de ejecución independientes.
Patrón Productor-Consumidor: Las estaciones de trabajo actúan como productores de componentes, mientras que la línea de ensamblaje consume estos componentes para construir el producto final.
Buffer Compartido: Los componentes producidos son almacenados en un buffer compartido gestionado con un BlockingQueue para asegurar un acceso sincronizado entre los productores y el consumidor.
Scheduling Round-Robin: La producción de componentes se distribuye entre las estaciones de trabajo mediante un algoritmo de scheduling round-robin.
Visualización en Tiempo Real: A medida que los componentes se producen y ensamblan, se muestra una representación gráfica en tiempo real de cómo se distribuyen los componentes en diferentes contenedores, simulando la curva de distribución normal.
Estructura del Proyecto
BufferCompartido.java: Modelo del buffer compartido que almacena los componentes producidos por las estaciones de trabajo.
Componente.java: Clase que representa los componentes producidos por las estaciones de trabajo.
EstacionDeTrabajo.java: Modelo de una estación de trabajo que produce componentes y los coloca en el buffer compartido.
FactoryApplication.java: Punto de entrada de la aplicación. Aquí se inicializan las estaciones de trabajo, la línea de ensamblaje y la visualización.
LineaDeEnsamblaje.java: Hilo de ejecución que actúa como el consumidor en el patrón productor-consumidor, ensamblando los componentes producidos.
Scheduler.java: Scheduler que distribuye las tareas de producción entre las estaciones de trabajo mediante un algoritmo round-robin.
VisualizacionDistribucion.java: Clase responsable de la visualización gráfica de la distribución de componentes en los contenedores, utilizando JavaFX.

link al repositorio: https://github.com/jacobgalactico/Concurrente-Parcial1.git
