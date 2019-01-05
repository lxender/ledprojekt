package app.ledprojekt.traits;

import app.ledprojekt.Entity;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class Turn extends Trait {
    @Override
    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();

        if (event != null) {
            if (event.getID() == KeyEvent.KEY_RELEASED) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (entity instanceof Player) {
                            if (!((Player) entity).isFlipped()) {
                                ((Player) entity).flip();
                            }
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        if (entity instanceof Player) {
                            if (((Player) entity).isFlipped()) {
                                ((Player) entity).flip();
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }
}