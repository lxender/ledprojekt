package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Model;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Go extends Trait {
    public Go() {}

    String animationName;
    public Go(String animationName) { this.animationName = animationName; }

    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();
        CollisionLayer layer = entity.getCollisionLayerRef();

        if(event != null) {
            if(event.getID() == KeyEvent.KEY_RELEASED) {
                int[][] map = layer.createRelativeCollisionMatrix(entity);

                int leftKey = (int) ((Player) entity).getKeyBindings().get("left");
                int rightKey = (int) ((Player) entity).getKeyBindings().get("right");

                if(event.getKeyCode() == leftKey) {
                    if(!layer.collides(entity.getModel().get2DArray(), entity.getX() - 1, entity.getY(), map)) {
                        entity.setX(entity.getX() - 1);
                        if(entity instanceof Player) {
                            if (((Player) entity).hasAnimation(this.animationName)) {
                                entity.setAnimationPlayState(this.animationName, true);
                            }
                        }
                    }
                } else if(event.getKeyCode() == rightKey) {
                    if(!layer.collides(entity.getModel().get2DArray(), entity.getX() + 1, entity.getY(), map)) {
                        entity.setX(entity.getX() + 1);
                        if(entity instanceof Player) {
                            if (((Player) entity).hasAnimation(this.animationName)) {
                                entity.setAnimationPlayState(this.animationName, true);
                            }
                        }
                    }
                }
            }
        }
    }
}
