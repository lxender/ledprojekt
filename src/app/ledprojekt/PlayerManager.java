package app.ledprojekt;

import app.ledprojekt.input.CustomKeyEvent;
import ledControl.BoardController;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerManager {
    private Player p1;
    private Player p2;

    public static final HashMap<String, Integer> keybindingsP1 = new HashMap<>();
    static {
        keybindingsP1.put("left", KeyEvent.VK_A);
        keybindingsP1.put("right", KeyEvent.VK_D);
        keybindingsP1.put("jump", KeyEvent.VK_W);
        keybindingsP1.put("attack", KeyEvent.VK_E);
        keybindingsP1.put("secondary-attack", KeyEvent.VK_Q);
    }
    public static final HashMap<String, Integer> keybindingsP2 = new HashMap<>();
    static {
        keybindingsP2.put("left", KeyEvent.VK_LEFT);
        keybindingsP2.put("right", KeyEvent.VK_RIGHT);
        keybindingsP2.put("jump", KeyEvent.VK_UP);
        keybindingsP2.put("attack", KeyEvent.VK_L);
        keybindingsP2.put("secondary-attack", KeyEvent.VK_K);
    }

    public PlayerManager(BoardController controller, Player p1, Player p2) {
        this.p1 = p1;
        if (this.p1 != null) {
            this.p1.setX(1);
        }

        this.p2 = p2;
        if (this.p2 != null) {
            this.p2.setX(12);
        }

        if (this.p1 != null && this.p2 != null) {
            this.p1.setKeyBindings(keybindingsP1);
            if (this.p1.hasTrait("Turn")) {
                this.p1.removeTrait("Turn");
            }

            this.p2.setKeyBindings(keybindingsP2);
            if (this.p2.hasTrait("Turn")) {
                this.p2.removeTrait("Turn");
            }
            this.p2.flip();
            this.p2.healthbar.setX(controller.getWidth() - this.p2.healthbar.getMaxLength());
        }
    }

    public List getPlayersAsList() {
        List<Player> list = new ArrayList<>();
        if (this.p1 != null) {
            list.add(this.p1);
        }
        if (this.p2 != null) {
            list.add(this.p2);
        }
        return list;
    }

    public void update(CustomKeyEvent event, double delta) {
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
