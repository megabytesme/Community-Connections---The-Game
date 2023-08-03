import java.util.ArrayList;
import java.util.List;

public class PlayerProperty {
    private Square property;
    private boolean developed;

    public PlayerProperty(Square property) {
        this.property = property;
        this.developed = false;
    }

    public Square getProperty() {
        return property;
    }

    public boolean isDeveloped() {
        return developed;
    }

    public void setDeveloped(boolean developed) {
        this.developed = developed;
    }
}