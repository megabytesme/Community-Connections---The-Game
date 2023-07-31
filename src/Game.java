import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Game {
    public static void main(String[] args) {
        // create a new display to manage the UI thread
        Display display = new Display();

        // create a new shell to display the board
        Shell shell = new Shell(display);

        // set the title of the shell
        shell.setText("Board");

        // create a new instance of the board and pass the shell as an argument
        new Board(shell);

        // pack the shell to fit the board size
        shell.pack();

        // make the shell visible
        shell.open();

        // run the event loop until the shell is disposed
        while (!shell.isDisposed()) {
        if (!display.readAndDispatch()) display.sleep();
        }

        // dispose the display when done
        display.dispose();
    }
}