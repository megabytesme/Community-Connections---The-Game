package GUI; // Make sure this is the correct package for PlayerStatistics

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class PlayerStatistics {
    private Label currentPlayerLabel; // Assuming you have a Label to display the current player's name
    private Label moneyLabel; // Label to display the current player's money
    private Label resourceTokensLabel; // Label to display the current player's resource tokens
    private Label permissionTokensLabel; // Label to display the current player's permission tokens
    private Label currentSpaceLabel; // Label to display the current player's current space

    public PlayerStatistics(Composite parent) {
        // Create a layout for the composite
        GridLayout layout = new GridLayout(2, false);
        parent.setLayout(layout);

        // Create a label to display the current player's name
        currentPlayerLabel = new Label(parent, SWT.NONE);
        currentPlayerLabel.setText("Current Player: N/A"); // Default text when no player is set
        currentPlayerLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Create a label to display the current player's money
        moneyLabel = new Label(parent, SWT.NONE);
        moneyLabel.setText("Money: N/A");
        moneyLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        // Create a label to display the current player's resource tokens
        resourceTokensLabel = new Label(parent, SWT.NONE);
        resourceTokensLabel.setText("Resource Tokens: N/A");
        resourceTokensLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        // Create a label to display the current player's permission tokens
        permissionTokensLabel = new Label(parent, SWT.NONE);
        permissionTokensLabel.setText("Permission Tokens: N/A");
        permissionTokensLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        // Create a label to display the current player's current space
        currentSpaceLabel = new Label(parent, SWT.NONE);
        currentSpaceLabel.setText("Current Space: N/A");
        currentSpaceLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    }

    // Method to set the current player's name in the label
    public void setCurrentPlayerName(String playerName) {
        if (currentPlayerLabel != null) {
            // make a new line between the words "Current Player" and the player's name
            currentPlayerLabel.setText("Current Player:\n" + playerName);
        } else {
            System.out.println("Current Player: N/A");
        }
    }

    // Method to update the current player's money in the label
    public void setMoney(int money) {
        if (moneyLabel != null) {
            moneyLabel.setText("Money: " + String.valueOf(money));
        } else {
            System.out.println("Money: N/A");
        }
    }

    // Method to update the current player's resource tokens in the label
    public void setResourceTokens(int resourceTokens) {
        if (resourceTokensLabel != null) {
            resourceTokensLabel.setText("Resource Tokens: " + String.valueOf(resourceTokens));
        } else {
            System.out.println("Resource Tokens: N/A");
        }
    }

    // Method to update the current player's permission tokens in the label
    public void setPermissionTokens(int permissionTokens) {
        if (permissionTokensLabel != null) {
            permissionTokensLabel.setText("Permission Tokens: " + String.valueOf(permissionTokens));
        } else {
            System.out.println("Permission Tokens: N/A");
        }
    }

    // Method to update the current player's current space in the label
    public void setCurrentSpace(int currentSpace) {
        if (currentSpaceLabel != null) {        
            switch (currentSpace) {
                case 5:
                    currentSpaceLabel.setText("Current Space: Top node");
                    break;
                case 9:
                    currentSpaceLabel.setText("Current Space: Right shop");
                    break;
                case 28:
                    currentSpaceLabel.setText("Current Space: Left node");
                    break;
                case 32:
                    currentSpaceLabel.setText("Current Space: Government");
                    break;
                case 34:
                    currentSpaceLabel.setText("Current Space: Start");
                    break;
                case 36:
                    currentSpaceLabel.setText("Current Space: Right node");
                    break;
                case 55:
                    currentSpaceLabel.setText("Current Space: Left shop");
                    break;
                case 59:
                    currentSpaceLabel.setText("Current Space: Bottom node");
                    break;
                default:
                    currentSpaceLabel.setText("Current Space: " + String.valueOf(currentSpace));
                    break;
            }
        }
    }
}
