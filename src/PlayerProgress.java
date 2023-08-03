public class PlayerProgress {
    private boolean[] completedTasks;

    public PlayerProgress(int numTasks) {
        completedTasks = new boolean[numTasks];
    }

    // Update the markTaskCompleted method to take Square object as an argument
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

    public boolean isCompletedCommunityNetwork() {
        // For simplicity, we assume that the player has completed the community network
        // if all tasks are completed.
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
        // In a real game, you would have a list of all tasks and retrieve the name using the index.
        // For simplicity, we'll use a placeholder task name here.
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
}
