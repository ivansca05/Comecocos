/*
 * Esta clase representa al enemigo del juego, el Fantasma.
 * Se encarga de gestionar su posición, movimiento aleatorio y dibujo.
 */

import java.awt.*;
import java.util.*;

public class Fantasma {
    private int x, y; // Variables para almacenar la posición actual del fantasma.
    private Mapa mapa; // Referencia al objeto Mapa para validar movimientos y evitar que el fantasma atraviese paredes.
    private Random rand = new Random(); // Objeto Random para generar movimientos aleatorios.

    // Constructor de la clase Fantasma.
    public Fantasma(int startX, int startY, Mapa mapa) {
        this.x = startX; // Asigna la posición inicial en el eje X.
        this.y = startY; // Asigna la posición inicial en el eje Y.
        this.mapa = mapa; // Guarda la referencia al mapa para validar los movimientos.
    }

    // Método que mueve al fantasma de forma aleatoria.
    public void mover() {
        // Define un array con posibles desplazamientos: 0 (sin movimiento), 1 (mover hacia adelante) y -1 (mover hacia atrás).
        int[] direcciones = {0, 1, -1};
        int dx = direcciones[rand.nextInt(3)]; // Selecciona aleatoriamente un desplazamiento en el eje X.
        int dy = direcciones[rand.nextInt(3)]; // Selecciona aleatoriamente un desplazamiento en el eje Y.
        // Calcula la nueva posición sumando el desplazamiento a la posición actual.
        int nuevaX = x + dx;
        int nuevaY = y + dy;
        // Verifica que la nueva posición no sea una pared usando el método esMuro de Mapa.
        if (!mapa.esMuro(nuevaX, nuevaY)) {
            // Actualiza la posición del fantasma.
            x = nuevaX;
            y = nuevaY;
        }
    }

    // Método que dibuja al fantasma en la pantalla.
    public void dibujar(Graphics g) {
        g.setColor(Color.RED); // Establece el color rojo para representar al fantasma.
        int margin = Mapa.CELL_SIZE / 10; // Define un margen para que el dibujo no ocupe toda la celda.
        int size = Mapa.CELL_SIZE - 2 * margin; // Calcula el tamaño del óvalo restando el doble del margen al tamaño de la celda.
        // Dibuja un óvalo en la posición actual del fantasma, centrado en la celda.
        g.fillOval(x * Mapa.CELL_SIZE + margin, y * Mapa.CELL_SIZE + margin, size, size);
    }

    // Métodos getter que retornan la posición en el eje X, Y del fantasma.
    public int getX() { return x; }
    public int getY() { return y; }
}