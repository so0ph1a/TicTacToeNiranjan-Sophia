package tictactoe;

import javax.swing.*;
import java.awt.*;

public class WelcomeUI extends JFrame {

    private final JTextField p1 = new JTextField();
    private final JTextField p2 = new JTextField();

    private final Leaderboard leaderboard = new Leaderboard();

    private final Color CREAM = new Color(255, 253, 208);
    private final Color GREEN = new Color(0, 120, 0);

    public WelcomeUI() {

        setTitle("Tic Tac Toe");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new GridLayout(5, 1));
        root.setBackground(CREAM);

        JLabel title = new JLabel("WELCOME TO TIC-TAC-TOE!", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(GREEN);

        JButton play = new JButton("PLAY!");
        JButton lb = new JButton("LEADERBOARD");

        play.setBackground(GREEN);
        play.setForeground(Color.WHITE);

        lb.setBackground(GREEN);
        lb.setForeground(Color.WHITE);

        p1.setBorder(BorderFactory.createTitledBorder("Player 1 (X)"));
        p2.setBorder(BorderFactory.createTitledBorder("Player 2 (O)"));

        play.addActionListener(e -> startGame());
        lb.addActionListener(e -> new LeaderboardUI(leaderboard));

        root.add(title);
        root.add(p1);
        root.add(p2);
        root.add(play);
        root.add(lb);

        add(root);

        setVisible(true);
    }

    private void startGame() {
        String a = p1.getText().trim();
        String b = p2.getText().trim();

        if (a.isEmpty() || b.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter both names");
            return;
        }

        new SwingUI(a, b, leaderboard);
        dispose();
    }
}