import java.util.HashSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Board {
  private Button[][] board; // the board array

  public Board(Shell shell) {
    // create a new GridLayout with 9 columns and no gaps
    GridLayout layout = new GridLayout(9, false);
    layout.horizontalSpacing = 0;
    layout.verticalSpacing = 0;
    layout.marginWidth = 0;
    layout.marginHeight = 0;

    // set the layout to the numbersComposite
    shell.setLayout(layout);

    // create the board array with 9 rows and 7 columns
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

    // loop through the board array and create the components
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        // calculate the current index from i and j
        int index = i * board[i].length + j + 1;

        // check if the current index is in the exclude set
        if (exclude.contains(index)) {
          // create a placeholder composite for the space
          Composite placeholder = new Composite(shell, SWT.NONE);

          // create a new GridData for the placeholder
          GridData placeholderLayoutData = new GridData();

          // set the horizontal and vertical alignment of the placeholder to fill
          placeholderLayoutData.horizontalAlignment = SWT.FILL;
          placeholderLayoutData.verticalAlignment = SWT.FILL;

          // set the grab excess horizontal and vertical space to true
          placeholderLayoutData.grabExcessHorizontalSpace = true;
          placeholderLayoutData.grabExcessVerticalSpace = true;

          // set the layout data of the placeholder
          placeholder.setLayoutData(placeholderLayoutData);

          // skip the creation of the button
          continue;
        }

        // create a new button for each space on the board
        board[i][j] = new Button (shell, SWT.TOGGLE);

        // set the id as the current index
        board[i][j].setData("id", index);

        // create a new GridData for each button
        GridData buttonLayoutData = new GridData();

        // set the horizontal and vertical alignment of the button to fill
        buttonLayoutData.horizontalAlignment = SWT.FILL;
        buttonLayoutData.verticalAlignment = SWT.FILL;

        // set the grab excess horizontal and vertical space to true
        buttonLayoutData.grabExcessHorizontalSpace = true;
        buttonLayoutData.grabExcessVerticalSpace = true;

        // set the layout data of the button
        board[i][j].setLayoutData(buttonLayoutData);

        // add a listener to the button to handle clicks
        board[i][j].addListener (SWT.Selection, new Listener() {
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