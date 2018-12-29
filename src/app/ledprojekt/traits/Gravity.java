package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;

public class Gravity extends Trait {

    private double strength = 0.15;

    @Override
    public void update(Entity entity) {
        CollisionLayer layer = entity.getCollisionLayerRef();

        double newYPosition = entity.getYAsDouble() + this.strength;

        if (!layer.isObstructed(entity, entity.getX(), (int) Math.floor(newYPosition))) {
            entity.setYAsDouble(newYPosition);
        }
    }
}
