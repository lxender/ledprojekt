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
                        if(!layer.collides(entity.getModel().get2DArray(), entity.getX() - 1, entity.getY(), map)) {
                            if(entity instanceof Player) {
                                if (((Player) entity).hasAnimation("walk")) {
                                    entity.setAnimationPlayState("walk", true);
                                }
                            }
                            entity.setX(entity.getX() - 1);
                        }
                        break;

                    case KeyEvent.VK_RIGHT:
                        if(!layer.collides(entity.getModel().get2DArray(), entity.getX() + 1, entity.getY(), map)) {
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
