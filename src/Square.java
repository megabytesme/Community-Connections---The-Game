import java.util.Scanner;

public abstract class Square {
    protected String name;
    protected Scanner scanner = new Scanner(System.in);

    public Square(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void performTask(Player player) {
        System.out.println("This square doesn't have any tasks.");
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
    public void performTask(Player player) {
        int diceRoll = player.rollDice();
        if (diceRoll % 2 == 0) {
            System.out.println(player.getName() + " rolled an even number and got permission!");
            PlayerProperty playerProperty = player.getCurrentProperty();
            if (playerProperty != null) {
                playerProperty.setPermission(true);
            } else {
                System.out.println(player.getName() + " doesn't own any property to grant permission.");
            }
        } else {
            System.out.println(player.getName() + " rolled an odd number and failed to get permission.");
        }
    }
}

class HardwareSquare extends Square {
    public HardwareSquare() {
        super("Acquiring and Installing Hardware");
    }

    @Override
    public void performTask(Player player) {
        int taskCost = 1; // The cost of installing hardware

        // Check if the player's property has permission
        PlayerProperty playerProperty = player.getCurrentProperty();
        if (playerProperty != null && playerProperty.hasPermission()) {
            // Ask the player if they want to spend a resource token to install hardware
            System.out.print(player.getName() + ", do you want to spend a resource token to install hardware? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                // Check if the player has enough resources to install hardware
                if (player.getResourceTokens() >= taskCost) {
                    player.useResourceToken(1); // Deduct one resource token from the player
                    playerProperty.setHardwareInstalled(true); // Mark hardware as installed

                    System.out.println(player.getName() + " successfully installed hardware!");
                } else {
                    System.out.println(player.getName() + " didn't have enough resources to install hardware. You need more resources!");
                }
            } else {
                System.out.println(player.getName() + " chose not to install hardware this time.");
            }
        } else {
            System.out.println(player.getName() + " cannot install hardware without permission on their property.");
        }
    }
}

class EducationSquare extends Square {
    public EducationSquare() {
        super("Informing and Educating the Community");
    }

    @Override
    public void performTask(Player player) {
        PlayerProperty playerProperty = player.getCurrentProperty();
        if (!playerProperty.isEducationCompleted()) {
            playerProperty.setEducationCompleted(true);
        } else {
            System.out.println(player.getName() + " has already completed education for this property.");
        }
    }
}

class ResourceSquare extends Square {
    public ResourceSquare() {
        super("Collecting Resources");
    }

    @Override
    public void performTask(Player player) {
        player.addResourceToken();
    }
}

class StartSquare extends Square {
    public StartSquare() {
        super("Start");
    }

    @Override
    public void performTask(Player player) {
        player.addResourceToken();
    }
}
