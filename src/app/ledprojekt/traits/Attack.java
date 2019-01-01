package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Model;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class Attack extends Trait {
    private String animationName = "";

    public Attack(String name) {
        this.animationName = name;
    }

    @Override
    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();

        if (event != null) {
            if (event.getID() == KeyEvent.KEY_RELEASED && event.getKeyCode() == KeyEvent.VK_E) {
                entity.setAnimationPlayState(animationName, true);
            }
        }
    }
}
