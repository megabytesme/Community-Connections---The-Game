import java.util.Random;

public class GameBoard {
    private final int numRows = 10;
    private final int numCols = 10;
    private final int numTasks;
    private Square[] squares;
    private int[] playerPositions;
    private int currentPlayerIndex;
    private boolean gameOver;

    public GameBoard(int numPlayers) {
        if (numPlayers < 1 || numPlayers > 8) {
            throw new IllegalArgumentException("Number of players must be between 1 and 8.");
        }

        squares = new Square[] {
            new PermissionSquare(),
            new HardwareSquare(),
            new EducationSquare(),
            new ResourceSquare()
            // Add more squares if needed
        };

        playerPositions = new int[numPlayers];
        currentPlayerIndex = 0;
        // Set the number of tasks
        numTasks = numPlayers * 2; // Each player has 2 tasks
        gameOver = false;
    }

    public int getNumTasks() {
        return numTasks;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public void movePlayer(Player player, int steps) {
        int currentPosition = playerPositions[currentPlayerIndex];
        int newPosition = (currentPosition + steps) % squares.length;
        player.setPosition(newPosition);
        playerPositions[currentPlayerIndex] = newPosition;
    }

    public Square getCurrentSquare(int position) {
        return squares[position];
    }

    public int getPlayerContribution(Player player, int maxContribution) {
        // Implement the specific way to get the player's contribution for this square
        // For simplicity, we'll use the same logic as in the GameBoard class.
        return player.getContribution(maxContribution);
    }

    public void updateCurrentPlayerIndex(int numPlayers) {
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
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
}