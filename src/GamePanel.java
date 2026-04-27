import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {

    int cellSize = 80;
    Board board = new Board();
    int player = 1;

    public GamePanel() {
        setPreferredSize(new Dimension(560, 480));
        addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {

                if (board.grid[r][c] == 1) {
                    g.setColor(Color.RED);
                } else if (board.grid[r][c] == 2) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);

                g.setColor(Color.BLACK);
                g.drawRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        int col = e.getX() / cellSize;

        boolean worked = board.drop(col, player);

        if (worked) {
            repaint();

            boolean winner = board.checkWinner(player);

            if (winner) {
                String message = "Spiller " + player + " vinder!";

                Object[] options = {"Spil igen", "Afslut"};
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

                if (choice == 0) {
                    board = new Board();
                    player = 1;
                    repaint();
                } else {
                    System.exit(0);
                }
                return;
            }

            player = (player == 1) ? 2 : 1;
            repaint();
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}