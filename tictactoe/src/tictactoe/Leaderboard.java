package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Simple in-memory leaderboard for Tic Tac Toe.
 * Stores player wins using normalized names as keys.
 */
public class Leaderboard {

    // Key = normalized player name, Value = total wins
    private final HashMap<String, Integer> winsByPlayer;

    public Leaderboard() {
        winsByPlayer = new HashMap<>();
    }

    /**
     * Normalizes names so duplicates like " Alex ", "alex", and "ALEX"
     * are treated as the same player.
     */
    private String normalizeName(String playerName) {
        if (playerName == null) {
            return "";
        }
        return playerName.trim().toLowerCase();
    }

    /**
     * Adds one win to the given player.
     */
    public void addWin(String playerName) {
        String normalized = normalizeName(playerName);

        // Ignore empty names after normalization
        if (normalized.isEmpty()) {
            return;
        }

        int currentWins = winsByPlayer.getOrDefault(normalized, 0);
        winsByPlayer.put(normalized, currentWins + 1);
    }

    /**
     * Returns how many wins this player has.
     */
    public int getWins(String playerName) {
        String normalized = normalizeName(playerName);
        return winsByPlayer.getOrDefault(normalized, 0);
    }

    /**
     * Returns all player names currently in the leaderboard.
     * Useful for display in Swing UI.
     */
    public Set<String> getAllPlayers() {
        return winsByPlayer.keySet();
    }

    /**
     * Returns the full score map for display purposes.
     * (Read-only view to avoid accidental direct edits.)
     */
    public Map<String, Integer> getAllScores() {
        return new HashMap<>(winsByPlayer);
    }
}

