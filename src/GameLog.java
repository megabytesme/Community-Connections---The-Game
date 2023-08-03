import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GameLog {
    public GameLog(List<Player> players) {
    }

    public void writeLogToFile(String fileName, Player currentPlayer) {
        // add to the log file and flush the log entries
        try {
            FileWriter logFile = new FileWriter(fileName, true); // Append mode
            List<LogEntry> logEntries = currentPlayer.getLogEntries();
            for (LogEntry logEntry : logEntries) {
                logFile.write(logEntry.toString() + "\n");
            }
            logFile.flush(); // Flush the buffered data to the file
            logFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game log.");
            e.printStackTrace();
        }
    }
}
