public abstract class Square {
    protected String name;
    protected boolean developed;

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

    // Update the performTask method to use the current Square object
    public void performTask(Player player, PlayerProgress playerProgress) {
        player.addPermissionToken();
        playerProgress.markTaskCompleted(this); // Pass the current Square object
    }

    public abstract int getPlayerContribution(Player player, int maxContribution);

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
            int contribution = player.getPlayerContribution(this, 50);
            player.addResources(contribution);
            // Update the progress of the task
            playerProgress.markTaskCompleted(this); // Pass the current PermissionSquare instance
            developed = true; // Mark the property as developed after acquiring permission
        }
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        return player.getContribution(maxContribution);
    }

    // Add the missing method to attempt to get permission
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
        if (developed) {
            int contribution = player.getPlayerContribution(this, 70);
            player.addResources(contribution);
            // Update the progress of the task
            playerProgress.markTaskCompleted(this); // Pass the current HardwareSquare instance
            developed = true; // Mark the property as developed after installing hardware
        }
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        return player.getContribution(maxContribution);
    }
}

class EducationSquare extends Square {
    public EducationSquare() {
        super("Informing and Educating the Community");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        if (developed) {
            int contribution = player.getPlayerContribution(this, 30);
            player.addResources(contribution);
            // Update the progress of the task
            playerProgress.markTaskCompleted(this); // Pass the current EducationSquare instance
            developed = true; // Mark the property as developed after educating users
        }
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        return player.getContribution(maxContribution);
    }
}

class ResourceSquare extends Square {
    public ResourceSquare() {
        super("Collecting Resources");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        if (developed) {
            int contribution = player.getPlayerContribution(this, 100);
            player.addResources(contribution);
            // Update the progress of the task
            playerProgress.markTaskCompleted(this); // Pass the current ResourceSquare instance
            developed = true; // Mark the property as developed after collecting resources
        }
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        return player.getContribution(maxContribution);
    }
}

class StartSquare extends Square {
    public StartSquare() {
        super("Start");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        player.addPermissionToken();
        playerProgress.markTaskCompleted(this); // Pass the current Square instance (this) instead of player.getPosition()
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        return 0; // Start square does not require any contribution
    }
}

class PropertySquare extends Square {
    private Player owner;

    public PropertySquare() {
        super("Property");
        this.developed = false;
        this.owner = null;
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        // Implement the specific way to perform the task for the property square
        // For example, acquiring permission, installing hardware, or educating users.
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        // Implement the specific way to get the player's contribution for this square
        return player.getContribution(maxContribution);
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return owner;
    }
}