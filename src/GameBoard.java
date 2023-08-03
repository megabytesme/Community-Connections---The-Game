import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {
    private final int size = 10;
    private final int[] squareTypes; // Array to store the types of squares
    private Square[] squares;
    private final int numRows = 10;
    private final int numCols = 10;
    private final int numTasks;
    private int[] playerPositions; // Update to store each player's position
    private int currentPlayerIndex;
    private boolean gameOver;
    private PlayerProgress[] playerProgresses;
    private Player[] players;
    private Scanner scanner;
    private Square[][] board;
    private final boolean useOwnDice;

    public GameBoard(String[] playerNames, String[] playerSymbols, boolean useOwnDice, Scanner scanner) {
        if (playerNames.length < 1 || playerNames.length > 8) {
            throw new IllegalArgumentException("Number of players must be between 1 and 8.");
        }
        this.useOwnDice = useOwnDice;
        this.scanner = scanner;

        squares = new Square[] {
            new StartSquare(),
            new PermissionSquare(),
            new HardwareSquare(),
            new EducationSquare(),
            new ResourceSquare()
            // Add more square types if needed
        };

        shuffleSquares();

        int numPlayers = playerNames.length;

        // Initialize the playerPositions array with the starting position for each player
        playerPositions = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerPositions[i] = 0; // All players start at position 0 (the first square)
        }
        currentPlayerIndex = 0;
        numTasks = numPlayers * 2; // Each player has 2 tasks
        gameOver = false;

        playerProgresses = new PlayerProgress[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerProgresses[i] = new PlayerProgress(numTasks);
        }

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i], playerSymbols[i], this);
        }

        // Initialize the squareTypes array with the types of squares
        squareTypes = new int[size * size];
        Arrays.fill(squareTypes, SquareType.START.ordinal()); // Set all squares to StartSquare initially

        // Place other square types on the board
        placeSquareType(SquareType.PERMISSION, 1);
        placeSquareType(SquareType.HARDWARE, 2);
        placeSquareType(SquareType.EDUCATION, 3);
        placeSquareType(SquareType.RESOURCE, 4);

        // Initialize the board as a 2D array of Square objects
        board = new Square[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int squareType = squareTypes[(row * size) + col];
                board[row][col] = squares[squareType];
            }
        }
    }

    private void placeSquareType(SquareType squareType, int num) {
        int totalSquares = size * size;
        int count = 0;
        int index = 0;
        while (count < num) {
            index = (index + 1) % totalSquares;
            if (squareTypes[index] == SquareType.START.ordinal()) {
                squareTypes[index] = squareType.ordinal();
                count++;
            }
        }
    }

    private void shuffleSquares() {
        List<Square> squareList = Arrays.asList(squares);
        Collections.shuffle(squareList);
        squareList.toArray(squares);
    }

    public int getNumTasks() {
        return numTasks;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int rollDice() {
        if (useOwnDice) {
            // Use the player's dice (manual input)
            System.out.print(players[currentPlayerIndex].getName() + ", enter the dice roll (1 to 6): ");
            int diceRoll = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()
            return diceRoll;
        } else {
            // Use the game's dice (random number between 1 and 6)
            int diceRoll = new Random().nextInt(6) + 1;
            System.out.println("You rolled a " + diceRoll);
            return diceRoll;
        }
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
            // Get the x and y coordinates of the player from their position
            int x = player.getX();
            int y = player.getY();
            // Get the mark of the player
            String playerMark = player.getMark();
            // Place the mark on the game board
            gameBoard[x][y] = playerMark;
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
    
        // Print where each player is on the board
        for (Player player : players) {
            System.out.println(player.getName() + " is on (" + player.getX() + ", " + player.getY() + ")");
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

    // New method to check if a player has developed all their properties
    public boolean hasPlayerDevelopedAllProperties(Player player) {
        return player.getPropertyStage() == 3;
    }

    public void movePlayer(Player player, int steps, String playerName) {
        // get the current position of the player
        int x = player.getX();
        int y = player.getY();
    
        // loop through the steps
        for (int i = 0; i < steps; i++) {
            // check which edge of the grid the player is on and move accordingly
            if (x == 0 && y < 9) {
                // move right
                y++;
            } else if (y == 9 && x < 9) {
                // move down
                x++;
            } else if (x == 9 && y > 0) {
                // move left
                y--;
            } else if (y == 0 && x > 0) {
                // move up
                x--;
            }
        }
    
        // update the position of the player
        player.setX(x);
        player.setY(y);
    
        System.out.println(playerName + " (" + player.getMark() + ")" + " is on " + board[x][y].getName() + " (" + x + ", " + y + ")");
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
