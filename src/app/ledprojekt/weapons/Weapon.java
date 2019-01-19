package app.ledprojekt.weapons;

import app.ledprojekt.*;
import app.ledprojekt.entities.Goku;
import ledControl.BoardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Weapon implements Collidable {

    private CollisionLayer layer;
    private Model model;
    private BoundingBox bounds;

    private int x = 0;
    private int y = 0;
    private int offsetX = 0;
    private int offsetY = 0;

    private int initialWidth = -1;

    private Player player;

    private int damage = 5;
    private long collisionTimer = System.currentTimeMillis();

    private AnimationManager animManager = new AnimationManager();

    public Weapon(int x, int y, Player player) {
        this.player = player;
        this.offsetX = x;
        this.offsetY = y;
    }

    public int getOffsetX() {
        return this.offsetX;
    }
    public int getOffsetY() {
        return this.offsetY;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }

    public Model getModel() {
        return this.model;
    }
    public void setModel(Model model) {
        if (initialWidth == -1) {
            initialWidth = model.getWidth();
        }
        if (this.player != null) {
            if (this.player.isFlipped()) {
                this.model = model;
                if (!this.model.isFlipped()) this.model.flip();
                this.updateBoundingBox();

                return;
            }
        }

        this.model = model;
        this.updateBoundingBox();
    }

    public void checkFlip() {
        if (this.model == null) return;

        if (this.player != null) {
            if (this.model.isFlipped() != this.player.isFlipped()) {
                this.model.flip();
            }
        }
    }

    public void addAnimation(String name, int durationInMillis, Model... models) {
        this.animManager.addAnimation(name, durationInMillis, models);
    }
    public void setAnimationPlayState(String name, boolean value) {
        this.animManager.setAnimationPlayState(name, value);
    }
    private void playAnimation() {
        Model frame = this.animManager.playAnimation();
        if (frame != null) {
            this.setModel(frame);
        }
    }

    public void updateCollisionLayerRef(CollisionLayer layer) {
        this.layer = layer;
    }
    public CollisionLayer getCollisionLayerRef() {
        return this.layer;
    }

    public boolean isSolid() {
        // true würde (momentan?) nichts machen
        return false;
    }

    public BoundingBox getBoundingBox() {
        return this.bounds;
    }
    private void updateBoundingBox() {
        this.bounds = new BoundingBox(this.x, this.y, this.model.getWidth(), this.model.getHeight());
    }

    public void updatePlayerRef(Player player) {
        this.player = player;
    }

    private void checkCollision() {
        if (this.layer == null) return;
        int[][] map = this.layer.createRelativeCollisionMatrix(this);
        if (this.layer.collides(this.player.getModel().get2DArray(), this.x, this.y, map)) {
            Collidable[] intersectionObjects = null;

            if (this.player.isFlipped()) {
                intersectionObjects = this.layer.getObjectsAt(this.player,this.x - this.model.getWidth(), this.y, this.model.getWidth(), this.model.getHeight());
            } else {
                intersectionObjects = this.layer.getObjectsAt(this.player, this.x, this.y, this.model.getWidth(), this.model.getHeight());
            }

            if (intersectionObjects.length != 0) {
                if (this.player == null) return;

                int damageCooldown = 1000;
                if (System.currentTimeMillis() - this.collisionTimer > damageCooldown) {
                    System.out.println("Collided with: " + Arrays.toString(intersectionObjects));

                    for (Collidable obj : intersectionObjects) {
                        this.doDamageOn(obj);
                    }

                    this.collisionTimer = System.currentTimeMillis();
                }
            }
        }
    }
    public void doDamageOn(Collidable obj) {
        if (obj instanceof Player) {
            if(((Player) obj).isKillable()) {
                ((Player) obj).decreaseHealth(this.damage);
                System.out.println(((Player) obj).getHealth());
            }
        }
    }

    public void update() {
        if (this.player != null) {
            this.checkFlip();
            if(this.player.isFlipped()) {
//                this.x = this.player.getX() + this.model.getWidth();
                int ofx = (this.offsetX < 0) ? -this.offsetX : -this.offsetX;
                this.x = this.player.getX() + ofx - this.initialWidth;
                this.y = this.player.getY() + this.offsetY;
            } else {
                this.x = this.player.getX() + player.getBoundingBox().width + this.offsetX;
                this.y = this.player.getY() + this.offsetY;
            }
            this.updateBoundingBox();
        }

        this.checkCollision();
    }
    public void draw(BoardController controller) {
        if (this.model == null) {
            System.out.println("Model has not been defined yet.");
            return;
        }

        this.playAnimation();
        this.checkFlip();
        if (this.model.isFlipped()) {
            this.model.draw(controller, this.x - this.model.getWidth(), this.y);
        } else {
            this.model.draw(controller, this.x, this.y);
        }
    }
}
