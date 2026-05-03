package tictactoe;

import java.awt.*;
import javax.swing.*;

public class SwingUI extends JFrame {

    private final String player1Name;
    private final String player2Name;

    private final Board board;
    private final GameLogic logic;
    private final Leaderboard leaderboard;

    private final JLabel statusLabel = new JLabel("", JLabel.CENTER);
    private final JButton[][] cells = new JButton[3][3];

    private final Color CREAM = new Color(255, 253, 208);
    private final Color GREEN = new Color(0, 120, 0);

    public SwingUI(String p1, String p2, Leaderboard lb) {
        this.player1Name = p1;
        this.player2Name = p2;
        this.leaderboard = lb;

        this.board = new Board("board.csv");
        this.logic = new GameLogic();

        buildUi();
        startNewGame();

        setVisible(true);
    }

    private void buildUi() {
        setTitle("Tic Tac Toe");
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(CREAM);

        // TURN LABEL
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(GREEN);
        statusLabel.setOpaque(true);
        statusLabel.setBackground(CREAM);

        // GRID
        JPanel grid = new JPanel(new GridLayout(3, 3, 5, 5));
        grid.setBackground(CREAM);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {

                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 45));
                btn.setForeground(Color.BLACK);
                btn.setBackground(Color.WHITE);

                int row = r;
                int col = c;

                btn.addActionListener(e -> handleMove(row, col));

                cells[r][c] = btn;
                grid.add(btn);
            }
        }

        // BOTTOM BUTTON (HOME)
        JPanel bottom = new JPanel();
        bottom.setBackground(CREAM);

        JButton homeBtn = new JButton("Cancel / Return Home");
        homeBtn.setBackground(GREEN);
        homeBtn.setForeground(Color.WHITE);

        homeBtn.addActionListener(e -> {
            dispose();
            new WelcomeUI();
        });

        bottom.add(homeBtn);

        root.add(statusLabel, BorderLayout.NORTH);
        root.add(grid, BorderLayout.CENTER);
        root.add(bottom, BorderLayout.SOUTH);

        add(root);
    }

    private void startNewGame() {
        board.clearBoard();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                cells[r][c].setText("");
                cells[r][c].setEnabled(true);
            }
        }

        refreshUI();
    }

    private void handleMove(int r, int c) {

        if (logic.isGameOver(board)) return;

        if (!logic.makeMove(board, r, c)) return;

        refreshUI();

        if (logic.checkWin(board, 'X')) {
            leaderboard.addWin(player1Name);
            endGame(player1Name + " (X) wins!");
        }
        else if (logic.checkWin(board, 'O')) {
            leaderboard.addWin(player2Name);
            endGame(player2Name + " (O) wins!");
        }
        else if (logic.isDraw(board)) {
            endGame("Draw!");
        }
    }

    private void refreshUI() {

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                char val = board.getCell(r, c);
                cells[r][c].setText(val == 'E' ? "" : String.valueOf(val));
            }
        }

        char turn = logic.getCurrentPlayer(board);
        String name = (turn == 'X') ? player1Name : player2Name;

        statusLabel.setText(name + "'s Turn (" + turn + ")");
    }

    private void endGame(String msg) {
        JOptionPane.showMessageDialog(this, msg);
        startNewGame();
    }
}