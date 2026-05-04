package tictactoe;

import javax.swing.*;
import java.awt.*;

public class LeaderboardUI extends JFrame {

	public LeaderboardUI(Leaderboard lb) {

		setTitle("Leaderboard");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout()); // IMPORTANT FIX
		getContentPane().setBackground(new Color(253, 251, 212));

		JLabel area = new JLabel();
		area.setBackground(new Color(253, 251, 212));
		area.setForeground(new Color(0, 202, 177));
		area.setFont(new Font("Jungle Adventurer", Font.BOLD, 28));

		StringBuilder sb = new StringBuilder();
		setLayout(new BorderLayout());
		JLabel title = new JLabel("LEADERBOARD", JLabel.CENTER);
		title.setFont(new Font("Jungle Adventurer", Font.BOLD, 40));
		title.setForeground(new Color(0, 202, 177));

		lb.getAllPlayers().stream()
	    .sorted((a, b) -> Integer.compare(lb.getWins(b), lb.getWins(a)))
	    .forEach(player -> 
	        sb.append(String.format("  %-10s %d%n", player, lb.getWins(player)))
	    );

		area.setText("<html><pre style='font-family: monospace;'>" 
		        + sb.toString() 
		        + "</pre></html>");

		JButton back = new JButton("Back to Home");

		style(back);

		back.addActionListener(e -> {
			dispose(); // close leaderboard safely
			SwingUtilities.invokeLater(() -> new WelcomeUI(lb)); // safe UI thread
		});

		add(title, BorderLayout.NORTH);
		add(area, BorderLayout.CENTER);
		add(back, BorderLayout.SOUTH);

		setVisible(true);
	}

	private void style(JButton b) {
		b.setForeground(new Color(0, 202, 177));
		b.setBackground(Color.WHITE);
		b.setFont(new Font("Jungle Adventurer", Font.BOLD, 16));
		b.setBorder(BorderFactory.createLineBorder(new Color(0, 202, 177), 2));
		b.setOpaque(true);
		b.setContentAreaFilled(true);
		b.setFocusPainted(false);
	}
}