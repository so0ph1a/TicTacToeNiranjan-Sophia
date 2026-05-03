package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Leaderboard {

    // Key = trimmed EXACT name (case-sensitive, space-safe)
    private final HashMap<String, Integer> winsByPlayer;

    public Leaderboard() {
        winsByPlayer = new HashMap<>();
    }

    /**
     * Clean input ONLY removes spaces, keeps case intact.
     */
    private String clean(String name) {
        if (name == null) return "";
        return name.trim();
    }

    /**
     * Add player if not already exists (case-sensitive, space-insensitive).
     */
    public void ensurePlayer(String name) {
        String key = clean(name);

        if (key.isEmpty()) return;

        if (!winsByPlayer.containsKey(key)) {
            winsByPlayer.put(key, 0);
        }
    }

    /**
     * Checks duplicate using trimmed version only.
     */
    public boolean playerExists(String name) {
        String key = clean(name);

        return winsByPlayer.containsKey(key);
    }

    /**
     * Adds win to exact stored player.
     */
    public void addWin(String playerName) {
        String key = clean(playerName);

        if (key.isEmpty()) return;

        int current = winsByPlayer.getOrDefault(key, 0);
        winsByPlayer.put(key, current + 1);
    }

    /**
     * Gets wins for exact stored player.
     */
    public int getWins(String playerName) {
        String key = clean(playerName);
        return winsByPlayer.getOrDefault(key, 0);
    }

    public Set<String> getAllPlayers() {
        return winsByPlayer.keySet();
    }

    public Map<String, Integer> getAllScores() {
        return new HashMap<>(winsByPlayer);
    }
}