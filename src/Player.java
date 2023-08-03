import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private String mark;
    private int position;
    private int permissionTokens;
    private int resourceTokens;
    private PlayerProgress playerProgress;
    private static Scanner scanner = new Scanner(System.in);
    private int x;
    private int y;
    private List<PropertySquare> properties = new ArrayList<>();

    public Player(String name, String mark) {
        this.name = name;
        this.mark = mark;
        this.position = 0;
        this.permissionTokens = 2;
        this.resourceTokens = 2;
        this.playerProgress = new PlayerProgress(4); // Set the initial number of tasks (4 in this case)
    }

    // Method to add a property to the player's list of properties
    public void addProperty(PropertySquare propertySquare) {
        properties.add(propertySquare);
    }

    // Method to get the player's list of properties
    public List<PropertySquare> getProperties() {
        return properties;
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

    private boolean wantsToContribute() {
        System.out.print("Do you want to contribute (yes/no)? ");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("yes");
    }

    public void addPermissionToken() {
        if (permissionTokens < 3) { // Limit the number of permission tokens to 3
            permissionTokens++;
        }
    }

    public void usePermissionToken() {
        if (permissionTokens > 0) { // Check if there are available permission tokens
            permissionTokens--;
        }
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

    public int getPermissionTokens() {
        return permissionTokens;
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
}
