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
            System.out.println("Completed Tasks: " + currentPlayerProgress.getCompletedTasksString());

            // Check if the player has completed the community network
            if (currentPlayerProgress.isCompletedCommunityNetwork(players)) { // Pass the array of players as an argument
                System.out.println("\nGame Over!");
                System.out.println(currentPlayer.getName() + " has successfully completed the community network!");

                // Mark all other players as ran out of resources
                for (int j = 0; j < numPlayers; j++) {
                    if (j != currentPlayerIndex) {
                        Player otherPlayer = players[j];
                        if (otherPlayer.getResources() <= 0) {
                            System.out.println(otherPlayer.getName() + " ran out of resources. Better luck next time!");
                        }
                    }
                }
                break; // Exit the game loop
            }

            // Update the current player index for the next turn
            gameBoard.updateCurrentPlayerIndex(numPlayers);
        }

        scanner.close();
    }
}
