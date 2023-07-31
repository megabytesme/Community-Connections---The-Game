package GUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import Game.Player;

public class Players {
    private final static int MIN_PLAYERS = 2;
    private final static int MAX_PLAYERS = 8;

    public Players(Shell shell) {
        // create a composite to hold the player buttons
        Composite playersComposite = new Composite(shell, SWT.NONE);

        // create a grid layout for the composite
        GridLayout playersLayout = new GridLayout(MIN_PLAYERS, false);
        playersComposite.setLayout(playersLayout);

        // get the current players from Player.java
        String[] players = Player.getPlayers();

        // calculate the number of columns for the grid layout based on the number of players
        int numColumns = Math.min(players.length, MAX_PLAYERS);

        // set the number of columns for the grid layout
        playersLayout.numColumns = numColumns;

        // create buttons for each player and add them to the composite
        for (int i = 0; i < numColumns; i++) {
            if (i < players.length) {
                String playerName = players[i];
                Button playerButton = new Button(playersComposite, SWT.PUSH);
                playerButton.setText(playerName);
                // Set any other properties you want for the buttons here
                GridData buttonData = new GridData(SWT.FILL, SWT.FILL, true, true);
                playerButton.setLayoutData(buttonData);
            }
        }
    }
}