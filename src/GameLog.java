import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GameLog {
    public void writeLogToFile(String fileName, Player currentPlayer) {
        try {
            FileWriter logFile = new FileWriter(fileName, true);
            List<LogEntry> logEntries = currentPlayer.getLogEntries();
            for (LogEntry logEntry : logEntries) {
                logFile.write(logEntry.toString() + "\n");
            }
            logFile.flush();
            logFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game log.");
            e.printStackTrace();
        }
    }
}