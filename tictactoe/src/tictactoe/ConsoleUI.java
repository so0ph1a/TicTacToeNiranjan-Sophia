package tictactoe;

import java.util.Scanner;

public class ConsoleUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            Board board = new Board("board.csv");
            GameLogic logic = new GameLogic();

            while (!logic.isGameOver(board)) {

                board.printGrid();

                // FIX: don't rely on board scanning logic
                char currentPlayer = logic.getCurrentPlayer(board);
                System.out.println("Current player: " + currentPlayer);

                int row = -1;
                int col = -1;

                boolean moveMade = false;

                while (!moveMade) {

                    // ROW INPUT
                    System.out.print("Enter row (0-2): ");
                    if (scanner.hasNextInt()) {
                        row = scanner.nextInt();
                    } else {
                        System.out.println("Please enter a number.");
                        scanner.next();
                        continue;
                    }

                    // COL INPUT
                    System.out.print("Enter column (0-2): ");
                    if (scanner.hasNextInt()) {
                        col = scanner.nextInt();
                    } else {
                        System.out.println("Please enter a number.");
                        scanner.next();
                        continue;
                    }

                    // RANGE CHECK
                    if (row < 0 || row > 2 || col < 0 || col > 2) {
                        System.out.println("Row and column must be between 0 and 2.");
                        continue;
                    }

                    moveMade = logic.makeMove(board, row, col);

                    if (!moveMade) {
                        System.out.println("Invalid move. Try again.");
                    }
                }
            }

            board.printGrid();

            if (logic.checkWin(board, 'X')) {
                System.out.println("X wins!");
            } else if (logic.checkWin(board, 'O')) {
                System.out.println("O wins!");
            } else {
                System.out.println("It's a draw!");
            }

            System.out.print("Play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        scanner.close();
    }
}