package tictactoe;

public class GameLogic {

	public boolean checkWin(Board board, char player) {
		// rows
		for (int i = 0; i < 3; i++) {
			if (board.getCell(i, 0) == player && board.getCell(i, 1) == player && board.getCell(i, 2) == player)
				return true;
		}

		// cols
		for (int i = 0; i < 3; i++) {
			if (board.getCell(0, i) == player && board.getCell(1, i) == player && board.getCell(2, i) == player)
				return true;
		}

		// diagonals
		if (board.getCell(0, 0) == player && board.getCell(1, 1) == player && board.getCell(2, 2) == player)
			return true;

		if (board.getCell(0, 2) == player && board.getCell(1, 1) == player && board.getCell(2, 0) == player)
			return true;

		return false;
	}

	public boolean isGameOver(Board board) {
		return checkWin(board, 'X') || checkWin(board, 'O') || isDraw(board);
	}

	public boolean isDraw(Board board) {
		if (checkWin(board, 'X') || checkWin(board, 'O'))
			return false;

		for (char[] row : board.getGrid()) {
			for (char cell : row) {
				if (cell == 'E')
					return false;
			}
		}
		return true;
	}

	public char getCurrentPlayer(Board board) {
		int x = 0, o = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char c = board.getCell(i, j);
				if (c == 'X')
					x++;
				if (c == 'O')
					o++;
			}
		}

		return (x <= o) ? 'X' : 'O';
	}

	public boolean makeMove(Board board, int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2)
			return false;

		if (board.getCell(row, col) != 'E')
			return false;

		char player = getCurrentPlayer(board);
		board.setCell(row, col, player);

		return true;
	}
}