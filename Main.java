/*
 * Esta clase es la entrada principal del juego.
 * Extiende JFrame para crear una ventana gráfica.
 */

import javax.swing.*;

public class Main extends JFrame {
    private Game gamePanel; // Declaración de la variable gamePanel de tipo Game, que contiene la lógica y visualización del juego.

    // Constructor de la clase Main.
    public Main() {
        setTitle("Comecocos"); // Establece el título de la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación al cerrar la ventana.
        setSize(Mapa.MAP_WIDTH * Mapa.CELL_SIZE, Mapa.MAP_HEIGHT * Mapa.CELL_SIZE); // Establece el tamaño de la ventana.
        setResizable(false); // Impide que la ventana sea redimensionable por el usuario.
        gamePanel = new Game(); // Crea una nueva instancia del panel de juego y la asigna a la variable gamePanel.
        add(gamePanel); // Agrega el panel de juego a la ventana.
        addKeyListener(gamePanel); // Agrega un detector de teclas para que el juego responda a las pulsaciones del usuario.
        setVisible(true); // Hace visible la ventana en la pantalla.
    }

    // Punto de entrada del programa.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new); // Ejecuta la creación de la interfaz gráfica en el hilo de eventos de Swing.
    }
}