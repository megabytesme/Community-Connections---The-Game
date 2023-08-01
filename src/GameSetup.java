import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class GameSetup {

    public String[] setupGame() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Game Setup");

        // Create a grid layout for the shell
        GridLayout shellLayout = new GridLayout(2, false);
        shell.setLayout(shellLayout);

        // Create a label for player names input
        Label nameLabel = new Label(shell, SWT.NONE);
        nameLabel.setText("Enter player names (comma-separated):");

        // Create a text field for player names input
        Text nameText = new Text(shell, SWT.BORDER);
        GridData textData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        nameText.setLayoutData(textData);

        // Create a button to start the game
        Button startButton = new Button(shell, SWT.PUSH);
        startButton.setText("Start Game");
        GridData buttonData = new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1);
        startButton.setLayoutData(buttonData);

        // Create a list to store player names
        List<String> playerNames = new ArrayList<>();

        // Add a selection listener to the startButton
        startButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Split player names by commas and add them to the list
                String[] names = nameText.getText().split(",");
                for (String name : names) {
                    playerNames.add(name.trim());
                }

                // Close the setup shell
                shell.close();
            }
        });

        shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();

        // Convert the list of player names to an array of strings and return it
        return playerNames.toArray(new String[0]);
    }
}
