import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private int resources;
    private int position;

    public Player(String name) {
        this.name = name;
        this.resources = 0;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getResources() {
        return resources;
    }

    public void addResources(int amount) {
        resources += amount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // Method to get player's contribution for a square
    public int getPlayerContribution(Square square, int maxContribution) {
        // You can implement the specific logic for calculating player's contribution here
        // For simplicity, we'll use the same logic as in the GameBoard class.
        return getContribution(maxContribution);
    }

    // Method to get player's contribution to the community project
    public int getContribution(int maxContribution) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(getName() + ", enter your contribution (0 to " + maxContribution + "): ");
        int contribution = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()
        while (contribution < 0 || contribution > maxContribution) {
            System.out.print("Invalid contribution. Enter your contribution (0 to " + maxContribution + "): ");
            contribution = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()
        }
        return contribution;
    }
}
