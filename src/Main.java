import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting the number of players
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        // Creating an array to hold the players
        Player[] players = new Player[numPlayers];

        // Initializing players
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter the name of Player " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            players[i] = new Player(playerName);
        }

        // Creating a new game board
        GameBoard gameBoard = new GameBoard(numPlayers);

        // Creating player progress for each player
        PlayerProgress[] playerProgresses = new PlayerProgress[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerProgresses[i] = new PlayerProgress(gameBoard.getNumTasks());
        }

        // Game loop
        while (!gameBoard.isGameOver()) {
            for (int i = 0; i < numPlayers; i++) {
                Player currentPlayer = players[i];
                PlayerProgress currentPlayerProgress = playerProgresses[i];

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
                        if (j != i) {
                            Player otherPlayer = players[j];
                            if (otherPlayer.getResources() <= 0) {
                                System.out.println(otherPlayer.getName() + " ran out of resources. Better luck next time!");
                            }
                        }
                    }
                    gameBoard.setGameOver(true);
                    break; // Exit the game loop
                }
            }
        }

        scanner.close();
    }
}
