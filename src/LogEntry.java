import java.time.LocalDateTime;

public class LogEntry {
    private LocalDateTime timestamp;
    private String player;
    private String action;

    public LogEntry(LocalDateTime timestamp, String player, String action) {
        this.timestamp = timestamp;
        this.player = player;
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPlayer() {
        return player;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return timestamp + " - " + player + ": " + action;
    }
}