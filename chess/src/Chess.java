public class Chess {
    private int[][] board;


    public Chess() {
        board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
            }
        }
        placeQueens();
    }

    void placeQueens() {
        int r = 0;
        for (int i = 0; i < 8; i++) {
            if (!queenInRow(i)) {
            while (!placeQueenRow(i) && r < 8) {
                clearRow(r);
                r++;
            }
            r = 0;
            }
        }
        if (!EightQueens()) {
            placeQueens();
        }
    }

    boolean placeQueenRow(int row) {
        for (int j = 0; j < 8; j++) {
            if (alive(row,j)) {
                board[row][j] = 1;
                return true;
            }
        }
        return false;
    }

    boolean alive(int r, int c) {
        return checkHandV(r, c) && checkDiag(r, c);
    }

    boolean checkDiag(int r, int c) {
        int i = r;
        int j = c;
        while (i >= 0 && i < 8 && j >= 0 && j < 8) {
            if (board[i][j] > 0) {
                return false;
            }
            i--;
            j--;
        }

        i = r;
        j = c;
        while (i >= 0 && i < 8 && j >= 0 && j < 8) {
            if (board[i][j] > 0) {
                return false;
            }
            i++;
            j++;
        }

        i = r;
        j = c;
        while (i >= 0 && i < 8 && j >= 0 && j < 8) {
            if (board[i][j] > 0) {
                return false;
            }
            i--;
            j++;
        }

        i = r;
        j = c;
        while (i >= 0 && i < 8 && j >= 0 && j < 8) {
            if (board[i][j] > 0) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    boolean checkHandV(int r, int c) {
        for (int i = 0; i < 8; i++) {
            if (i != c) {
                if (board[r][i] > 0) {
                    return false;
                }
            }
            if (i != r) {
                if (board[i][c] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "    ");
            }
            System.out.println("\n");
        }
    }

    public void clearRow(int r) {
        for (int j = 0; j < 8; j++) {
            if (board[r][j] > 0) {
                board[r][j] = 0;
                break;
            }
        }
    }

    boolean EightQueens() {
        for (int i = 0; i < 8; i++) {
            if (!queenInRow(i)) {
                return false;
            }
        }
        return true;
    }

    boolean queenInRow(int r) {
        for (int j = 0; j < 8; j++) {
            if (board[r][j] > 0) {
                return true;
            }
        }
        return false;
    }
}