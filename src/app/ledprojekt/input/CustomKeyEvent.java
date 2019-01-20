package app.ledprojekt.input;

import java.awt.event.KeyEvent;

public class CustomKeyEvent {
    private int code;
    private int id;

    public CustomKeyEvent(int code, int id) {
        this.code = code;
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public int getKeyCode() {
        return this.code;
    }
}
