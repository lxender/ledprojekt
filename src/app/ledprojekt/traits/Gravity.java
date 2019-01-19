package app.ledprojekt.traits;

import app.ledprojekt.layers.CollisionLayer;
import app.ledprojekt.Player;

public class Gravity extends Trait {

    private double strength = 8.0;

    @Override
    public void update(Player entity) {
        CollisionLayer layer = entity.getCollisionLayerRef();

        int[][] map = layer.createRelativeCollisionMatrix(entity);

        // Wenn gesprungen wird, wende keine Schwerkraft an (damit sie sich nicht mit der Sprungkraft messen muss)
        if(entity.isJumping) {
            return;
        }

        // Wenn unter dem Entity nichts ist, wende Schwerkraft an
        if(!layer.collides(entity.getModel().get2DArray(), entity.getX(), entity.getY() + 1, map)) {
            entity.setVelocityY(this.strength);
        } else if(entity.getVelocityY() == this.strength) {
            entity.setVelocityY(0.0);

            // Runde Y (um Artefakte zu vermeiden)
            entity.setYAsDouble(entity.getY());
        }
    }
}
