package tictactoe;

import javax.swing.*;
import java.awt.*;

public class LeaderboardUI extends JFrame {

	public LeaderboardUI(Leaderboard lb) {

		setTitle("Leaderboard");
		setSize(300, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout()); // IMPORTANT FIX

		JTextArea area = new JTextArea();
		area.setEditable(false);
		area.setForeground(Color.BLACK);
		area.setFont(new Font("Arial", Font.PLAIN, 14));

		StringBuilder sb = new StringBuilder();
		sb.append("LEADERBOARD\n\n");

		for (String player : lb.getAllPlayers()) {
			sb.append(player).append(" : ").append(lb.getWins(player)).append("\n");
		}

		area.setText(sb.toString());

		JButton back = new JButton("Back to Home");

		style(back);

		back.addActionListener(e -> {
			dispose(); // close leaderboard safely
			SwingUtilities.invokeLater(() -> new WelcomeUI(lb)); // safe UI thread
		});

		add(new JScrollPane(area), BorderLayout.CENTER);
		add(back, BorderLayout.SOUTH);

		setVisible(true);
	}

	private void style(JButton b) {
		b.setForeground(Color.BLACK);
		b.setBackground(Color.WHITE);
		b.setOpaque(true);
		b.setContentAreaFilled(true);
		b.setFocusPainted(false);
	}
}