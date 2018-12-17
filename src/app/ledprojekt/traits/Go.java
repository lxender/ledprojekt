package app.ledprojekt.traits;

import app.ledprojekt.Entity;

import java.awt.event.KeyEvent;

public class Go extends Trait {

    public void update(Entity entity, Object... options) {
        // System.out.println(event);
        KeyEvent event = (KeyEvent) this.getClassFromArray("KeyEvent", options);
        if(event != null) {
            if(event.getID() == KeyEvent.KEY_RELEASED) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        entity.setY(entity.getY() - 1);
                        break;

                    case KeyEvent.VK_DOWN:
                        entity.setY(entity.getY() + 1);
                        break;

                    case KeyEvent.VK_LEFT:
                        entity.setX(entity.getX() - 1);
                        break;

                    case KeyEvent.VK_RIGHT:
                        entity.setX(entity.getX() + 1);
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
