import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Opretter et nyt vindue (JFrame)
        JFrame frame = new JFrame("Connect 4");

        // Sørger for at programmet lukker når man trykker på X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tilføjer selve spillet (GamePanel) ind i vinduet
        frame.add(new GamePanel());

        // Tilpasser vinduets størrelse til indholdet
        frame.pack();

        // Gør vinduet synligt
        frame.setVisible(true);
    }
}