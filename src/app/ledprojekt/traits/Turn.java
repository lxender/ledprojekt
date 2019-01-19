package app.ledprojekt.traits;

import app.ledprojekt.layers.CollisionLayer;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class Turn extends Trait {
    @Override
    public void update(Player entity) {
        KeyEvent event = entity.getKeyEventRef();

        if (event != null) {
            if (event.getID() == KeyEvent.KEY_RELEASED) {
                CollisionLayer layer = entity.getCollisionLayerRef();

                int leftKey = (int) ((Player) entity).getKeyBindings().get("left");
                int rightKey = (int) ((Player) entity).getKeyBindings().get("right");

                if (event.getKeyCode() == leftKey) {
                    if (!entity.isFlipped() && !layer.isObstructed(entity, entity.getX(), entity.getY())) {
                        entity.flip();
                    }
                } else if (event.getKeyCode() == rightKey) {
                    if (entity.isFlipped() && !layer.isObstructed(entity, entity.getX(), entity.getY())) {
                        entity.flip();
                    }
                }
            }
        }
    }
}
