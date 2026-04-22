import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements MouseListener {

    // Størrelse på hver celle (pixel)
    static final int CELL = 80;

    // Spillebræt
    Board board = new Board();

    // Starter med spiller 1
    int player = 1;

    public GamePanel() {
        // Sætter størrelse på panelet
        setPreferredSize(new Dimension(Board.COLS * CELL, Board.ROWS * CELL));

        // Gør så vi kan registrere museklik
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Rydder skærmen

        // Gennemgår hele gridet
        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLS; c++) {

                // Vælg farve baseret på spiller
                if (board.grid[r][c] == 1)
                    g.setColor(Color.RED);
                else if (board.grid[r][c] == 2)
                    g.setColor(Color.BLUE);
                else
                    g.setColor(Color.WHITE);

                // Tegn selve feltet
                g.fillRect(c * CELL, r * CELL, CELL, CELL);

                // Tegn kanten
                g.setColor(Color.BLACK);
                g.drawRect(c * CELL, r * CELL, CELL, CELL);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        // Finder hvilken kolonne der blev klikket
        int col = e.getX() / CELL;

        // Prøv at droppe en brik
        if (board.drop(col, player)) {

            // Tjek om spilleren har vundet
            if (board.checkWinner(player)) {
                repaint(); // Opdater skærm

                // Vis vinder-besked
                JOptionPane.showMessageDialog(this, "Spiller " + player + " vinner!");

                // Reset spillet
                board = new Board();
                player = 1;
                repaint();
                return;
            }

            // Skift spiller (1 → 2 eller 2 → 1)
            player = (player == 1) ? 2 : 1;

            // Opdater skærm
            repaint();
        }
    }

    // Disse metoder skal være der, men bruges ikke
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}