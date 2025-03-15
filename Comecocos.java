/*
 * Esta clase representa al personaje principal del juego, Comecocos.
 * Se encarga de gestionar la posición, el movimiento, el puntaje y el dibujo del personaje.
 */

import java.awt.*;

public class Comecocos {
    private int x, y; // Variables para almacenar la posición actual de Comecocos en el mapa.
    private int puntos; // Variable para almacenar el puntaje acumulado al comer cocos.
    private Mapa mapa; // Referencia al objeto Mapa para interactuar con sus elementos (paredes, cocos, etc.).

    // Constructor de la clase Comecocos.
    public Comecocos(int startX, int startY, Mapa mapa) {
        this.x = startX; // Asigna la posición inicial en el eje X.
        this.y = startY; // Asigna la posición inicial en el eje Y.
        this.mapa = mapa; // Guarda la referencia del mapa para interactuar con él.
        this.puntos = 0; // Inicializa el puntaje en 0.
    }

    // Método que mueve a Comecocos según el desplazamiento (dx, dy).
    public void mover(int dx, int dy) {
        // Calcula la nueva posición sumando el desplazamiento a la posición actual.
        int nuevaX = x + dx;
        int nuevaY = y + dy;
        // Verifica que la nueva posición no sea una pared usando el método esMuro de Mapa.
        if (!mapa.esMuro(nuevaX, nuevaY)) {
            // Actualiza la posición de Comecocos.
            x = nuevaX;
            y = nuevaY;
            // Si en la nueva posición hay un coco, se come el coco y se suma 10 puntos.
            if (mapa.comerCoco(nuevaX, nuevaY)) {
                puntos += 10;
            }
        }
    }

    // Método que dibuja a Comecocos en la pantalla.
    public void dibujar(Graphics g) {
        g.setColor(Color.YELLOW); // Establece el color amarillo para representar a Comecocos.
        int margin = Mapa.CELL_SIZE / 10; // Define un margen para que el dibujo no ocupe toda la celda.
        int size = Mapa.CELL_SIZE - 2 * margin; // Calcula el tamaño del óvalo restando el doble del margen al tamaño de la celda.
        // Dibuja un óvalo en la posición de Comecocos, centrado en la celda.
        g.fillOval(x * Mapa.CELL_SIZE + margin, y * Mapa.CELL_SIZE + margin, size, size);
    }

    // Método getters que retornan la posición en el eje X, Y, puntos.
    public int getX() { return x; }
    public int getY() { return y; }
    public int getPuntos() { return puntos; }
}