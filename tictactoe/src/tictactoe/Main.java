package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Console welcome (console part)
        System.out.println("==============================================");
        System.out.println("      Welcome to Tic-Tac-Toe");
        System.out.println("==============================================");

        // UI part
        SwingUtilities.invokeLater(Main::createAndShowUI);
    }

    private static void createAndShowUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        frame.setLocationRelativeTo(null);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(Color.WHITE);
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Big playful green welcome sign
        JLabel welcomeLabel = new JLabel(
            "<html><div style='text-align:center;'>"
            + "Welcome to Tic-Tac-Toe.<br>Please put enter your name."
            + "</div></html>",
            JLabel.CENTER
        );
        welcomeLabel.setForeground(new Color(34, 139, 34)); // green
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32)); // medium/playful
        welcomeLabel.setPreferredSize(new Dimension(700, 140));

        // Name input (black text)
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(320, 42));
        nameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        nameField.setForeground(Color.BLACK);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your name.");
                return;
            }

            // Name shown in black in this message
            JOptionPane.showMessageDialog(
                frame,
                "<html><span style='color:black;'>Welcome, " + name + "!</span></html>"
            );

            // Next step would be launching your game screen/board
            // using your existing Board and GameLogic classes.
        });

        gbc.gridy = 0;
        root.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        root.add(nameField, gbc);

        gbc.gridy = 2;
        root.add(startButton, gbc);

        frame.setLayout(new BorderLayout());
        frame.add(root, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}