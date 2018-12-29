package app.ledprojekt.traits;

import app.ledprojekt.Entity;

import java.awt.event.KeyEvent;

public class Attack extends Trait {
    @Override
    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();

        if (event != null) {
            if (event.getID() == KeyEvent.KEY_RELEASED && event.getKeyCode() == KeyEvent.VK_E) {
                entity.setAnimationPlayState("attack", true);
            }
        }
    }
}
