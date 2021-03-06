package app.ledprojekt.traits;

import app.ledprojekt.Main;
import app.ledprojekt.input.CustomKeyEvent;
import app.ledprojekt.layers.CollisionLayer;
import app.ledprojekt.Player;

import java.awt.event.KeyEvent;

public class Jump extends Trait {

    private double strength = -50;

    private int totalMaxHeight = 3;
    private int currentJumpMaxHeight = 0;
    private int startY = -1;

    @Override
    public void update(Player entity) {
        CustomKeyEvent event = entity.getKeyEventRef();

        // Wenn der Player springt und die Maximalhöhe erreicht ist (also wenn startY - momentanesY = der Maximalhöhe ist)
        // -> ist der Sprung fertig, also wird alles zurückgesetzt
        if (entity.isJumping && Math.abs(this.startY - entity.getY()) >= this.currentJumpMaxHeight) {
            entity.isJumping = false;
            this.startY = -1;
        }

        if (event != null) {
            int jumpKey = (int) entity.getKeyBindings().get("jump");

            if(event.getID() == KeyEvent.KEY_RELEASED && event.getKeyCode() == jumpKey) {

                CollisionLayer layer = entity.getCollisionLayerRef();
                int[][] map = layer.createRelativeCollisionMatrix(entity);

                if(layer.collides(entity.getModel().get2DArray(), entity.getX(), entity.getY() + 1, map)) {
                    // Finde heraus wie hoch maximal gesprungen werden kann
                    for (int i = this.totalMaxHeight; i >= 0; i--) {
                        if(!layer.isObstructed(entity, entity.getX(), entity.getY() - i)) {
                            this.currentJumpMaxHeight = i;
                            break;
                        }
                    }
                    //Wenn nicht gesprungen werden kann, spring nicht
                    if(this.currentJumpMaxHeight == 0) return;

                    // Schalte Gravity aus, damit gesprungen werden kann
                    entity.isJumping = true;

                    // Setz startY, wenn es noch nicht gesetzt wurde
                    if (this.startY == -1) {
                        this.startY = entity.getY();
                    }

                    entity.setVelocityY(this.strength);
                }
            }
        }
    }
}
