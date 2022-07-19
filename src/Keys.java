import java.awt.event.KeyEvent;

public enum Keys{

    LEFT("Left", KeyEvent.VK_LEFT, -2, 0),
    RIGHT("Right", KeyEvent.VK_RIGHT, 2, 0),
    UP("Up", KeyEvent.VK_UP, 0, -2),
    DOWN("Down", KeyEvent.VK_DOWN, 0, 2),
    SPACE("Space", KeyEvent.VK_SPACE, 0, 0);
    private String name;
    private int eventCode;
    private int moveX;
    private int moveY;

    Keys(String name, int eventCode, int moveX, int moveY) {
        this.name = name;
        this.eventCode = eventCode;
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public String getName() {
        return name;
    }

    public int getEventCode() {
        return eventCode;
    }

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }
}
