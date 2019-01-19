package app.ledprojekt.traits;

import app.ledprojekt.Collidable;
import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class AttackWithModel extends Trait {
    private String animationName;

    public AttackWithModel(String animationName) {
        this.animationName = animationName;
    }

    @Override
    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();
        if (event != null && event.getID() == KeyEvent.KEY_RELEASED) {
            if (entity instanceof Player) {
                int secondaryAttackKey = (int) ((Player) entity).getKeyBindings().get("secondary-attack");
                if (event.getKeyCode() == secondaryAttackKey && ((Player) entity).hasAnimation(this.animationName)) {
                    entity.setAnimationPlayState(animationName, true);
                }
            }
        }
    }
}
