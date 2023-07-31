import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class Game {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Board");

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
        GridLayout shellLayout = new GridLayout(1, false);
        shell.setLayout(shellLayout);

        // Create an outer composite to hold the player buttons
        Composite outerComposite = new Composite(shell, SWT.NONE);
        GridLayout outerLayout = new GridLayout(1, false);
        outerComposite.setLayout(outerLayout);
        GridData outerData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        outerComposite.setLayoutData(outerData);

        // Create a composite for player buttons
        Composite playersComposite = new Composite(outerComposite, SWT.NONE);
        GridLayout playersLayout = new GridLayout(8, true);
        playersComposite.setLayout(playersLayout);

        // Get the current players from Player.java
        String[] players = Player.getPlayers();

        // Create buttons for each player and add them to the composite
        for (int i = 0; i < Math.min(players.length, 8); i++) {
            Button playerButton = new Button(playersComposite, SWT.PUSH);
            playerButton.setText(players[i]);
            GridData buttonData = new GridData(SWT.FILL, SWT.FILL, true, true);
            playerButton.setLayoutData(buttonData);
        }

        // Create a composite for the board
        Composite boardComposite = new Composite(shell, SWT.NONE);
        GridLayout boardLayout = new GridLayout(9, false);
        boardComposite.setLayout(boardLayout);
        GridData boardData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        boardComposite.setLayoutData(boardData);

        // Create the board using Board.java and pass the boardComposite and shell as arguments
        new Board(boardComposite, shell);

        // Create a composite for the labels "2" and "3"
        Composite labelsComposite = new Composite(shell, SWT.NONE);
        GridLayout labelsLayout = new GridLayout(2, true);
        labelsComposite.setLayout(labelsLayout);

        // Create the label "3" and add it to the composite
        Label label3 = new Label(labelsComposite, SWT.NONE);
        label3.setText("3");
        label3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

        // Set layout data for the outer composite, board composite, and the labels composite
        GridData playersCompositeData = new GridData(SWT.FILL, SWT.FILL, true, false);
        playersComposite.setLayoutData(playersCompositeData);

        GridData boardCompositeData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        boardComposite.setLayoutData(boardCompositeData);

        GridData labelsCompositeData = new GridData(SWT.FILL, SWT.FILL, true, true);
        labelsComposite.setLayoutData(labelsCompositeData);
    }
}