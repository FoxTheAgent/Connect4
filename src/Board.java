public class Board {
    public static final int ROWS = 6;
    public static final int COLS = 7;

    public int[][] grid;

    public Board() {
        grid = new int[ROWS][COLS];
    }

    public boolean drop(int col, int player) {
        for (int r = ROWS - 1; r >= 0; r--) {
            if (grid[r][col] == 0) {
                grid[r][col] = player;
                return true;
            }
        }
        return false;
    }

    public boolean checkWinner(int p) {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {

                // vandret →
                if (c <= COLS - 4 &&
                        grid[r][c] == p &&
                        grid[r][c+1] == p &&
                        grid[r][c+2] == p &&
                        grid[r][c+3] == p)
                    return true;

                // lodret ↓
                if (r <= ROWS - 4 &&
                        grid[r][c] == p &&
                        grid[r+1][c] == p &&
                        grid[r+2][c] == p &&
                        grid[r+3][c] == p)
                    return true;

                // diagonal ↘
                if (r <= ROWS - 4 && c <= COLS - 4 &&
                        grid[r][c] == p &&
                        grid[r+1][c+1] == p &&
                        grid[r+2][c+2] == p &&
                        grid[r+3][c+3] == p)
                    return true;

                // diagonal ↙
                if (r <= ROWS - 4 && c >= 3 &&
                        grid[r][c] == p &&
                        grid[r+1][c-1] == p &&
                        grid[r+2][c-2] == p &&
                        grid[r+3][c-3] == p)
                    return true;
            }
        }
        return false;
    }
}