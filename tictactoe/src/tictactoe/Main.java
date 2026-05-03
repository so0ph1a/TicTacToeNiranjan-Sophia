package tictactoe;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        SwingUtilities.invokeLater(() -> new WelcomeUI(leaderboard));
    }
}