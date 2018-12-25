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
                    case KeyEvent.VK_UP:
//                        System.out.println(layer.isObstructedY(entity, entity.getY() - 1));
                        if(!layer.isObstructedY(entity, entity.getY() - 2)) {
                            entity.setY(entity.getY() - 2);
                        }
                        break;

                    case KeyEvent.VK_DOWN:
//                        System.out.println(layer.isObstructedY(entity, entity.getY() + 1));
                        if(!layer.isObstructedY(entity, entity.getY() + 1)) {
                            entity.setY(entity.getY() + 1);
                        }
                        break;

                    case KeyEvent.VK_LEFT:
//                        System.out.println("Is left obstructed? " + layer.isObstructedX(entity, entity.getX() - 1));
                        if(!layer.isObstructedX(entity, entity.getX() - 1)) {
                            entity.setX(entity.getX() - 1);
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
//                        System.out.println("Is right obstructed? " + layer.isObstructedX(entity, entity.getX() + 1));
                        if(!layer.isObstructedX(entity, entity.getX() + 1)) {
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
