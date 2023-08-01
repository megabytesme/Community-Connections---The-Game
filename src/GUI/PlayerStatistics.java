package GUI; // Make sure this is the correct package for PlayerStatistics

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class PlayerStatistics {
    private Label currentPlayerLabel; // Assuming you have a Label to display the current player's name

    public PlayerStatistics(Composite parent) {
        // Create a layout for the composite
        GridLayout layout = new GridLayout(1, false);
        parent.setLayout(layout);

        // Create a label to display the current player's name
        currentPlayerLabel = new Label(parent, SWT.NONE);
        currentPlayerLabel.setText("Current Player: N/A"); // Default text when no player is set
        currentPlayerLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
    }

    // Method to set the current player's name in the label
    public void setCurrentPlayerName(String playerName) {
        if (currentPlayerLabel != null) {
            currentPlayerLabel.setText("Current Player: " + playerName);
        }
    }
}
