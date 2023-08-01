package Game;

public class Player {
    private static String[] players;

    // Function to set player names
    public static void setPlayers(String[] playerNames) {
        players = playerNames;
    }

    // Function to get player names
    public static String[] getPlayers() {
        // If players are not set, return an empty array
        if (players == null) {
            return new String[0];
        }
        return players;
    }
}