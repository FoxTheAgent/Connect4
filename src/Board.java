public class Board {

    // Antal rækker og kolonner i spillet
    public static final int ROWS = 6;
    public static final int COLS = 7;

    // 2D array der gemmer spillets tilstand
    // 0 = tom, 1 = spiller 1, 2 = spiller 2
    public int[][] grid;

    // Constructor: opretter et tomt board
    public Board() {
        grid = new int[ROWS][COLS];
    }

    // drop(): forsøger at lægge en brik i en kolonne
    // returnerer true hvis det lykkes, ellers false
    public boolean drop(int col, int player) {

        // Starter fra bunden og går opad
        for (int r = ROWS - 1; r >= 0; r--) {

            // Hvis feltet er tomt, placeres brikken
            if (grid[r][col] == 0) {
                grid[r][col] = player;
                return true;
            }
        }

        // Hvis kolonnen er fuld
        return false;
    }

    // checkWinner(): tjekker om en spiller har vundet
    public boolean checkWinner(int p) {

        // Gennemgår hele boardet
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {

                // Vandret →
                if (c <= COLS - 4 &&
                        grid[r][c] == p &&
                        grid[r][c+1] == p &&
                        grid[r][c+2] == p &&
                        grid[r][c+3] == p)
                    return true;

                // Lodret ↓
                if (r <= ROWS - 4 &&
                        grid[r][c] == p &&
                        grid[r+1][c] == p &&
                        grid[r+2][c] == p &&
                        grid[r+3][c] == p)
                    return true;

                // Diagonal ↘
                if (r <= ROWS - 4 && c <= COLS - 4 &&
                        grid[r][c] == p &&
                        grid[r+1][c+1] == p &&
                        grid[r+2][c+2] == p &&
                        grid[r+3][c+3] == p)
                    return true;

                // Diagonal ↙
                if (r <= ROWS - 4 && c >= 3 &&
                        grid[r][c] == p &&
                        grid[r+1][c-1] == p &&
                        grid[r+2][c-2] == p &&
                        grid[r+3][c-3] == p)
                    return true;
            }
        }

        // Ingen vinder fundet
        return false;
    }
}