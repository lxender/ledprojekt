package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Model;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Go extends Trait {

    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();
        CollisionLayer layer = entity.getCollisionLayerRef();
        if(event != null) {
//            System.out.println(event.getKeyCode());
            if(event.getID() == KeyEvent.KEY_RELEASED) {
                int[][] map = layer.createRelativeCollisionMatrix(entity);

                switch (event.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        entity.setX(entity.getX() - 1);
                        if(layer.collides(entity, map)) {
                            entity.setX(entity.getX() + 1);
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        entity.setX(entity.getX() + 1);
                        if(layer.collides(entity, map)) {
                            entity.setX(entity.getX() - 1);
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
