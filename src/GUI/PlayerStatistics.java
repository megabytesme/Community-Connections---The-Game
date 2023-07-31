package GUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import Game.Player;

public class PlayerStatistics {
    private Composite composite;

    public PlayerStatistics(Composite parent) {
        composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);
        composite.setLayout(layout);

        // Add labels for player statistics, assets, and name
        Label nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText("Current Player:");

        Label playerNameLabel = new Label(composite, SWT.NONE);
        playerNameLabel.setText(""); // You can set the player's name here

        Label propertiesLabel = new Label(composite, SWT.NONE);
        propertiesLabel.setText("Properties:");

        Label propertiesValueLabel = new Label(composite, SWT.NONE);
        propertiesValueLabel.setText(""); // You can set the player's properties count here

        Label permissionTokensLabel = new Label(composite, SWT.NONE);
        permissionTokensLabel.setText("Permission Tokens:");

        Label permissionTokensValueLabel = new Label(composite, SWT.NONE);
        permissionTokensValueLabel.setText(""); // You can set the player's permission tokens count here

        Label moneyLabel = new Label(composite, SWT.NONE);
        moneyLabel.setText("Money:");

        Label moneyValueLabel = new Label(composite, SWT.NONE);
        moneyValueLabel.setText(""); // You can set the player's money value here

        Label resourceTokensLabel = new Label(composite, SWT.NONE);
        resourceTokensLabel.setText("Resource Tokens:");

        Label resourceTokensValueLabel = new Label(composite, SWT.NONE);
        resourceTokensValueLabel.setText(""); // You can set the player's resource tokens count here

        // Set layout data for the composite
        GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
        composite.setLayoutData(data);
    }

    // Method to update the player statistics and assets
    public void updatePlayerStatistics(Player player) {
        // Set the player's name
        // playerNameLabel.setText(player.getName());

        // Set the player's properties count
        // propertiesValueLabel.setText(Integer.toString(player.getPropertiesCount()));

        // Set the player's permission tokens count
        // permissionTokensValueLabel.setText(Integer.toString(player.getPermissionTokensCount()));

        // Set the player's money value
        // moneyValueLabel.setText(Integer.toString(player.getMoney()));

        // Set the player's resource tokens count
        // resourceTokensValueLabel.setText(Integer.toString(player.getResourceTokensCount()));
    }
}
