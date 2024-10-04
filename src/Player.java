import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private String mark;
    private int position;
    private int resourceTokens;
    private static Scanner scanner = new Scanner(System.in);
    private int x;
    private int y;
    private int displayX;
    private int displayY;
    private List<PlayerProperty> playerProperties = new ArrayList<>();
    private GameBoard gameBoard;
    private int currentPropertyIndex;
    private List<LogEntry> logEntries = new ArrayList<>();
    
    public Player(String name, String mark, GameBoard gameBoard, boolean useOwnDice) {
        this.name = name;
        this.mark = mark;
        this.position = 0;
        this.resourceTokens = 2;
        this.gameBoard = gameBoard;
        this.currentPropertyIndex = 0;
        this.displayX = this.x;
        this.displayY = this.y;
    }

    public void addProperty(PlayerProperty property) {
        playerProperties.add(property);
    }

    public PlayerProperty getCurrentProperty() {
        if (currentPropertyIndex >= 0 && currentPropertyIndex < playerProperties.size()) {
            return playerProperties.get(currentPropertyIndex);
        }
        return null;
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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        this.displayX = x;
        this.displayY = y;
    }

    public int getDisplayX() {
        return displayX;
    }

    public int getDisplayY() {
        return displayY;
    }

    public void setDisplayPosition(int x, int y) {
        this.displayX = x;
        this.displayY = y;
    }

    public int getResources() {
        return resourceTokens;
    }

    public void addResources(int resources) {
        this.resourceTokens += resources;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void addResourceToken() {
        resourceTokens++;
    }

    public void useResourceToken(int count) {
        if (resourceTokens >= count) {
            resourceTokens -= count;
        } else {
            System.out.println("You don't have enough resource tokens to perform this task.");
        }
    }

    public int getResourceTokens() {
        return resourceTokens;
    }

    public static void closeScanner() {
        scanner.close();
    }

    public boolean isPropertyFullyEnhanced(PlayerProperty property) {
        return property.hasPermission() && property.isHardwareInstalled() && property.isEducationCompleted();
    }

    public void removeProperty(PlayerProperty property) {
        playerProperties.remove(property);
    }

    public void addLogEntry(String action) {
        LocalDateTime timestamp = LocalDateTime.now();
        logEntries.add(new LogEntry(timestamp, name, action));
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public List<PlayerProperty> getProperties() {
        return playerProperties;
    }
}
