package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;

public class Gravity extends Trait {
    public void update(Entity entity) {
        // System.out.println(this.entityY);

        CollisionLayer layer = entity.getCollisionLayerRef();

        if (!layer.isObstructedY(entity, (int) Math.floor(entity.getYAsDouble() + 0.2))) {
            entity.setYAsDouble(entity.getYAsDouble() + 0.2);
        }
    }
}
