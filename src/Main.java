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
        String[] playerSymbols = {"X", "O", "A", "B", "C", "D", "E", "F"};

        // Creating an array to hold the players
        Player[] players = new Player[numPlayers];

        // Initializing players
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name of Player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            players[i] = new Player(playerName, playerSymbols[i]);
        }

        // Creating a new game board
        GameBoard gameBoard = new GameBoard(numPlayers);

        // Creating player progress for each player
        PlayerProgress[] playerProgresses = new PlayerProgress[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerProgresses[i] = new PlayerProgress(gameBoard.getNumTasks());
        }

        System.out.println("\nWelcome to the game!");

        // Game loop
        while (!gameBoard.isGameOver()) {
            int currentPlayerIndex = gameBoard.getCurrentPlayerIndex();
            Player currentPlayer = players[currentPlayerIndex];
            PlayerProgress currentPlayerProgress = playerProgresses[currentPlayerIndex];

            gameBoard.displayGameBoard(players);
            System.out.println("\n" + currentPlayer.getName() + ", it's your turn!");

            // Rolling the dice and moving the player
            int diceRoll = gameBoard.rollDice();
            System.out.println("You rolled a " + diceRoll);
            gameBoard.movePlayer(currentPlayer, diceRoll);

            // Getting the current square the player is on
            Square currentSquare = gameBoard.getCurrentSquare(currentPlayer.getPosition());

            // Performing the task on the current square
            currentSquare.performTask(currentPlayer, currentPlayerProgress);

            // Displaying current player's position and resources
            System.out.println("Current Game State:");
            System.out.println(currentPlayer.getName() + " - Position: " + currentPlayer.getPosition() +
                    " | Resources: " + currentPlayer.getResources());
            System.out.println("Completed Tasks: " + currentPlayerProgress.getCompletedTasksString());

            // Check if the player has completed the community network
            if (currentPlayerProgress.isCompletedCommunityNetwork()) {
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
                gameBoard.setGameOver(true);
            }

            // Update the current player index for the next turn
            gameBoard.updateCurrentPlayerIndex(numPlayers);
        }

        scanner.close();
    }
}
