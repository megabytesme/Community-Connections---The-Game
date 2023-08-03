import java.util.Arrays;

public class PlayerProgress {
    private boolean[] completedTasks;
    private int propertyStage; // Property enhancement level

    public PlayerProgress(int numTasks) {
        completedTasks = new boolean[numTasks];
        propertyStage = 1; // Initialize propertyStage to the starting level
    }

    public void markTaskCompleted(Square square) {
        int taskIndex = getTaskIndex(square);
        if (taskIndex >= 0) {
            completedTasks[taskIndex] = true;
        }
    }

    public int getTaskIndex(Square square) {
        for (int i = 0; i < completedTasks.length; i++) {
            if (square.getName().equals(getTaskName(i))) {
                return i;
            }
        }
        return -1;
    }

    public boolean isTaskCompleted(int taskIndex) {
        return completedTasks[taskIndex];
    }

    public String getCompletedTasksString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < completedTasks.length; i++) {
            if (completedTasks[i]) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(getTaskName(i));
                first = false;
            }
        }
        return sb.toString();
    }

    public boolean isPropertyDeckEnhanced() {
        return propertyStage == 3;
    }

    public boolean isCompletedCommunityNetwork(Player[] players) {
        for (Player player : players) {
            if (player.getPropertyStage() < 3) {
                return false;
            }
        }
        return allTasksCompleted();
    }

    private boolean allTasksCompleted() {
        for (boolean completed : completedTasks) {
            if (!completed) {
                return false;
            }
        }
        return true;
    }

    public int getCompletedTasksCount() {
        int count = 0;
        for (boolean completed : completedTasks) {
            if (completed) {
                count++;
            }
        }
        return count;
    }

    private String getTaskName(int taskIndex) {
        String[] taskNames = {
            "Acquiring Permissions",
            "Acquiring and Installing Hardware",
            "Informing and Educating the Community",
            "Collecting Resources",
            // Add more task names if needed
        };
        if (taskIndex >= 0 && taskIndex < taskNames.length) {
            return taskNames[taskIndex];
        }
        return "UNKNOWN";
    }

    public int getCompletedTasks() {
        return getCompletedTasksCount();
    }

    public void addCompletedTask() {
        // Increment the property stage when a task is completed
        propertyStage++;
    }

    public void advanceProperty() {
        // Advance the property to the next stage (you need to implement the logic for this)
        // Here, you can define how the property advances to the next stage based on the player's completed tasks.
        // For example, if the player has completed a certain number of tasks, you can increment the propertyStage.
        // You can implement your custom logic here based on the game's rules.
    }

    public int getPropertyStage() {
        return propertyStage;
    }

    public void incrementPropertyStage() {
        propertyStage++;
    }
}