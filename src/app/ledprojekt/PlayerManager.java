package app.ledprojekt;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerManager {
    private Player p1;
    private Player p2;

    public static final HashMap<String, Integer> keybindingsP1 = new HashMap<>();
    public static final HashMap<String, Integer> keybindingsP2 = new HashMap<>();

    public PlayerManager(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;

        keybindingsP1.put("left", KeyEvent.VK_A);
        keybindingsP1.put("right", KeyEvent.VK_D);
        keybindingsP1.put("jump", KeyEvent.VK_W);
        keybindingsP1.put("attack", KeyEvent.VK_E);
        if (this.p1 != null) {
            //this.p1.setKeyBindings(keybindingsP2);
        }

        keybindingsP2.put("left", KeyEvent.VK_LEFT);
        keybindingsP2.put("right", KeyEvent.VK_RIGHT);
        keybindingsP2.put("jump", KeyEvent.VK_UP);
        keybindingsP2.put("attack", KeyEvent.VK_L);
        if (this.p2 != null) {
            this.p2.setKeyBindings(keybindingsP2);
        }

        if (this.p1 != null && this.p2 != null) {
            if (this.p1.hasTrait("Turn")) {
                this.p1.removeTrait("Turn");
            }

            if (this.p2.hasTrait("Turn")) {
                this.p2.removeTrait("Turn");
            }
            this.p2.flip();
        }
    }

    public Player getP1() {
        return this.p1;
    }
    public Player getP2() {
        return this.p2;
    }

    public void update(KeyEvent event, double delta) {
        if (this.p1 != null) {
            this.p1.updateKeyEventRef(event);
            this.p1.updateDelta(delta);
        }

        if (this.p2 != null) {
            this.p2.updateKeyEventRef(event);
            this.p2.updateDelta(delta);
        }
    }
}
