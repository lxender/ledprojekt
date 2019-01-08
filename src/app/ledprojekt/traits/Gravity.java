package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Model;

import java.util.Arrays;

public class Gravity extends Trait {

    private double strength = 8.2;

    @Override
    public void update(Entity entity) {
        CollisionLayer layer = entity.getCollisionLayerRef();
        double dt = entity.getDelta();

        int[][] map = layer.createRelativeCollisionMatrix(entity);

        entity.setYAsDouble(entity.getYAsDouble() + this.strength * dt);
        if (layer.collides(entity, map)) {
            entity.setYAsDouble(entity.getYAsDouble() - this.strength * dt);
        }

//        double newYPosition = entity.getYAsDouble() + this.strength * dt;
//        if (!layer.isObstructed(entity, entity.getX(), (int) Math.floor(newYPosition))) {
//            entity.setYAsDouble(newYPosition);
//        }
    }
}
