public class PlayerProgress {
    private int[] completedTasks;

    public PlayerProgress(int numTasks) {
        completedTasks = new int[numTasks];
    }

    public void markTaskCompleted(int taskIndex) {
        completedTasks[taskIndex] = 1;
    }

    public boolean isTaskCompleted(int taskIndex) {
        return completedTasks[taskIndex] == 1;
    }

    public String getCompletedTasksString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < completedTasks.length; i++) {
            if (completedTasks[i] == 1) {
                sb.append(SquareType.values()[i]).append(", ");
            }
        }
        // Remove the trailing ", " if tasks are completed
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public boolean isCompletedCommunityNetwork() {
        for (int i = 0; i < completedTasks.length; i++) {
            if (completedTasks[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
