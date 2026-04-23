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
                    g.fillRect(c * 80, r * 80, 80, 80);
                }
                if (board.grid[r][c] == 2) {
                    g.setColor(Color.BLUE);
                    g.fillRect(c * 80, r * 80, 80, 80);
                }
                if (board.grid[r][c] == 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(c * 80, r * 80, 80, 80);
                }

                g.setColor(Color.BLACK);
                g.drawRect(c * 80, r * 80, 80, 80);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        int col = e.getX() / 80;

        boolean worked = board.drop(col, player);

        if (worked == true) {
            repaint();

            boolean winner = board.checkWinner(player);

            if (winner == true) {
                String message = "Spiller " + player + " vinder!";
                JOptionPane.showMessageDialog(this, message);

                board = new Board();
                player = 1;
                repaint();
                return;
            }

            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }

            repaint();
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}