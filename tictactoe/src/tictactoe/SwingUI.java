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
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel root = new JPanel(new BorderLayout());
		root.setBackground(new Color(253, 251, 212));

		statusLabel.setFont(new Font("Jungle Adventurer", Font.BOLD, 22));
		statusLabel.setForeground(new Color(0, 202, 177));

		JPanel grid = new JPanel(new GridLayout(3, 3, 5, 5));
		grid.setBackground(new Color(253, 251, 212));

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				JButton btn = new JButton("");
				btn.setFont(new Font("Jungle Adventurer", Font.BOLD, 80));
				styleButton(btn);

				int row = r;
				int col = c;

				btn.addActionListener(e -> handleMove(row, col));

				cells[r][c] = btn;
				grid.add(btn);
			}
		}

		JButton home = new JButton("Cancel / Home");
		styleButton(home);

		home.addActionListener(e -> {
			dispose();
			new WelcomeUI(leaderboard);
		});

		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(253, 251, 212));
		bottom.add(home);

		root.add(statusLabel, BorderLayout.NORTH);
		root.add(grid, BorderLayout.CENTER);
		root.add(bottom, BorderLayout.SOUTH);

		add(root);
	}

	private void styleButton(JButton b) {
		b.setForeground(new Color(0, 202, 177));;
		b.setBackground(Color.WHITE);
		b.setOpaque(true);
		b.setContentAreaFilled(true);
		b.setFocusPainted(false);
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

		if (logic.isGameOver(board))
			return;
		if (board.getCell(r, c) != 'E')
			return;

		logic.makeMove(board, r, c);

		refreshUI();

		if (logic.checkWin(board, 'X')) {
			leaderboard.addWin(player1Name);
			endGame(player1Name + " (X) wins!");
		} else if (logic.checkWin(board, 'O')) {
			leaderboard.addWin(player2Name);
			endGame(player2Name + " (O) wins!");
		} else if (logic.isDraw(board)) {
			endGame("Draw!");
		}
	}

	private void refreshUI() {
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				char v = board.getCell(r, c);
				cells[r][c].setText(v == 'E' ? "" : String.valueOf(v));
			}
		}

		char turn = logic.getCurrentPlayer(board);
		String name = (turn == 'X') ? player1Name : player2Name;

		statusLabel.setText(name + "'s Turn (" + turn + ")");
	}

	private void endGame(String msg) {

		JOptionPane.showMessageDialog(this, msg);

		Object[] options = { "Play Again", "Home", "Leaderboard" };

		int choice = JOptionPane.showOptionDialog(this, "What next?", "Game Over", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if (choice == 0) {
			startNewGame();
		}

		else if (choice == 1) {
			dispose();
			new WelcomeUI(leaderboard);
		}

		else if (choice == 2) {
			dispose(); // 🔥 THIS FIXES YOUR PROBLEM
			new LeaderboardUI(leaderboard);
		}
	}
}