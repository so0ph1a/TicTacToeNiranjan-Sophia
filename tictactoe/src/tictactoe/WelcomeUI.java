package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class WelcomeUI extends JFrame {

    private final Leaderboard leaderboard;

    private final JTextField p1New = new JTextField();
    private final JTextField p2New = new JTextField();

    private final JComboBox<String> p1Select = new JComboBox<>();
    private final JComboBox<String> p2Select = new JComboBox<>();

    public WelcomeUI(Leaderboard lb) {
        this.leaderboard = lb;

        setTitle("Tic Tac Toe");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new GridLayout(7, 1, 5, 5));
        root.setBackground(new Color(255, 253, 208));

        JLabel title = new JLabel("WELCOME", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.BLACK);

        // Load existing players into dropdowns
        refreshDropdowns();

        JButton play = new JButton("PLAY");
        JButton leaderboardBtn = new JButton("LEADERBOARD");

        style(play);
        style(leaderboardBtn);

        // PLAY BUTTON
        play.addActionListener(e -> {

        	String player1 = getPlayerName(p1Select, p1New).trim();
        	String player2 = getPlayerName(p2Select, p2New).trim();

            if (player1.isEmpty() || player2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter both player names!");
                return;
            }
            if (player1.equals(player2)) {
                JOptionPane.showMessageDialog(this,
                        "Both players cannot have the same name. Please choose different names.");
                return;
            }

            // 🔴 FIX GOES HERE (duplicate typed-name protection)

            boolean p1TypedNew = !p1New.getText().trim().isEmpty();
            boolean p2TypedNew = !p2New.getText().trim().isEmpty();

            if (p1TypedNew && leaderboard.playerExists(player1)) {
                JOptionPane.showMessageDialog(this,
                        "User already taken. Please pick a new name or select from existing players.");
                return;
            }

            if (p2TypedNew && leaderboard.playerExists(player2)) {
                JOptionPane.showMessageDialog(this,
                        "User already taken. Please pick a new name or select from existing players.");
                return;
            }

            leaderboard.ensurePlayer(player1);
            leaderboard.ensurePlayer(player2);

            new SwingUI(player1, player2, leaderboard);
            dispose();
        });
        
        // LEADERBOARD BUTTON
        leaderboardBtn.addActionListener(e -> {
            new LeaderboardUI(leaderboard);
            dispose();
        });

        // UI layout
        p1New.setBorder(BorderFactory.createTitledBorder("New Player 1"));
        p2New.setBorder(BorderFactory.createTitledBorder("New Player 2"));

        p1Select.setBorder(BorderFactory.createTitledBorder("Existing Player 1"));
        p2Select.setBorder(BorderFactory.createTitledBorder("Existing Player 2"));

        root.add(title);
        root.add(p1Select);
        root.add(p1New);
        root.add(p2Select);
        root.add(p2New);
        root.add(play);
        root.add(leaderboardBtn);

        add(root);
        setVisible(true);
    }

    // Gets either dropdown selection OR new typed name
    private String getPlayerName(JComboBox<String> select, JTextField text) {

        String typed = text.getText().trim();

        if (!typed.isEmpty()) {
            return typed;
        }

        Object selected = select.getSelectedItem();

        if (selected != null) {
            return selected.toString();
        }

        return "";
    }

    // Load leaderboard names into dropdowns
    private void refreshDropdowns() {

        Set<String> players = leaderboard.getAllPlayers();

        p1Select.addItem("Select existing player");
        p2Select.addItem("Select existing player");

        for (String p : players) {
            p1Select.addItem(p);
            p2Select.addItem(p);
        }
    }

    private void style(JButton b) {
        b.setForeground(Color.BLACK);
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setFocusPainted(false);
    }
}