package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;

public class Gravity extends Trait {

    private double strength = 8.2;

    @Override
    public void update(Entity entity) {
        CollisionLayer layer = entity.getCollisionLayerRef();
        double dt = entity.getDelta();

        double newYPosition = entity.getYAsDouble() + this.strength * dt;

        if (!layer.isObstructed(entity, entity.getX(), (int) Math.floor(newYPosition))) {
            entity.setYAsDouble(newYPosition);
        }
    }
}
