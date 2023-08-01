package GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import Game.Dice;
import Game.Player;

public class GUI {
    private PlayerStatistics playerStats;
    private Dice dice;
    private Label diceResultLabel;
    private boolean isSingleDice = true;

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
        GridLayout statsLayout = new GridLayout(5, false);
        statsComposite.setLayout(statsLayout);
        GridData statsData = new GridData(SWT.FILL, SWT.FILL, true, true);
        statsComposite.setLayoutData(statsData);

        // Create an instance of PlayerStatistics and pass the statsComposite
        playerStats = new PlayerStatistics(statsComposite);

        // Create an instance of Dice
        dice = new Dice();
        
        // Create a composite for the button and label
        Composite buttonComposite = new Composite(shell, SWT.NONE);
        GridLayout buttonLayout = new GridLayout(3, false);
        buttonComposite.setLayout(buttonLayout);
        GridData buttonData = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1);
        buttonComposite.setLayoutData(buttonData);

        // Create a button for rolling the dice
        Button rollDiceButton = new Button(buttonComposite, SWT.PUSH);
        rollDiceButton.setText("Roll Dice");
        rollDiceButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        

        // Create a label to display the result of the dice roll
        diceResultLabel = new Label(buttonComposite, SWT.NONE);
        diceResultLabel.setText("Dice Result: ");
        diceResultLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // Create a checkbox for toggling the single or double dice mode
        Button singleDiceButton = new Button(buttonComposite, SWT.CHECK);
        singleDiceButton.setText("Single Dice");
        singleDiceButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        // toggle the isSingleDice variable when the checkbox is clicked
        singleDiceButton.addListener(SWT.Selection, event -> isSingleDice = !isSingleDice);
        rollDiceButton.addListener(SWT.Selection, event -> rollDice(isSingleDice));
    }

    private void rollDice(boolean isSingleDice) {
        int diceResult = dice.rollDice(isSingleDice);
        diceResultLabel.setText("Dice Result: " + diceResult);
    }

    // Method to update the current player's name in the PlayerStatistics panel
    public void updateCurrentPlayer(String playerName) {
        if (playerStats != null) {
            playerStats.setCurrentPlayerName(playerName);
        }
    }

    // Method to update the current player's money in the PlayerStatistics panel
    public void updateMoney(int money) {
        if (playerStats != null) {
            playerStats.setMoney(money);
        }
    }

    // Method to update the current player's resource tokens in the PlayerStatistics panel
    public void updateResourceTokens(int resourceTokens) {
        if (playerStats != null) {
            playerStats.setResourceTokens(resourceTokens);
        }
    }

    // Method to update the current player's current space in the PlayerStatistics panel
    public void updateCurrentSpace(int currentSpace) {
        if (playerStats != null) {
            playerStats.setCurrentSpace(currentSpace);
        }
    }
}