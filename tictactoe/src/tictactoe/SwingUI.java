package tictactoe;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SwingUI {

    private final Board board;
    private final GameLogic logic;
    private final JButton[][] buttons;

    public SwingUI() {
        board = new Board("board.csv");
        logic = new GameLogic();
        buttons = new JButton[3][3];

        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                JButton button = new JButton("");
                buttons[row][col] = button;

                int r = row;
                int c = col;

                button.addActionListener(e -> handleMove(r, c));

                frame.add(button);
            }
        }

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleMove(int row, int col) {

        if (board.getCell(row, col) != 'E') {
            return;
        }

        char player = logic.getCurrentPlayer(board);
        board.setCell(row, col, player);

        buttons[row][col].setText(String.valueOf(player));

        if (logic.checkWin(board, player)) {
            JOptionPane.showMessageDialog(null, player + " wins");
            resetGame();
        } else if (logic.isDraw(board)) {
            JOptionPane.showMessageDialog(null, "Draw");
            resetGame();
        }
    }

    private void resetGame() {
        board.clearBoard();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingUI::new);
    }
}
