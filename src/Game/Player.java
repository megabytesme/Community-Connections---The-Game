package Game;

import java.util.ArrayList;

public class Player {
    private static String[] players;
    private int money;
    private int resourceTokens;
    private int permissionTokens;
    private int currentSpace;
    private ArrayList<String> actionLog;

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

    // Getters and setters for money, resourceTokens, and currentSpace
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getResourceTokens() {
        return resourceTokens;
    }

    public void setResourceTokens(int resourceTokens) {
        this.resourceTokens = resourceTokens;
    }

    public int getPermissionTokens() {
        return permissionTokens;
    }

    public void setPermissionTokens(int permissionTokens) {
        this.permissionTokens = permissionTokens;
    }

    public int getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(int currentSpace) {
        this.currentSpace = currentSpace;
    }

    // Function to add an action to the log
    public void logAction(String action) {
        if (actionLog == null) {
            actionLog = new ArrayList<>();
        }
        actionLog.add(action);
    }

    // Function to get the action log
    public ArrayList<String> getActionLog() {
        if (actionLog == null) {
            return new ArrayList<>();
        }
        return actionLog;
    }
}