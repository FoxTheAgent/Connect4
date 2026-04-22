import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Opretter et vindue
        JFrame frame = new JFrame("Connect 4");

        // Lukker programmet når man trykker på X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tilføjer spil-panelet til vinduet
        frame.add(new GamePanel());

        // Tilpasser størrelsen til indholdet
        frame.pack();

        // Gør vinduet synligt
        frame.setVisible(true);
    }
}