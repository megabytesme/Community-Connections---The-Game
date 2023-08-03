import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private String mark;
    private int position;
    private int resourceTokens;
    private PlayerProgress playerProgress;
    private static Scanner scanner = new Scanner(System.in);
    private int x;
    private int y;
    private List<PlayerProperty> playerProperties = new ArrayList<>();
    private GameBoard gameBoard;
    private int currentPropertyIndex; // New field to keep track of the current property index

    
    public Player(String name, String mark, GameBoard gameBoard) {
        this.name = name;
        this.mark = mark;
        this.position = 0;
        this.resourceTokens = 2;
        this.playerProgress = new PlayerProgress(4); // Set the initial number of tasks (4 in this case)
        this.gameBoard = gameBoard;
        this.currentPropertyIndex = 0; // Initialize the current property index to 0
    }

    public void addProperty(PlayerProperty property) {
        playerProperties.add(property);
    }

    public PlayerProperty getCurrentProperty() {
        if (currentPropertyIndex >= 0 && currentPropertyIndex < playerProperties.size()) {
            return playerProperties.get(currentPropertyIndex);
        }
        return null; // If the currentPropertyIndex is out of range, return null
    }

    public void nextProperty() {
        currentPropertyIndex++;
    }

    public void resetPropertyIndex() {
        currentPropertyIndex = 0;
    }

    public int rollDice() {
        return gameBoard.rollDice();
    }

    public List<PlayerProperty> getPlayerProperties() {
        return playerProperties;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getResources() {
        return resourceTokens;
    }

    public void addResources(int resources) {
        this.resourceTokens += resources;
    }

    // New method to set the player's mark
    public void setMark(String mark) {
        this.mark = mark;
    }

    // New method to get the player's mark
    public String getMark() {
        return mark;
    }

    public void addResourceToken() {
        resourceTokens++;
    }

    public void useResourceToken(int count) {
        if (resourceTokens >= count) { // Check if there are enough available resource tokens
            resourceTokens -= count;
        } else {
            System.out.println("You don't have enough resource tokens to perform this task.");
        }
    }

    public int getResourceTokens() {
        return resourceTokens;
    }

    public int getPropertyStage() {
        return playerProgress.getPropertyStage();
    }

    public void incrementPropertyStage() {
        playerProgress.incrementPropertyStage();
    }

    public PlayerProgress getPlayerProgress() {
        return playerProgress;
    }

    public void setPlayerProgress(PlayerProgress playerProgress) {
        this.playerProgress = playerProgress;
    }

    public static void closeScanner() {
        scanner.close();
    }

    public void performTask(Square square) {
        square.performTask(this, playerProgress);
    }

    // Method to check if a property is fully enhanced
    public boolean isPropertyFullyEnhanced(PlayerProperty property) {
        return property.hasPermission() && property.isHardwareInstalled() && property.isEducationCompleted();
    }

    // Method to remove fully enhanced properties
    public void removeFullyEnhancedProperties() {
        List<PlayerProperty> propertiesToRemove = new ArrayList<>();
        for (PlayerProperty property : playerProperties) {
            if (isPropertyFullyEnhanced(property)) {
                propertiesToRemove.add(property);
            }
        }
        playerProperties.removeAll(propertiesToRemove);
        for (PlayerProperty property : propertiesToRemove) {
            property.setPermission(false);
        }
        // check if there are any properties left
        if (playerProperties.size() == 0) {
            // if no properties left, player has won the game. set boolean to true to end the game
            gameBoard.setGameOver(true);
        }
    }
}
