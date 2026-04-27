import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {

    // Størrelsen på hver celle (pixel)
    int cellSize = 80;

    // Spil-boardet
    Board board = new Board();

    // Holder styr på hvilken spiller der er i gang (1 eller 2)
    int player = 1;

    // Constructor: opsætter panelet
    public GamePanel() {

        // Sætter størrelsen på panelet
        setPreferredSize(new Dimension(560, 480));

        // Lytter efter museklik
        addMouseListener(this);
    }

    // Tegner spillet på skærmen
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Gennemgår alle felter
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {

                // Bestem farve ud fra spiller
                if (board.grid[r][c] == 1) {
                    g.setColor(Color.RED);
                } else if (board.grid[r][c] == 2) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.WHITE);
                }

                // Tegner firkanten
                g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);

                // Tegner kant rundt om feltet
                g.setColor(Color.BLACK);
                g.drawRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }
    }

    // Kører når brugeren klikker med musen
    public void mouseClicked(MouseEvent e) {

        // Finder hvilken kolonne der blev klikket på
        int col = e.getX() / cellSize;

        // Forsøger at placere brikken
        boolean worked = board.drop(col, player);

        if (worked) {
            repaint();

            // Tjekker om spilleren vandt
            boolean winner = board.checkWinner(player);

            if (winner) {
                String message = "Spiller " + player + " vinder!";

                // Mulige knapper
                Object[] options = {"Spil igen", "Afslut"};

                // Viser dialog
                int choice = JOptionPane.showOptionDialog(
                        this,
                        message,
                        "Spillet er slut",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                // Hvis spilleren vil spille igen
                if (choice == 0) {
                    board = new Board(); // nulstil board
                    player = 1;          // start med spiller 1
                    repaint();
                } else {
                    System.exit(0);     // luk program
                }
                return;
            }

            // Skifter spiller
            player = (player == 1) ? 2 : 1;

            repaint();
        }
    }

    // Ubrugte mouse methods (krævet af MouseListener)
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}