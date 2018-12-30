package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Model;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class Attack extends Trait {
    public Attack(Player player) {
        Model weapon = new Model(new int[][][]{
                {{0,34,24,1}},
                {{0,86,34,1}},
                {{0,42,47,1}},
                {{0,23,97,1}}
        });
        player.addWeapon(weapon, 3, 4);
        Model frame1 = player.characterModel.calculateModelWithAttachment(weapon, 3, 4);
        Model frame2 = player.characterModel.calculateModelWithAttachment(new Model(new int[][][]{
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0,23,24,1}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0,34,24,1}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0,45,24,1}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0,65,24,1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
        }), 3, 4);
        Model frame3 = player.characterModel.calculateModelWithAttachment(new Model(new int[][][]{
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0,65,24,1}, {0,45,24,1}, {0,34,24,1}, {0,23,24,1}},
        }), 3, 4);
        player.addAnimation("attack", frame1, frame2, frame3);
    }
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
