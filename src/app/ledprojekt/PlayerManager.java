package app.ledprojekt;

import java.awt.event.KeyEvent;

public class PlayerManager {
    private Player p1;
    private Player p2;

    public PlayerManager(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Player getP1() {
        return this.p1;
    }
    public Player getP2() {
        return this.p2;
    }

    public void update(KeyEvent event, double delta) {
        this.p1.updateKeyEventRef(event);
        this.p1.updateDelta(delta);

        if (this.p2 == null) return;
        this.p2.updateKeyEventRef(event);
        this.p2.updateDelta(delta);
    }
}
