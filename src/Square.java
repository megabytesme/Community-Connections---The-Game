import java.util.Random;
import java.util.Scanner;

public abstract class Square {
    protected String name;

    public Square(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void performTask(Player player, PlayerProgress playerProgress);

    public abstract int getPlayerContribution(Player player, int maxContribution);
}

class PermissionSquare extends Square {
    public PermissionSquare() {
        super("Acquiring Permissions");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        int contribution = player.getPlayerContribution(this, 50); // 50 is just an example contribution amount
        player.addResources(contribution);
        // Update the progress of the task
        playerProgress.markTaskCompleted(player.getPosition());
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        // Implement the specific way to get the player's contribution for this square
        return player.getContribution(maxContribution);
    }
}

class HardwareSquare extends Square {
    public HardwareSquare() {
        super("Acquiring and Installing Hardware");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        int contribution = player.getPlayerContribution(this, 70); // 70 is just an example contribution amount
        player.addResources(contribution);
        // Update the progress of the task
        playerProgress.markTaskCompleted(player.getPosition());
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        // Implement the specific way to get the player's contribution for this square
        return player.getContribution(maxContribution);
    }
}

class EducationSquare extends Square {
    public EducationSquare() {
        super("Informing and Educating the Community");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        int contribution = player.getPlayerContribution(this, 30); // 30 is just an example contribution amount
        player.addResources(contribution);
        // Update the progress of the task
        playerProgress.markTaskCompleted(player.getPosition());
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        // Implement the specific way to get the player's contribution for this square
        return player.getContribution(maxContribution);
    }
}

class ResourceSquare extends Square {
    public ResourceSquare() {
        super("Collecting Resources");
    }

    @Override
    public void performTask(Player player, PlayerProgress playerProgress) {
        int contribution = player.getPlayerContribution(this, 100); // 100 is just an example contribution amount
        player.addResources(contribution);
        // Update the progress of the task
        playerProgress.markTaskCompleted(player.getPosition());
    }

    @Override
    public int getPlayerContribution(Player player, int maxContribution) {
        // Implement the specific way to get the player's contribution for this square
        return player.getContribution(maxContribution);
    }
}