package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Model;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class AttackWithWeapon extends Trait {
    private String animationName;

    public AttackWithWeapon(String animationName) {
        this.animationName = animationName;
    }

    @Override
    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();

        if (event != null) {
            if (event.getID() == KeyEvent.KEY_RELEASED && event.getKeyCode() == KeyEvent.VK_E) {
                if (entity instanceof Player && ((Player) entity).getWeapon() != null) {
                    ((Player) entity).getWeapon().setAnimationPlayState(this.animationName, true);
                }
            }
        }
    }
}
