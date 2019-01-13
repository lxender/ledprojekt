package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class Turn extends Trait {
    @Override
    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();

        if (event != null) {
            if (event.getID() == KeyEvent.KEY_RELEASED) {
                CollisionLayer layer = entity.getCollisionLayerRef();

                int leftKey = (int) ((Player) entity).getKeyBindings().get("left");
                int rightKey = (int) ((Player) entity).getKeyBindings().get("right");

                if (event.getKeyCode() == leftKey) {
                    if (entity instanceof Player) {
                        if (!((Player) entity).isFlipped() && !layer.isObstructed(entity, entity.getX(), entity.getY())) {
                            ((Player) entity).flip();
                        }
                    }
                } else if (event.getKeyCode() == rightKey) {
                    if (entity instanceof Player) {
                        if (((Player) entity).isFlipped() && !layer.isObstructed(entity, entity.getX(), entity.getY())) {
                            ((Player) entity).flip();
                        }
                    }
                }
            }
        }
    }
}
