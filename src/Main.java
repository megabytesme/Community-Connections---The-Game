import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static void permissionSpace(Player player) {
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

    private static void hardwareSpace(Player player) {
        // check if property has permission
        PlayerProperty playerProperty = player.getCurrentProperty();
        if (playerProperty != null) {
            if (playerProperty.hasPermission()) {
                // check if player has enough resources
                if (player.getResourceTokens() >= 1) {
                    playerProperty.setHardwareInstalled(true);
                    player.useResourceToken(1);
                    System.out.println(player.getName() + " successfully installed hardware on " + playerProperty.getName() + "!");
                } else {
                    System.out.println(player.getName() + " didn't have enough resources to install hardware. You need more resources!");
                }
            } else {
                System.out.println(player.getName() + " cannot install hardware without permission on " + playerProperty.getName() + ".");
            }
        } else {
            System.out.println(player.getName() + " doesn't own any property to install hardware.");
        }
    }

    private static void educationSpace(Player player) {
        PlayerProperty playerProperty = player.getCurrentProperty();
        if (!playerProperty.isEducationCompleted()) {
            playerProperty.setEducationCompleted(true);
        } else {
            System.out.println(player.getName() + " has already completed education for this property.");
        }
    }

    private static void resourceSpace(Player player) {
        player.addResourceToken();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting the number of players
        System.out.print("Enter the number of players (1 to 8): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        if (numPlayers < 1 || numPlayers > 8) {
            System.out.println("Invalid number of players. Please enter a number between 1 and 8.");
            scanner.close();
            return;
        }

        // Array of symbols to represent players (can be extended if needed)
        String[] playerSymbols = {"A", "B", "C", "D", "E", "F", "G", "H"};

        // Create an array to hold the player names
        String[] playerNames = new String[numPlayers];

        // Initializing players and asking for their names
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name of Player " + (i + 1) + ": ");
            playerNames[i] = scanner.nextLine();
        }

        // Creating an array to hold the player objects
        Player[] players = new Player[numPlayers];

        // Ask the player if they want to use their own dice or the game's dice
        System.out.print("Do you want to use your own dice (yes/no)? ");
        boolean useOwnDice = scanner.nextLine().trim().equalsIgnoreCase("yes");

        // Creating a new game board
        GameBoard gameBoard = new GameBoard(playerNames, playerSymbols, useOwnDice);

        // Initializing players with names, symbols, and gameBoard
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i], playerSymbols[i], gameBoard, useOwnDice); // Pass the useOwnDice parameter
        }

        System.out.println("\nWelcome to the game!");

        // Creating player properties for each player
        int numPropertiesPerPlayer = 10;

        for (int i = 0; i < numPlayers; i++) {
            Player currentPlayer = players[i];

            for (int j = 0; j < numPropertiesPerPlayer; j++) {
                // Create a new property for the player
                String propertyName = "Property " + (j + 1);
                PlayerProperty property = new PlayerProperty(propertyName);

                // Add the property to the player
                currentPlayer.addProperty(property);
            }
        }

        GameLog gameLog = new GameLog();

        // Game loop
        while (true) {
            int currentPlayerIndex = gameBoard.getCurrentPlayerIndex();
            Player currentPlayer = players[currentPlayerIndex];

            gameBoard.displayGameBoard(players);
            System.out.println("\n" + currentPlayer.getName() + " (" + currentPlayer.getMark() + ")" + ", it's your turn!");

            int diceRoll = currentPlayer.rollDice(); // Use currentPlayer's rollDice() method
            gameBoard.movePlayer(currentPlayer, diceRoll, currentPlayer.getName());

            String currentSquare = gameBoard.getCurrentSquare();
            if (currentSquare.equals("Acquiring Permissions")) {
                permissionSpace(currentPlayer);
            } else if (currentSquare.equals("Acquiring and Installing Hardware")) {
                hardwareSpace(currentPlayer);
            } else if (currentSquare.equals("Informing and Educating the Community")) {
                educationSpace(currentPlayer);
            } else if (currentSquare.equals("Collecting Resources")) {
                resourceSpace(currentPlayer);
            } else if (currentSquare.equals("Start")) {
                resourceSpace(currentPlayer);
            }

            System.out.println("Current Game State:");
            System.out.println(currentPlayer.getName() + " - Position: " + currentPlayer.getPosition() +
                    " | Resources: " + currentPlayer.getResources());
            System.out.print("Has permission: ");
            boolean first = true;
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (property.hasPermission()) {
                    if (first) {
                        first = false;
                    } else {
                        System.out.print(", ");
                    }
                    System.out.print(property.getName());
                }
            }
            System.out.println();

            System.out.print("Hardware installed: ");
            first = true;
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (property.isHardwareInstalled()) {
                    if (first) {
                        first = false;
                    } else {
                        System.out.print(", ");
                    }
                    System.out.print(property.getName());
                }
            }
            System.out.println();

            System.out.print("Education completed: ");
            first = true;
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (property.isEducationCompleted()) {
                    if (first) {
                        first = false;
                    } else {
                        System.out.print(", ");
                    }
                    System.out.print(property.getName());
                }
            }
            System.out.println();

            System.out.print("Other properties: ");
            first = true;
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (!property.hasPermission() && !property.isHardwareInstalled() && !property.isEducationCompleted()) {
                    if (first) {
                        first = false;
                    } else {
                        System.out.print(", ");
                    }
                    System.out.print(property.getName());
                }
            }
            System.out.println();

            Iterator<PlayerProperty> iterator = currentPlayer.getProperties().iterator();
            while (iterator.hasNext()) {
                PlayerProperty property = iterator.next();
                if (currentPlayer.isPropertyFullyEnhanced(property)) {
                    currentPlayer.addLogEntry(currentPlayer.getName() + " has fully enhanced " + property.getName() + " and received a resource token.");
                    currentPlayer.addLogEntry(currentPlayer.getName() + " - Position: " + currentPlayer.getPosition() + " | Resources: " + currentPlayer.getResources());
                    iterator.remove();
                }
            }

            if (currentPlayer.getProperties().size() == 0) {
                System.out.println(currentPlayer.getName() + " has won the game!");
                System.out.println("Game Over!");

                currentPlayer.addLogEntry(currentPlayer.getName() + " has won the game!");
                currentPlayer.addLogEntry(currentPlayer.getName() + " - Position: " + currentPlayer.getPosition() + " | Resources: " + currentPlayer.getResources());
                break;
            }

            currentPlayer.addLogEntry(currentPlayer.getName() + " has completed their turn.");
            currentPlayer.addLogEntry(currentPlayer.getName() + " - Position: " + currentPlayer.getPosition() + " | Resources: " + currentPlayer.getResources());
            currentPlayer.addLogEntry(currentPlayer.getName() + "'s properties:");
            currentPlayer.addLogEntry("Has permission: ");
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (property.hasPermission()) {
                    currentPlayer.addLogEntry(property.getName());
                }
            }
            currentPlayer.addLogEntry("Hardware installed: ");
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (property.isHardwareInstalled()) {
                    currentPlayer.addLogEntry(property.getName());
                }
            }
            currentPlayer.addLogEntry("Education completed: ");
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (property.isEducationCompleted()) {
                    currentPlayer.addLogEntry(property.getName());
                }
            }
            currentPlayer.addLogEntry("Other properties: ");
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (!property.hasPermission() && !property.isHardwareInstalled() && !property.isEducationCompleted()) {
                    currentPlayer.addLogEntry(property.getName());
                }
            }
            gameLog.writeLogToFile("game_log.txt", currentPlayer);
            currentPlayer.getLogEntries().clear();
            gameBoard.updateCurrentPlayerIndex(numPlayers);
        }

        scanner.close();
    }
}
