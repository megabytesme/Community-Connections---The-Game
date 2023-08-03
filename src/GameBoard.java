import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {
    private final int numRows = 10;
    private final int numCols = 10;
    private final int numTasks;
    private Square[] squares;
    private int[] playerPositions;
    private int currentPlayerIndex;
    private boolean gameOver;
    private PlayerProgress[] playerProgresses;
    private Player[] players;
    private Scanner scanner;

    public GameBoard(String[] playerNames, String[] playerSymbols, Scanner scanner) {
        if (playerNames.length < 1 || playerNames.length > 8) {
            throw new IllegalArgumentException("Number of players must be between 1 and 8.");
        }
        
        this.scanner = scanner; // Set the provided scanner to the local scanner variable

        squares = new Square[] {
            new PermissionSquare(),
            new HardwareSquare(),
            new EducationSquare(),
            new ResourceSquare()
            // Add more square types if needed
        };
    
        shuffleSquares();
    
        int numPlayers = playerNames.length;
    
        playerPositions = new int[numPlayers];
        currentPlayerIndex = 0;
        numTasks = numPlayers * 2; // Each player has 2 tasks
        gameOver = false;
    
        playerProgresses = new PlayerProgress[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerProgresses[i] = new PlayerProgress(numTasks);
        }
    
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i], playerSymbols[i]);
        }
    
        assignPropertiesToPlayers(players);
    }
    

    private void shuffleSquares() {
        List<Square> squareList = Arrays.asList(squares);
        Collections.shuffle(squareList);
        squareList.toArray(squares);
    }

    private void assignPropertiesToPlayers(Player[] players) {
        List<Square> propertySquares = new ArrayList<>();
        for (Square square : squares) {
            if (square instanceof PropertySquare) {
                propertySquares.add(square);
            }
        }

        Collections.shuffle(propertySquares);

        int numPlayers = players.length;
        int propertiesPerPlayer = propertySquares.size() / numPlayers;
        int remainingProperties = propertySquares.size() % numPlayers;

        int propertyIndex = 0;
        for (int i = 0; i < numPlayers; i++) {
            int propertiesToAssign = propertiesPerPlayer;
            if (i < remainingProperties) {
                propertiesToAssign++;
            }

            for (int j = 0; j < propertiesToAssign; j++) {
                PropertySquare propertySquare = (PropertySquare) propertySquares.get(propertyIndex);
                propertySquare.setOwner(players[i]);
                propertyIndex++;
            }
        }
    }

    public int getNumTasks() {
        return numTasks;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public SquareType getSquareType(int position) {
        return squares[position].getType();
    }

    public void updateCurrentPlayerIndex(int numPlayers) {
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Square getCurrentSquare(int position) {
        return squares[position];
    }

    public void displayGameBoard(Player[] players) {
        String boundary = "+----+----+----+----+----+----+----+----+----+----+";
        String middleBoundary = "+----+                                       +----+";

        // Create an array to store the marks of the players on the game board
        String[][] gameBoard = new String[numRows][numCols];

        // Initialize the game board with empty strings
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                gameBoard[i][j] = " ";
            }
        }

        // Place the marks of the players on the game board
        for (Player player : players) {
            int playerPosition = player.getPosition();
            int playerRow = playerPosition / numCols;
            int playerCol = playerPosition % numCols;
            String playerMark = player.getMark();
            gameBoard[playerRow][playerCol] = playerMark;
        }

        // Display the top boundary
        System.out.println(boundary);

        // Display the rows of the game board
        for (int i = 0; i < numRows; i++) {
            // Display the row of cells
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + gameBoard[i][j] + "  ");
            }
            System.out.println();
            // Display the row boundary
            if (i == 0 || i == numRows - 2 || i == numRows - 1) {
                System.out.println(boundary);
            } else {
                System.out.println(middleBoundary);
            }
        }
    }

    // New method to check if any player has won
    public boolean checkForWinner(Player[] players) {
        for (Player player : players) {
            PlayerProgress playerProgress = player.getPlayerProgress();
            if (playerProgress.getCompletedTasksCount() == numTasks) {
                System.out.println("\nGame Over!");
                System.out.println(player.getName() + " has successfully developed all their properties!");
                return true;
            }
        }
        return false;
    }

    public void movePlayer(Player player, int steps, String playerName) {
        int currentPosition = playerPositions[currentPlayerIndex];
        int newPosition = (currentPosition + steps) % squares.length;
        player.setPosition(newPosition);
        playerPositions[currentPlayerIndex] = newPosition;
    
        Square currentSquare = squares[newPosition];
        playerProgresses[currentPlayerIndex].markTaskCompleted(currentSquare);
    
        // Check if the current square is a PermissionSquare
        if (currentSquare instanceof PermissionSquare) {
            PermissionSquare permissionSquare = (PermissionSquare) currentSquare;
            System.out.println(playerName + ", do you want to attempt getting permission (yes/no)?");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("yes")) {
                permissionSquare.attemptGetPermission(player);
            }
        }
    }

    public boolean hasPlayerDevelopedAllProperties(Player player) {
        for (int position = 0; position < squares.length; position++) {
            SquareType squareType = getSquareType(position);
            if (squareType == SquareType.PROPERTY) {
                // Check if the property square is owned by the player and is developed
                PropertySquare propertySquare = (PropertySquare) squares[position];
                if (propertySquare.getOwner() == player && !propertySquare.isDeveloped()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameOver() {
        // Check if any player has developed all their properties
        for (Player player : players) {
            if (hasPlayerDevelopedAllProperties(player)) {
                System.out.println("\nGame Over!");
                System.out.println(player.getName() + " has successfully developed all their properties!");
                gameOver = true;
                return true;
            }
        }
        return gameOver;
    }
}
