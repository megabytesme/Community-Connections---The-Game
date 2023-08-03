import java.util.Scanner;

public abstract class Square {
    protected String name;
    protected boolean developed;
    protected Scanner scanner = new Scanner(System.in);

    public Square(String name) {
        this.name = name;
        this.developed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isDeveloped() {
        return developed;
    }

    public void setDeveloped(boolean developed) {
        this.developed = developed;
    }

    public void performTask(Player player, PlayerProgress playerProgress) {
        player.addPermissionToken();
        playerProgress.markTaskCompleted(this); // Pass the current Square object
    }

    public SquareType getType() {
        return null;
    }
}

class PermissionSquare extends Square {
    public PermissionSquare() {
        super("Acquiring Permissions");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        if (!developed) {
            int contribution = 50; // Set the fixed contribution for PermissionSquare
            player.addResources(contribution);
            // Update the progress of the task
            playerProgress.markTaskCompleted(this); // Pass the current PermissionSquare instance
            developed = true; // Mark the property as developed after acquiring permission
        }
    }

    public void attemptGetPermission(Player player) {
        // Implement the specific logic to attempt to get permission
        // For example, you can prompt the player for input or use random chance to determine success.
    }
}

class HardwareSquare extends Square {
    public HardwareSquare() {
        super("Acquiring and Installing Hardware");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        int taskCost = getTaskCost(playerProgress.getCompletedTasks());

        // Ask the player for their contribution
        System.out.print(player.getName() + ", enter your contribution (0 to " + taskCost + "): ");
        int contribution = scanner.nextInt();
        while (contribution < 0 || contribution > taskCost) {
            System.out.print("Invalid contribution. Enter your contribution (0 to " + taskCost + "): ");
            contribution = scanner.nextInt();
        }

        playerProgress.addCompletedTask();
        player.useResourceToken(contribution); // Deduct the player's contribution from the resources

        // Check if the player has enough resources to advance the property to the next stage
        if (contribution >= taskCost) {
            System.out.println(player.getName() + " successfully enhanced their property!");
            playerProgress.advanceProperty(); // Advance the property to the next stage
        } else {
            System.out.println(player.getName() + " didn't contribute enough to enhance their property this time.");
        }
    }

    private int getTaskCost(int completedTasks) {
        // Provide the task cost based on the number of completed tasks
        // You can implement your custom logic here
        return 0;
    }
}

class EducationSquare extends Square {
    public EducationSquare() {
        super("Informing and Educating the Community");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        if (!developed) {
            int contribution = 30; // Set the fixed contribution for EducationSquare
            player.addResources(contribution);
            // Update the progress of the task
            playerProgress.markTaskCompleted(this); // Pass the current EducationSquare instance
            developed = true; // Mark the property as developed after educating users
        }
    }
}

class ResourceSquare extends Square {
    public ResourceSquare() {
        super("Collecting Resources");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        if (!developed) {
            int contribution = 100; // Set the fixed contribution for ResourceSquare
            player.addResources(contribution);
            // Update the progress of the task
            playerProgress.markTaskCompleted(this); // Pass the current ResourceSquare instance
            developed = true; // Mark the property as developed after collecting resources
        }
    }
}

class StartSquare extends Square {
    public StartSquare() {
        super("Start");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        player.addPermissionToken();
        player.addResourceToken();
        playerProgress.markTaskCompleted(this); // Pass the current Square instance (this) instead of player.getPosition()
    }
}

class PropertySquare extends Square {
    private Player owner;
    private int propertyStage; // Property enhancement level

    public PropertySquare() {
        super("Property");
        this.developed = false;
        this.owner = null;
        this.propertyStage = 0; // Initialize propertyStage to 0 (no progress)
    }

    public int getPropertyStage() {
        return propertyStage;
    }

    public void incrementPropertyStage() {
        propertyStage++;
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        player.addPermissionToken();
        player.addResourceToken(); // Give the player 1 resource token for landing on the Start square
        playerProgress.markTaskCompleted(this); // Pass the current Square instance (this) instead of player.getPosition()
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return owner;
    }
}
