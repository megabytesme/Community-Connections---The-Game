import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

import GUI.GUI;
import Game.Player;

public class Game {
    private Player[] players;
    private int currentPlayerIndex;
    private boolean gameOver = false;

    public Game(String[] playerNames) {
        players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player();
        }
        Player.setPlayers(playerNames);
        this.currentPlayerIndex = 0;
    }

    public void start() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Community Connections");

        GUI gui = new GUI(shell);

        shell.pack();
        shell.open();

        // write a method to give each player 1000 money, 10 resource tokens, and 1 permission token
        for (int i = 0; i < players.length; i++) {
            players[i].setMoney(1000);
            players[i].setResourceTokens(10);
            players[i].setPermissionTokens(0);
            players[i].setCurrentSpace(33);
        }

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }

            // Perform game actions here
            // For example, handle player turns and update player statistics

            // When it's time to switch to the next player's turn
            // You can use currentPlayerIndex to determine the current player
            // and then move to the next player
            Player currentPlayer = players[currentPlayerIndex];

            // For simplicity, let's just increment the current player index on every iteration
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;

            // Now you can use currentPlayer to get the current player

            // Update player statistics or handle other game logic as needed
            // For example, you can display the current player's name in the PlayerStatistics panel
            gui.updateCurrentPlayer(Player.getPlayers()[currentPlayerIndex]);
            gui.updateMoney(currentPlayer.getMoney());
            gui.updateResourceTokens(currentPlayer.getResourceTokens());
            gui.updateCurrentSpace(currentPlayer.getCurrentSpace());

            // You may also check for game over conditions and end the game when necessary

            // For example, if the current player is out of money, then end the game
            if (currentPlayer.getMoney() <= -1) {
                gameOver = true;
            }

            // make a method which can end the game
            if (gameOver) {
                // Determine the winner and break out of the loop
                String winner = Player.getPlayers()[currentPlayerIndex];

                // Display a window saying who the winner is
                MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                messageBox.setText("Game Over");
                messageBox.setMessage("The winner is: " + winner);
                messageBox.open();
                break;
            }
        }

        display.dispose();
    }

    // Add more game logic methods as needed

    public static void main(String[] args) {
        // Run the game setup to get player names
        GameSetup gameSetup = new GameSetup();
        String[] players = gameSetup.setupGame();

        // Start the game with the obtained player names
        Game game = new Game(players);
        game.start();
    }
}
