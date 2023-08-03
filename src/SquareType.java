import java.util.Scanner;

public enum SquareType {
    PERMISSION,
    HARDWARE,
    EDUCATION,
    RESOURCE,
    START
}

// Nested interface for square actions
interface SquareAction {
    void performTask(Player player, Scanner scanner);
}