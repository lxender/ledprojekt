package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;

import java.awt.event.KeyEvent;

public class Jump extends Trait {

    private int height = 2;

    @Override
    public void update(Entity entity) {
        KeyEvent event = entity.getKeyEventRef();

        if (event != null) {
            if(event.getID() == KeyEvent.KEY_RELEASED && event.getKeyCode() == KeyEvent.VK_SPACE) {

                CollisionLayer layer = entity.getCollisionLayerRef();
                boolean isOnGround = layer.isObstructed(entity, entity.getX(), entity.getY() + 1);

                // Ist 2 drüber nicht beleget? && Ist 1 drunter belegt?
                for (int i = this.height; i >= 0; i--) {
                    if(!layer.isObstructed(entity, entity.getX(), entity.getY() - i) && isOnGround) {
                        entity.setY(entity.getY() - i);
                        break;
                    }
                }
            }
        }
    }
}
