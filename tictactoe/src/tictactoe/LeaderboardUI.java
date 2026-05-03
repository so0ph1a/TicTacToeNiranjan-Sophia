package tictactoe;

import javax.swing.*;
import java.awt.*;

public class LeaderboardUI extends JFrame {

    private final Color CREAM = new Color(255, 253, 208);
    private final Color GREEN = new Color(0, 120, 0);

    public LeaderboardUI(Leaderboard leaderboard) {

        setTitle("Leaderboard");
        setSize(350, 450);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(CREAM);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 16));
        area.setBackground(CREAM);

        StringBuilder sb = new StringBuilder();
        sb.append("LEADERBOARD\n\n");

        for (String player : leaderboard.getAllPlayers()) {
            sb.append(player)
              .append(" : ")
              .append(leaderboard.getWins(player))
              .append("\n");
        }

        area.setText(sb.toString());

        // bottom button
        JButton home = new JButton("Back to Home");
        home.setBackground(GREEN);
        home.setForeground(Color.WHITE);

        home.addActionListener(e -> {
            dispose();
            new WelcomeUI();
        });

        root.add(new JScrollPane(area), BorderLayout.CENTER);
        root.add(home, BorderLayout.SOUTH);

        add(root);

        setVisible(true);
    }
}