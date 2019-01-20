package app.ledprojekt.traits;

import app.ledprojekt.input.CustomKeyEvent;
import app.ledprojekt.layers.CollisionLayer;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class Go extends Trait {
    public Go() {}

    String animationName;
    public Go(String animationName) { this.animationName = animationName; }

    public void update(Player entity) {
        CustomKeyEvent event = entity.getKeyEventRef();
        CollisionLayer layer = entity.getCollisionLayerRef();

        if(event != null) {
            if(event.getID() == KeyEvent.KEY_RELEASED) {
                int[][] map = layer.createRelativeCollisionMatrix(entity);

                int leftKey = (int) entity.getKeyBindings().get("left");
                int rightKey = (int) entity.getKeyBindings().get("right");

                if(event.getKeyCode() == leftKey) {
                    if(!layer.collides(entity.getModel().get2DArray(), entity.getX() - 1, entity.getY(), map)) {
                        entity.setX(entity.getX() - 1);
                        if (entity.hasAnimation(this.animationName)) {
                            entity.setAnimationPlayState(this.animationName, true);
                        }
                    }
                } else if(event.getKeyCode() == rightKey) {
                    if(!layer.collides(entity.getModel().get2DArray(), entity.getX() + 1, entity.getY(), map)) {
                        entity.setX(entity.getX() + 1);
                        if (entity.hasAnimation(this.animationName)) {
                            entity.setAnimationPlayState(this.animationName, true);
                        }
                    }
                }
            }
        }
    }
}
