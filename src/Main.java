import java.util.List;
import java.util.Scanner;

public class Main {
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
        GameBoard gameBoard = new GameBoard(playerNames, playerSymbols, useOwnDice, scanner);

        // Initializing players with names, symbols, and gameBoard
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i], playerSymbols[i], gameBoard);
        }

        // Creating player progress for each player
        PlayerProgress[] playerProgresses = new PlayerProgress[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerProgresses[i] = new PlayerProgress(gameBoard.getNumTasks());
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

        GameLog gameLog = new GameLog(List.of(players));

        // Game loop
        while (true) {
            int currentPlayerIndex = gameBoard.getCurrentPlayerIndex();
            Player currentPlayer = players[currentPlayerIndex];
            PlayerProgress currentPlayerProgress = playerProgresses[currentPlayerIndex];

            gameBoard.displayGameBoard(players);
            System.out.println("\n" + currentPlayer.getName() + " (" + currentPlayer.getMark() + ")" + ", it's your turn!");

            // Rolling the dice and moving the player
            int diceRoll = gameBoard.rollDice();
            gameBoard.movePlayer(currentPlayer, diceRoll, currentPlayer.getName());

            // Getting the current square the player is on
            Square currentSquare = gameBoard.getCurrentSquare(currentPlayer.getPosition());

            // Perform the action based on the type of square the player lands on
            if (currentSquare instanceof PermissionSquare) {
                // Perform the permission task for the player
                ((PermissionSquare) currentSquare).performTask(currentPlayer, currentPlayerProgress);
            } else if (currentSquare instanceof HardwareSquare) {
                // Perform the hardware task for the player
                ((HardwareSquare) currentSquare).performTask(currentPlayer, currentPlayerProgress);
            } else if (currentSquare instanceof EducationSquare) {
                // Perform the education task for the player
                ((EducationSquare) currentSquare).performTask(currentPlayer, currentPlayerProgress);
            } else if (currentSquare instanceof ResourceSquare) {
                // Perform the resource collection task for the player
                ((ResourceSquare) currentSquare).performTask(currentPlayer, currentPlayerProgress);
            }

            // Displaying current player's position and resources
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
                    

            // for loop to check if player properties are fully enhanced
            for (PlayerProperty property : currentPlayer.getProperties()) {
                if (currentPlayer.isPropertyFullyEnhanced(property)) {                   
                    // Log entries
                    currentPlayer.addLogEntry(currentPlayer.getName() + " has fully enhanced " + property.getName() + " and received a resource token.");
                    currentPlayer.addLogEntry(currentPlayer.getName() + " - Position: " + currentPlayer.getPosition() + " | Resources: " + currentPlayer.getResources());
                
                    // remove the property from the player
                    currentPlayer.removeProperty(property);
                }
            }

            // check if the player has any properties left
            if (currentPlayer.getProperties().size() == 0) {
                // the person who wins is the current player
                System.out.println(currentPlayer.getName() + " has won the game!");
                System.out.println("Game Over!");

                // Log entries
                currentPlayer.addLogEntry(currentPlayer.getName() + " has won the game!");
                currentPlayer.addLogEntry(currentPlayer.getName() + " - Position: " + currentPlayer.getPosition() + " | Resources: " + currentPlayer.getResources());
                break;
            }

            // Log entries
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

            // Flush the log to the file after each turn
            gameLog.writeLogToFile("game_log.txt", currentPlayer);

            // empty the log entries after each turn
            currentPlayer.getLogEntries().clear();

            // Update the current player index for the next turn
            gameBoard.updateCurrentPlayerIndex(numPlayers);
        }

        scanner.close();
    }
}
