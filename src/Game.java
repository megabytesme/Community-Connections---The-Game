import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import GUI.Board;
import GUI.PlayerStatistics;

import Game.Player;
import Game.Dice;

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

class GUI {
    private PlayerStatistics playerStats;
    private Dice dice;
    private Label diceResultLabel;

    public GUI(Shell shell) {
        // Create a grid layout for the shell
        GridLayout shellLayout = new GridLayout(2, false);
        shell.setLayout(shellLayout);

        // Create a composite for the player buttons
        Composite playersComposite = new Composite(shell, SWT.NONE);
        GridLayout playersLayout = new GridLayout(8, true);
        playersComposite.setLayout(playersLayout);
        GridData playersData = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1);
        playersComposite.setLayoutData(playersData);

        // Create buttons for each player and add them to the composite
        String[] players = Player.getPlayers();        
        for (int i = 0; i < players.length; i++) {
            Button playerButton = new Button(playersComposite, SWT.PUSH);
            playerButton.setText(players[i]);
            GridData buttonData = new GridData(SWT.FILL, SWT.FILL, true, true);
            playerButton.setLayoutData(buttonData);
        }

        // Create a composite for the board
        Composite boardComposite = new Composite(shell, SWT.NONE);
        GridLayout boardLayout = new GridLayout(9, false);
        boardComposite.setLayout(boardLayout);
        GridData boardData = new GridData(SWT.FILL, SWT.FILL, true, true);
        boardComposite.setLayoutData(boardData);

        // Create the board using Board.java and pass the boardComposite and shell as arguments
        new Board(boardComposite, shell);

        // Create a composite for the player statistics and assets
        Composite statsComposite = new Composite(shell, SWT.NONE);
        GridLayout statsLayout = new GridLayout(2, false);
        statsComposite.setLayout(statsLayout);
        GridData statsData = new GridData(SWT.FILL, SWT.FILL, true, true);
        statsComposite.setLayoutData(statsData);

        // Create an instance of PlayerStatistics and pass the statsComposite
        playerStats = new PlayerStatistics(statsComposite);

        // Create an instance of Dice
        dice = new Dice();
        
        // Create a composite for the button and label
        Composite buttonComposite = new Composite(shell, SWT.NONE);
        GridLayout buttonLayout = new GridLayout(2, false);
        buttonComposite.setLayout(buttonLayout);
        GridData buttonData = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1);
        buttonComposite.setLayoutData(buttonData);

        // Create a button for rolling the dice
        Button rollDiceButton = new Button(buttonComposite, SWT.PUSH);
        rollDiceButton.setText("Roll Dice");
        rollDiceButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        rollDiceButton.addListener(SWT.Selection, event -> rollDice());

        // Create a label to display the result of the dice roll
        diceResultLabel = new Label(buttonComposite, SWT.NONE);
        diceResultLabel.setText("Dice Result: ");
        diceResultLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    private void rollDice() {
        int diceResult = dice.rollDice(true);
        // Update the dice result label with the rolled value
        diceResultLabel.setText("Dice Result: " + diceResult);
    }

    // Method to update the current player's name in the PlayerStatistics panel
    public void updateCurrentPlayer(String playerName) {
        if (playerStats != null) {
            playerStats.setCurrentPlayerName(playerName);
        }
    }
}
