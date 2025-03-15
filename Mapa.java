/*
 * Esta clase representa el mapa del juego, que incluye paredes y cocos.
 * Proporciona métodos para dibujar el mapa, verificar colisiones, comer cocos y comprobar si quedan cocos.
 */

import java.awt.*; // Importa todas las clases de java.awt.

public class Mapa {
    public static final int MAP_WIDTH = 20; // Define el número de columnas del mapa.
    public static final int MAP_HEIGHT = 9;// Define el número de filas del mapa.
    public static final int WINDOW_WIDTH = 1200; // Define el ancho deseado de la ventana en píxeles.
    public static final int WINDOW_HEIGHT = 800;  // Define el alto deseado de la ventana en píxeles.
    public static final int CELL_SIZE = Math.min(WINDOW_WIDTH / MAP_WIDTH, WINDOW_HEIGHT / MAP_HEIGHT); // Calcula el tamaño de cada celda, tomando el mínimo entre el ancho y alto dividido por el número de celdas.

    // Matriz que representa el diseño inicial del mapa:
    // 0 -> espacio vacío, 1 -> pared, 2 -> coco.
    public static final int[][] MAPA_INICIAL = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1},
            {1, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 2, 1, 2, 1, 1},
            {1, 2, 1, 0, 0, 2, 1, 2, 1, 0, 0, 2, 1, 2, 0, 0, 1, 2, 1, 1},
            {1, 2, 1, 2, 1, 2, 1, 0, 1, 2, 2, 1, 2, 0, 1, 2, 1, 2, 1, 1},
            {1, 2, 2, 2, 1, 2, 2, 0, 1, 2, 2, 2, 2, 0, 1, 2, 2, 2, 2, 1},
            {1, 2, 1, 2, 1, 1, 1, 0, 1, 2, 1, 1, 1, 0, 1, 2, 1, 1, 2, 1},
            {1, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private int[][] mapa; // Matriz que almacenará el estado actual del mapa.

    // Constructor de la clase Mapa.
    public Mapa() {
        this.mapa = new int[MAP_HEIGHT][MAP_WIDTH];  // Inicializa la matriz 'mapa' con el número de filas y columnas definidos.
        // Recorre cada fila del mapa.
        for (int y = 0; y < MAP_HEIGHT; y++) {
            // Recorre cada columna de la fila actual.
            for (int x = 0; x < MAP_WIDTH; x++) {
                // Asigna a la celda (x, y) el valor correspondiente del diseño inicial.
                this.mapa[y][x] = MAPA_INICIAL[y][x];
            }
        }
    }

    // Método que verifica si la celda en la posición (x, y) es una pared.
    public boolean esMuro(int x, int y) {
        return mapa[y][x] == 1; // Retorna true si la celda contiene el valor 1 (pared).
    }

    // Método para simular la acción de comer un coco en la celda (x, y).
    public boolean comerCoco(int x, int y) {
        if (mapa[y][x] == 2) { // Verifica si la celda contiene un coco (valor 2).
            mapa[y][x] = 0; // Cambia el valor de la celda a 0, indicando que el coco ha sido comido.
            return true; // Retorna true indicando que se ha comido un coco.
        }
        return false; // Retorna false si la celda no contenía un coco.
    }

    // Método que dibuja el mapa en la pantalla.
    public void dibujar(Graphics g) {
        // Recorre cada fila del mapa.
        for (int y = 0; y < MAP_HEIGHT; y++) {
            // Recorre cada columna de la fila actual.
            for (int x = 0; x < MAP_WIDTH; x++) {
                // Si la celda contiene una pared (valor 1).
                if (mapa[y][x] == 1) {
                    g.setColor(Color.BLUE); // Establece el color azul para dibujar la pared.
                    // Dibuja un rectángulo relleno en la posición de la celda.
                    g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    // Si la celda contiene un coco (valor 2).
                } else if (mapa[y][x] == 2) {
                    g.setColor(Color.LIGHT_GRAY); // Establece el color gris claro para dibujar el coco.
                    int margin = CELL_SIZE / 4; // Calcula un margen para centrar el dibujo del coco dentro de la celda.
                    int size = CELL_SIZE - 2 * margin; // Calcula el tamaño del coco, restando el doble del margen al tamaño de la celda.
                    // Dibuja un óvalo en la posición de la celda que representa el coco.
                    g.fillOval(x * CELL_SIZE + margin, y * CELL_SIZE + margin, size, size);
                }
            }
        }
    }

    // Método que verifica si aún quedan cocos en el mapa.
    public boolean hayCocosRestantes() {
        // Recorre cada fila del mapa.
        for (int y = 0; y < MAP_HEIGHT; y++) {
            // Recorre cada columna de la fila actual.
            for (int x = 0; x < MAP_WIDTH; x++) {
                // Si la celda contiene un coco (valor 2).
                if (mapa[y][x] == 2) {
                    return true; // Retorna true indicando que todavía hay cocos en el mapa.
                }
            }
        }
        return false; // Si se recorrió todo el mapa sin encontrar cocos
    }
}