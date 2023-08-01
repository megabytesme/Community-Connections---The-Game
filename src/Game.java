import org.eclipse.swt.widgets.*;

import GUI.GUI;
import Game.Player;

public class Game {
    private String[] players;
    private int currentPlayerIndex;

    public Game(String[] players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public void start() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Community Connections");

        GUI gui = new GUI(shell);

        shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }

            // Perform game actions here
            // For example, handle player turns and update player statistics

            // When it's time to switch to the next player's turn
            // You can use currentPlayerIndex to determine the current player
            // and then move to the next player

            // For simplicity, let's just increment the current player index on every iteration
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

            // Now you can use currentPlayerIndex to get the current player from the players array
            String currentPlayerName = players[currentPlayerIndex];

            // Update player statistics or handle other game logic as needed
            // For example, you can display the current player's name in the PlayerStatistics panel
            gui.updateCurrentPlayer(currentPlayerName);

            // You may also check for game over conditions and end the game when necessary

            // Here, you can add a delay (e.g., Thread.sleep) to control the game's pace
            // Note that using Thread.sleep in the UI thread may not be the best approach for a real game
        }

        display.dispose();
    }

    // Add more game logic methods as needed

    public static void main(String[] args) {
        // Run the game setup to get player names
        GameSetup gameSetup = new GameSetup();
        String[] players = gameSetup.setupGame();

        // Set the obtained player names in the Player class
        Player.setPlayers(players);

        // Start the game with the obtained player names
        Game game = new Game(players);
        game.start();
    }
}