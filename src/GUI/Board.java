package GUI;
import java.util.HashSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell; // Import the Shell class

public class Board {
    private Button[][] board; // the board array

    public Board(Composite composite, Shell shell) { // Add the Shell argument
        // Create a new GridLayout with 9 columns and no gaps
        GridLayout layout = new GridLayout(9, false);
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 0;
        layout.marginWidth = 0;
        layout.marginHeight = 0;

        // Set the layout to the composite
        composite.setLayout(layout);

        // Create the board array with 9 rows and 7 columns
        board = new Button[9][7];

      // create a HashSet to store the numbers that you want to exclude
      HashSet<Integer> exclude = new HashSet<Integer>();
      exclude.add(11);
      exclude.add(12);
      exclude.add(13);
      exclude.add(15);
      exclude.add(16);
      exclude.add(17);
      exclude.add(20);
      exclude.add(21);
      exclude.add(22);
      exclude.add(24);
      exclude.add(25);
      exclude.add(26);
      exclude.add(38);
      exclude.add(39);
      exclude.add(40);
      exclude.add(42);
      exclude.add(43);
      exclude.add(44);
      exclude.add(47);
      exclude.add(48);
      exclude.add(49);
      exclude.add(51);
      exclude.add(52);
      exclude.add(53);

      // create a new hashset to store the numbers that are shops
      HashSet<Integer> shops = new HashSet<Integer>();
      shops.add(9);
      shops.add(55);

      // create a new hashset to store the numbers that are shops
      HashSet<Integer> nodes = new HashSet<Integer>();
      nodes.add(5);
      nodes.add(28);
      nodes.add(36);
      nodes.add(59);

      // create a new hashset to store the number that is start
      HashSet<Integer> start = new HashSet<Integer>();
      start.add(34);

      // create a new hashset to store the number that is government
      HashSet<Integer> government = new HashSet<Integer>();
      government.add(32);

      // Create buttons for each cell in the grid
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 7; j++) {
            int index = i * 7 + j + 1;

            // Check if the current index is in the exclude set (blank spaces)
            if (exclude.contains(index)) {
                // Create a placeholder composite for the space
                Composite placeholder = new Composite(composite, SWT.NONE);

                // Create a new GridData for the placeholder
                GridData placeholderLayoutData = new GridData();

                // Set the horizontal and vertical alignment of the placeholder to fill
                placeholderLayoutData.horizontalAlignment = SWT.FILL;
                placeholderLayoutData.verticalAlignment = SWT.FILL;

                // Set the grab excess horizontal and vertical space to true
                placeholderLayoutData.grabExcessHorizontalSpace = true;
                placeholderLayoutData.grabExcessVerticalSpace = true;

                // Set the layout data of the placeholder
                placeholder.setLayoutData(placeholderLayoutData);

                // Skip the creation of the button
                continue;
            }

            // Create a new button for each space on the board
            board[i][j] = new Button(composite, SWT.TOGGLE);
            board[i][j].setData("id", index);

            if (shops.contains(index)) {
                board[i][j].setText("Shop");
            } else if (nodes.contains(index)) {
                board[i][j].setText("Node");
            } else if (start.contains(index)) {
                board[i][j].setText("Start");
            } else if (government.contains(index)) {
                board[i][j].setText("Government");
            } else {
                board[i][j].setText("Property " + index);
            }

            // Create a new GridData for each button
            GridData buttonLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);

            // Set the horizontal and vertical alignment of the button to fill
            buttonLayoutData.horizontalAlignment = SWT.FILL;
            buttonLayoutData.verticalAlignment = SWT.FILL;

            // Set the grab excess horizontal and vertical space to true
            buttonLayoutData.grabExcessHorizontalSpace = true;
            buttonLayoutData.grabExcessVerticalSpace = true;

            // Set the layout data of the button
            board[i][j].setLayoutData(buttonLayoutData);

            // Add a listener to the button to handle clicks
            board[i][j].addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event e) {
              // get the id from the button data
              int id = (int) e.widget.getData("id");

              // print the id
              if (shops.contains(id)) {
                System.out.println("Shop index: " + id);
              } else if (nodes.contains(id)) {
                System.out.println("Node index: " + id);
              } else if (start.contains(id)) {
                System.out.println("Start index: " + id);
              } else if (government.contains(id)) {
                System.out.println("Government index: " + id);
              } else {
                System.out.println("Property index: " + id);
              }
            }
          });
        }
      }
    }
}