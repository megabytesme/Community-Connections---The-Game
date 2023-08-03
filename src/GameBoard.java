import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {
    private final int size = 10;
    private final int[] squareTypes;
    private Square[] squares;
    private final int numRows = 10;
    private final int numCols = 10;
    private int[] playerPositions;
    private int currentPlayerIndex;
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
        };

        shuffleSquares();

        int numPlayers = playerNames.length;
        playerPositions = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerPositions[i] = 0;
        }
        currentPlayerIndex = 0;

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i], playerSymbols[i], this);
        }

        squareTypes = new int[size * size];
        Arrays.fill(squareTypes, SquareType.START.ordinal());

        placeSquareType(SquareType.START, 1); // Place 1 Start square
        placeSquareType(SquareType.PERMISSION, 5);
        placeSquareType(SquareType.HARDWARE, 9);
        placeSquareType(SquareType.EDUCATION, 9);
        placeSquareType(SquareType.RESOURCE, 8);

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
    
        while (count < num && index < totalSquares) {
            if (squareTypes[index] == SquareType.START.ordinal()) {
                squareTypes[index] = squareType.ordinal();
                count++;
            }
            index++;
        }
    }

    private void shuffleSquares() {
        List<Square> squareList = Arrays.asList(squares);
        Collections.shuffle(squareList);
        squareList.toArray(squares);
    }

    public int rollDice() {
        if (useOwnDice) {
            System.out.print(players[currentPlayerIndex].getName() + ", enter the dice roll (1 to 6): ");
            int diceRoll = scanner.nextInt();
            scanner.nextLine();
            return diceRoll;
        } else {
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
    
        String[][] gameBoard = new String[numRows][numCols];
    
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                gameBoard[i][j] = " ";
            }
        }
    
        for (Player player : players) {
            int x = player.getX();
            int y = player.getY();
            String playerMark = player.getMark();
            gameBoard[x][y] = playerMark;
        }
    
        System.out.println(boundary);
    
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + gameBoard[i][j] + "  ");
            }
            System.out.println();
            if (i == 0 || i == numRows - 2 || i == numRows - 1) {
                System.out.println(boundary);
            } else {
                System.out.println(middleBoundary);
            }
        }
    
        for (Player player : players) {
            System.out.println(player.getName() + " is on (" + player.getX() + ", " + player.getY() + ")");
        }
    }

    public void movePlayer(Player player, int steps, String playerName) {
        int x = player.getX();
        int y = player.getY();
    
        for (int i = 0; i < steps; i++) {
            if (x == 0 && y < 9) {
                y++;
            } else if (y == 9 && x < 9) {
                x++;
            } else if (x == 9 && y > 0) {
                y--;
            } else if (y == 0 && x > 0) {
                x--;
            }
        }
        player.setX(x);
        player.setY(y);
    
        System.out.println(playerName + " (" + player.getMark() + ")" + " is on " + board[x][y].getName() + " (" + x + ", " + y + ")");
    }
}
