package app.ledprojekt.traits;

import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class AttackWithWeapon extends Trait {
    private String animationName;

    public AttackWithWeapon(String animationName) {
        this.animationName = animationName;
    }

    @Override
    public void update(Player entity) {
        KeyEvent event = entity.getKeyEventRef();
        if (event != null) {

            int attackKey = (int) entity.getKeyBindings().get("attack");

            if (event.getID() == KeyEvent.KEY_RELEASED && event.getKeyCode() == attackKey) {
                if (entity.getWeapon() != null) {
                    entity.getWeapon().setAnimationPlayState(this.animationName, true);
                }
            }
        }
    }
}
