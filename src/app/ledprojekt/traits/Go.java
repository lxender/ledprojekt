package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;

import java.awt.event.KeyEvent;

public class Go extends Trait {

    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();
        CollisionLayer layer = entity.getCollisionLayerRef();
        if(event != null) {
//            System.out.println(event.getKeyCode());
            if(event.getID() == KeyEvent.KEY_RELEASED) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        if(!layer.isObstructed(entity, entity.getX(), entity.getY() + 1)) {
                            entity.setY(entity.getY() + 1);
                        }
                        break;

                    case KeyEvent.VK_LEFT:
                        if(!layer.isObstructed(entity, entity.getX() - 1, entity.getY())) {
                            entity.setX(entity.getX() - 1);
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        if(!layer.isObstructed(entity, entity.getX() + 1, entity.getY())) {
                            entity.setX(entity.getX() + 1);
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
