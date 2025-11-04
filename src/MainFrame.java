import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private final Game game;
    private final TTTTileButton[][] buttons = new TTTTileButton[3][3];
    private final JLabel statusLabel = new JLabel("Player X's turn");
    private boolean gameOver = false;

    public MainFrame() {
        this.game = new Game();

        setTitle("OOP Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 2, 2));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                TTTTileButton btn = new TTTTileButton(row, col);
                btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
                btn.addActionListener(e -> handleMove(btn));
                buttons[row][col] = btn;
                boardPanel.add(btn);
            }
        }

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(resetButton, BorderLayout.EAST);

        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setSize(400, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleMove(TTTTileButton btn) {
        if (gameOver) return;

        int row = btn.getRow();
        int col = btn.getCol();
        String symbol = game.getCurrentPlayer().getSymbol();

        if (game.getBoard().placeSymbol(row, col, symbol)) {
            btn.setText(symbol);

            if (game.getBoard().checkWin(symbol)) {
                statusLabel.setText("Player " + symbol + " wins!");
                gameOver = true;
                disableBoard();
                return;
            }

            if (game.getBoard().isFull()) {
                statusLabel.setText("It's a draw!");
                gameOver = true;
                disableBoard();
                return;
            }

            game.switchTurn();
            statusLabel.setText("Player " + game.getCurrentPlayer().getSymbol() + "'s turn");
        }
    }

    private void disableBoard() {
        for (TTTTileButton[] row : buttons)
            for (TTTTileButton btn : row)
                btn.setEnabled(false);
    }

    private void enableBoard() {
        for (TTTTileButton[] row : buttons)
            for (TTTTileButton btn : row)
                btn.setEnabled(true);
    }

    private void resetGame() {
        game.resetGame();
        gameOver = false;
        statusLabel.setText("Player X's turn");
        for (TTTTileButton[] row : buttons)
            for (TTTTileButton btn : row) {
                btn.setText("");
                btn.setEnabled(true);
            }
        enableBoard();
        repaint();
    }
}