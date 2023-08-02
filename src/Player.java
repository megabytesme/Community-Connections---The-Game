import java.util.Scanner;

public class Player {
    private String name;
    private String mark; // Symbol to represent the player on the game board
    private int position;
    private int resources;

    public Player(String name, String mark) {
        this.name = name;
        this.mark = mark;
        this.position = 0; // Start position on the game board (0-indexed)
        this.resources = 0; // Start with 0 resources
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
        return resources;
    }

    public void addResources(int resources) {
        this.resources += resources;
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
        // Assume the player's contribution is input from the user in this example
        Scanner scanner = new Scanner(System.in);
        System.out.print(getName() + ", enter your contribution (0 to " + maxContribution + "): ");
        int contribution = scanner.nextInt();
        while (contribution < 0 || contribution > maxContribution) {
            System.out.print("Invalid contribution. Enter your contribution (0 to " + maxContribution + "): ");
            contribution = scanner.nextInt();
        }
        return contribution;
    }

    public int getPlayerContribution(Square square, int maxContribution) {
        Scanner scanner = new Scanner(System.in);
        int contribution;
        do {
            System.out.print(this.getName() + ", enter your contribution (0 to " + maxContribution + "): ");
            contribution = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()
        } while (contribution < 0 || contribution > maxContribution);

        return contribution;
    }
}
