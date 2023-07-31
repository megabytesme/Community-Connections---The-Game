import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import GUI.Board;
import GUI.PlayerStatistics;
import Game.Player;

public class Game {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Community Connections");

        new GUI(shell);

        shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }
}

class GUI {
    public GUI(Shell shell) {
        // Create a grid layout for the shell
        GridLayout shellLayout = new GridLayout(2, false);
        shell.setLayout(shellLayout);

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
        PlayerStatistics playerStats = new PlayerStatistics(statsComposite);

        // Create a composite for the player buttons
        Composite playersComposite = new Composite(shell, SWT.NONE);
        GridLayout playersLayout = new GridLayout(8, true);
        playersComposite.setLayout(playersLayout);
        GridData playersData = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1);
        playersComposite.setLayoutData(playersData);

        // Get the current players from Player.java
        String[] players = Player.getPlayers();

        // Create buttons for each player and add them to the composite
        for (int i = 0; i < Math.min(players.length, 8); i++) {
            Button playerButton = new Button(playersComposite, SWT.PUSH);
            playerButton.setText(players[i]);
            GridData buttonData = new GridData(SWT.FILL, SWT.FILL, true, true);
            playerButton.setLayoutData(buttonData);
        }
    }
}
