public class Board {
    // Antal rækker og kolonner
    public static final int ROWS = 6;
    public static final int COLS = 7;

    // 2D array som gemmer spillet
    public int[][] grid;

    public Board() {
        // Opretter et tomt spillebræt
        grid = new int[ROWS][COLS];
    }

    public boolean drop(int col, int player) {
        // Starter fra bunden af kolonnen
        for (int r = ROWS - 1; r >= 0; r--) {
            // Hvis feltet er tomt
            if (grid[r][col] == 0) {
                // Læg spillerens brik
                grid[r][col] = player;
                return true; // Succes
            }
        }
        return false; // Kolonnen er fuld
    }

    public boolean checkWinner(int p) {

        // --- Tjek vandret ---
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c <= COLS - 4; c++)
                if (grid[r][c] == p &&
                        grid[r][c+1] == p &&
                        grid[r][c+2] == p &&
                        grid[r][c+3] == p)
                    return true;

        // --- Tjek lodret ---
        for (int r = 0; r <= ROWS - 4; r++)
            for (int c = 0; c < COLS; c++)
                if (grid[r][c] == p &&
                        grid[r+1][c] == p &&
                        grid[r+2][c] == p &&
                        grid[r+3][c] == p)
                    return true;

        // --- Tjek diagonal (ned højre) ---
        for (int r = 0; r <= ROWS - 4; r++)
            for (int c = 0; c <= COLS - 4; c++)
                if (grid[r][c] == p &&
                        grid[r+1][c+1] == p &&
                        grid[r+2][c+2] == p &&
                        grid[r+3][c+3] == p)
                    return true;

        // --- Tjek diagonal (ned venstre) ---
        for (int r = 0; r <= ROWS - 4; r++)
            for (int c = 3; c < COLS; c++)
                if (grid[r][c] == p &&
                        grid[r+1][c-1] == p &&
                        grid[r+2][c-2] == p &&
                        grid[r+3][c-3] == p)
                    return true;

        return false; // Ingen vinder
    }
}