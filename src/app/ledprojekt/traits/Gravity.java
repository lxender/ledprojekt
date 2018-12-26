package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;

public class Gravity extends Trait {

    private double strength = 0.4;

    @Override
    public void update(Entity entity) {
        CollisionLayer layer = entity.getCollisionLayerRef();

        if (!layer.isObstructed(entity, entity.getX(), (int) Math.floor(entity.getYAsDouble() + this.strength))) {
            entity.setYAsDouble(entity.getYAsDouble() + this.strength);
        }
    }
}
