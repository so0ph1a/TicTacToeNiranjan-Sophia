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
                char currentPlayer = logic.getCurrentPlayer(board);
                System.out.println("Current player: " + currentPlayer);

                boolean moveMade = false;

                while (!moveMade) {
                    System.out.print("Enter row (0-2): ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter a number.");
                        scanner.next();
                        continue;
                    }
                    int row = scanner.nextInt();

                    System.out.print("Enter column (0-2): ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter a number.");
                        scanner.next();
                        continue;
                    }
                    int col = scanner.nextInt();

                    moveMade = logic.makeMove(board, row, col);

                    if (!moveMade) {
                        System.out.println("Invalid move. Try again.");
                    }
                }
            }

            board.printGrid();

            if (logic.checkWin(board, 'X')) {
                System.out.println("X wins");
            } else if (logic.checkWin(board, 'O')) {
                System.out.println("O wins");
            } else {
                System.out.println("Draw");
            }

            System.out.print("Play again? (yes/no): ");
            String answer = scanner.next();
            playAgain = answer.equalsIgnoreCase("yes");
        }

        scanner.close();
    }
}
