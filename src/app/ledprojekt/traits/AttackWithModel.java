package app.ledprojekt.traits;

import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class AttackWithModel extends Trait {
    private String animationName;

    public AttackWithModel(String animationName) {
        this.animationName = animationName;
    }

    @Override
    public void update(Player entity) {
        KeyEvent event = entity.getKeyEventRef();
        if (event != null && event.getID() == KeyEvent.KEY_RELEASED) {
            int secondaryAttackKey = (int) entity.getKeyBindings().get("secondary-attack");
            if (event.getKeyCode() == secondaryAttackKey && entity.hasAnimation(this.animationName)) {
                entity.setAnimationPlayState(animationName, true);
            }
        }
    }
}
