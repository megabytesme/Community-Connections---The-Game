public class PlayerProperty {
    private Square property;
    private boolean developed;
    private String name;
    private boolean hasPermission;
    private boolean hardwareInstalled;
    private boolean educationCompleted;

    public PlayerProperty(String name) {
        this.name = name;
        this.developed = false;
        this.hasPermission = false;
        this.hardwareInstalled = false;
        this.educationCompleted = false;
    }

    public String getName() {
        return name;
    }

    public boolean hasPermission() {
        return hasPermission;
    }

    public void setPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public boolean isHardwareInstalled() {
        return hardwareInstalled;
    }

    public void setHardwareInstalled(boolean hardwareInstalled) {
        this.hardwareInstalled = hardwareInstalled;
    }

    public boolean isEducationCompleted() {
        return educationCompleted;
    }

    public void setEducationCompleted(boolean educationCompleted) {
        this.educationCompleted = educationCompleted;
    }

    public boolean isDeveloped() {
        return developed;
    }

    public void setDeveloped(boolean developed) {
        this.developed = developed;
    }
}
