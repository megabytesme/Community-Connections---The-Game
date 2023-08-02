import java.util.Random;

public class GameBoard {
    private int numPlayers;
    private int numTasks;
    private int[] playerPositions;
    private boolean gameOver;

    public GameBoard(int numPlayers) {
        this.numPlayers = numPlayers;
        this.numTasks = SquareType.values().length;
        this.playerPositions = new int[numPlayers];
        this.gameOver = false;
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
        return random.nextInt(6) + 1; // Return a random number between 1 and 6
    }

    public void movePlayer(Player player, int steps) {
        int currentPosition = player.getPosition();
        int newPosition = (currentPosition + steps) % numTasks;
        player.setPosition(newPosition);
    }

    public Square getCurrentSquare(int position) {
        SquareType squareType = SquareType.values()[position];
        switch (squareType) {
            case PERMISSION:
                return new PermissionSquare();
            case HARDWARE:
                return new HardwareSquare();
            case EDUCATION:
                return new EducationSquare();
            case RESOURCE:
                return new ResourceSquare();
            default:
                throw new IllegalArgumentException("Invalid square type");
        }
    }
}
