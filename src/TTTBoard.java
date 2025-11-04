public class TTTBoard {

    private String[][] board;

    public TTTBoard() {
        board = new String[3][3];
    }

    public boolean placeSymbol(int row, int col, String symbol) {
        if (board[row][col] == null) {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    public String getSymbol(int row, int col) {
        return board[row][col];
    }

    public boolean checkWin(String symbol) {
        for (int i = 0; i < 3; i++) {
            if (symbol.equals(board[i][0]) && symbol.equals(board[i][1]) && symbol.equals(board[i][2])) return true;
            if (symbol.equals(board[0][i]) && symbol.equals(board[1][i]) && symbol.equals(board[2][i])) return true;
        }
        return symbol.equals(board[0][0]) && symbol.equals(board[1][1]) && symbol.equals(board[2][2]) ||
                symbol.equals(board[0][2]) && symbol.equals(board[1][1]) && symbol.equals(board[2][0]);
    }

    public boolean isFull() {
        for (String[] row : board)
            for (String cell : row)
                if (cell == null) return false;
        return true;
    }

    public void reset() {
        board = new String[3][3];
    }
}