package app.ledprojekt.traits;

import app.ledprojekt.CollisionLayer;
import app.ledprojekt.Entity;
import app.ledprojekt.Model;
import app.ledprojekt.Player;

import java.util.Arrays;

public class Gravity extends Trait {

    private double strength = 8.0;

    @Override
    public void update(Entity entity) {
        CollisionLayer layer = entity.getCollisionLayerRef();

        int[][] map = layer.createRelativeCollisionMatrix(entity);

        // Wenn gesprungen wird, wende keine Schwerkraft an (damit sie sich nicht mit der Sprungkraft messen muss)
        if (entity instanceof Player) {
            if(((Player) entity).isJumping) {
                return;
            }
        }

        // Wenn unter dem Entity nichts ist, wende Schwerkraft an
        if(!layer.collides(entity.getModel().get2DArray(), entity.getX(), entity.getY() + 1, map)) {
            entity.setVelocityY(this.strength);
        } else if(entity.getVelocityY() == this.strength) {
            entity.setVelocityY(0.0);

            // Runde Y (um Artefakte zu vermeiden)
            entity.setYAsDouble(entity.getY());
        }

//        double newYPosition = entity.getYAsDouble() + this.strength * dt;
//        if (!layer.isObstructed(entity, entity.getX(), (int) Math.floor(newYPosition))) {
//            entity.setYAsDouble(newYPosition);
//        }
    }
}
