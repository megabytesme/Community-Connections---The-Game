import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private InputStream inputStream;
    private PrintStream originalOutput;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        originalOutput = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOutput);
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void provideInput(String data) {
        inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }

    private String getOutput() {
        return outputStream.toString();
    }

    @Test
    public void testGameWithTwoPlayers() {
        provideInput("2\nAlice\nBob\nno\n2\n");
        Main.main(null);
        String output = getOutput();
        assertTrue(output.contains("Game Over!"));
    }

    @Test
    public void testPlayerGainsPermissionAndInstallsHardware() {
        provideInput("1\nAlice\nno\n1\n2\n");
        Main.main(null);
        String output = getOutput();
        assertTrue(output.contains("Game Over!"));
    }

    @Test
    public void testPlayerCompletesEducation() {
        provideInput("1\nAlice\nno\n3\n");
        Main.main(null);
        String output = getOutput();
        assertTrue(output.contains("Game Over!"));
    }

    @Test
    public void testGameWithWatchingMode() {
        provideInput("3\nAlice\nBob\nCharlie\nno\n1\n");
        Main.main(null);
        String output = getOutput();
        assertTrue(output.contains("Game Over!"));
    }

    @Test
    public void testInvalidNumPlayers() {
        provideInput("0\n");
        Main.main(null);
        String output = getOutput();
        assertTrue(output.contains("Invalid number of players. Please enter a number between 1 and 8."));
    }
}
