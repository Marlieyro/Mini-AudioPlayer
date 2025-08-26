import gui.swingGUI;
import javax.swing.*;


public class Main {
    @SuppressWarnings("uncheked")
    public static void main(String[] args) throws InterruptedException {

        SwingUtilities.invokeLater(() -> {
            swingGUI gui = new swingGUI();
            gui.setVisible(true);

            // Приклад використання методів для зміни стану
            gui.setSongTitle("Нова пісня - Виконавець");
            gui.setPlaying(true);
        });

    }
}
