/*
 * Esta clase representa el panel principal del juego.
 * Extiende JPanel para dibujar los elementos del juego e
 * implementa KeyListener y ActionListener para gestionar eventos de teclado y temporización.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements KeyListener, ActionListener {
    private Comecocos comecocos; // Instancia de Comecocos
    private Fantasma fantasma; // Instancia de Fantasma
    private Fantasma fantasma2; // Instancia segundo fantasma

    private Mapa mapa; // Instancia de Mapa
    private Timer timer; // Timer que actualiza la lógica del juego en intervalos de 100 ms

    // Constructor de la clase Game.
    public Game() {
        this.mapa = new Mapa(); // Crea una nueva instancia de Mapa con el diseño inicial.
        this.comecocos = new Comecocos(1, 1, mapa); // Inicializa a Comecocos en la posición (1,1) y le pasa el mapa para interacción.
        this.fantasma = new Fantasma(7, 5, mapa); // Inicializa al Fantasma en la posición
        this.fantasma2 = new Fantasma(13, 7, mapa); // Inicializa al Fantasma en la posición
        setBackground(Color.BLACK); // Establece el color de fondo del panel de juego en negro.
        this.timer = new Timer(100, this); // Crea un Timer que llama al método actionPerformed cada 100 milisegundos.
        timer.start(); // Inicia el Timer para comenzar la actualización del juego.
    }

    // Método paintComponent: se encarga de dibujar todos los elementos del juego en el panel.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método paintComponent de la clase padre para limpiar el área de dibujo.
        mapa.dibujar(g); // Dibuja el mapa (paredes y cocos) usando el método dibujar de Mapa.
        comecocos.dibujar(g); // Dibuja al personaje Comecocos en su posición actual.
        fantasma.dibujar(g); // Dibuja al Fantasma en su posición actual.
        fantasma2.dibujar(g); // dibuja el segundo fantasma en su posición actual
        dibujarPuntos(g); // Dibuja el puntaje obtenido en la parte superior de la pantalla.
    }

    // Método que dibuja el puntaje del jugador en pantalla.
    private void dibujarPuntos(Graphics g) {
        g.setColor(Color.WHITE); // Establece el color del texto en blanco.
        g.drawString("Puntos: " + comecocos.getPuntos(), 10, 20); // Dibuja el texto en la posición (10,20)
    }

    // Método que verifica si Comecocos colisiona con alguno de los fantasmas
    private void verificarColision() {
        // Comprueba si la posición de Comecocos coincide con la del primer fantasma
        // o si la posición de Comecocos coincide con la del segundo fantasma.
        if (comecocos.getX() == fantasma.getX() && comecocos.getY() == fantasma.getY() || comecocos.getX() == fantasma2.getX() && comecocos.getY() == fantasma2.getY()) {
            timer.stop(); // Se detiene el Timer para detener la actualización del juego.
            // Se muestra un cuadro de diálogo indicando que el juego ha terminado y se muestra el puntaje final.
            JOptionPane.showMessageDialog(this, "¡Game Over!\nPuntos: " + comecocos.getPuntos());
            // Se pregunta al usuario si desea continuar jugando, mostrando un diálogo de confirmación.
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres seguir jugando?", "Reiniciar", JOptionPane.YES_NO_OPTION);
            // Si el usuario selecciona "Sí", se reinicia el juego.
            if (respuesta == JOptionPane.YES_OPTION) {
                reiniciarJuego();
            } else {
                System.exit(0); // Si el usuario selecciona "No", se finaliza la ejecución del programa.
            }
        }
    }

    // Método para verificar si se han comido todos los cocos, lo que indica que el jugador ha ganado.
    private void verificarVictoria() {
        // Llama al método hayCocosRestantes para saber si aún quedan cocos en el mapa.
        if (!mapa.hayCocosRestantes()) {
            timer.stop(); // Si no quedan cocos, se detiene el Timer.
            // Muestra un cuadro de diálogo indicando que se ha ganado el juego y muestra el puntaje final.
            JOptionPane.showMessageDialog(this, "¡Ganaste!\nPuntos: " + comecocos.getPuntos());
            // Pregunta al usuario si desea volver a jugar.
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres volver a jugar?", "Reiniciar", JOptionPane.YES_NO_OPTION);
            // Si el usuario selecciona "Sí", se reinicia el juego.
            if (respuesta == JOptionPane.YES_OPTION) {
                reiniciarJuego();
            } else {
                System.exit(0); // Si el usuario selecciona "No", se finaliza el programa.
            }
        }
    }

    // Método que reinicia el juego, creando nuevas instancias de Mapa, Comecocos y Fantasma.
    private void reiniciarJuego() {
        this.mapa = new Mapa(); // Crea una nueva instancia de Mapa y la asigna a la variable mapa.
        this.comecocos = new Comecocos(1, 1, mapa); // Reinicializa a Comecocos en la posición inicial (1,1) con el nuevo mapa.
        this.fantasma = new Fantasma(7, 5, mapa); // Reinicializa al Fantasma en la posición inicial (11,7) con el nuevo mapa.
        this.fantasma2 = new Fantasma(13, 7, mapa); //Reinicializa el segundo Fantasma
        timer.start(); // Reinicia el Timer para continuar la actualización del juego.
        repaint(); // Solicita el repintado del panel para reflejar los cambios.
    }

    // Método actionPerformed: se ejecuta cada vez que el Timer dispara un evento (cada 100 ms).
    @Override
    public void actionPerformed(ActionEvent e) {
        fantasma.mover(); // Mueve al Fantasma en una dirección aleatoria.
        fantasma2.mover();//Mueve al Fantasma 2 en una dirección aleatoria
        verificarColision(); // Verifica si se produjo una colisión entre Comecocos y el Fantasma.
        verificarVictoria(); // Verifica si se han comido todos los cocos (victoria del jugador).
        repaint(); // Solicita que se repinte el panel para actualizar la visualización del juego.
    }

    // Método keyPressed: se invoca cuando el usuario presiona una tecla.
    @Override
    public void keyPressed(KeyEvent e) {
        // Evalúa el código de la tecla presionada.
        switch (e.getKeyCode()) {
            // Si se presiona la tecla de flecha arriba, mueve a Comecocos hacia arriba
            case KeyEvent.VK_UP -> comecocos.mover(0, -1);
            // Si se presiona la tecla de flecha abajo, mueve a Comecocos hacia abajo
            case KeyEvent.VK_DOWN -> comecocos.mover(0, 1);
            // Si se presiona la tecla de flecha izquierda, mueve a Comecocos hacia la izquierda
            case KeyEvent.VK_LEFT -> comecocos.mover(-1, 0);
            // Si se presiona la tecla de flecha derecha, mueve a Comecocos hacia la derecha
            case KeyEvent.VK_RIGHT -> comecocos.mover(1, 0);
        }
        // Solicita el repintado del panel para reflejar el movimiento.
        repaint();
    }

    // Los métodos keyTyped y KeyReleased son requeridos por KeyListener, pero no se utilizan.
    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}
}