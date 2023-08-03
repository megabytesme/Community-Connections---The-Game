import java.util.Scanner;

public class Player {
    private String name;
    private String mark;
    private int position;
    private int permissionTokens;
    private int resourceTokens;
    private PlayerProgress playerProgress;
    private static Scanner scanner = new Scanner(System.in);

    public Player(String name, String mark) {
        this.name = name;
        this.mark = mark;
        this.position = 0;
        this.permissionTokens = 2;
        this.resourceTokens = 2;
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

    public int getContribution(int maxContribution) {
        System.out.print(getName() + ", enter your contribution (0 to " + maxContribution + "): ");
        int contribution = scanner.nextInt();
        while (contribution < 0 || contribution > maxContribution) {
            System.out.print("Invalid contribution. Enter your contribution (0 to " + maxContribution + "): ");
            contribution = scanner.nextInt();
        }
        return contribution;
    }

    public int getPlayerContribution(Square square, int maxContribution) {
        int contribution;
        do {
            System.out.print(this.getName() + ", enter your contribution (0 to " + maxContribution + "): ");
            contribution = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()
        } while (contribution < 0 || contribution > maxContribution);

        return contribution;
    }

    public void addPermissionToken() {
        permissionTokens++;
    }

    public void usePermissionToken() {
        permissionTokens--;
    }

    public int getPermissionTokens() {
        return permissionTokens;
    }

    public void addResourceToken() {
        resourceTokens++;
    }

    public void useResourceToken() {
        resourceTokens--;
    }

    public int getResourceTokens() {
        return resourceTokens;
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
}