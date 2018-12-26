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
            if(event.getID() == KeyEvent.KEY_RELEASED && event.getKeyCode() == KeyEvent.VK_UP) {

                CollisionLayer layer = entity.getCollisionLayerRef();

                // Ist 2 drüber nicht beleget? && Ist 1 drunter belegt?
                if(!layer.isObstructed(entity, entity.getX(), entity.getY() - this.height) &&
                        layer.isObstructed(entity, entity.getX(), entity.getY() + 1)) {
                    entity.setY(entity.getY() - this.height);
                }
            }
        }
    }
}
