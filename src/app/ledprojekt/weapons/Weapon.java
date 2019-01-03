package app.ledprojekt.weapons;

import app.ledprojekt.*;
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

    private Player player;

    private long collisionTimer = 0;
    private long timer = 0;

    private HashMap<String, AnimationWrapper> animations = new HashMap<>();
    private HashMap<String, Boolean> animationPlayStates = new HashMap<>();
    private ArrayList<Model> currentlyPlayingAnimation = new ArrayList<>();

    public Weapon(Model model, int x, int y, Player player) {
        this.player = player;
        this.setModel(model);
        this.offsetX = x;
        this.offsetY = y;

        this.updateBoundingBox();
    }

    public int getOffsetX() {
        return this.offsetX;
    }
    public int getOffsetY() {
        return this.offsetY;
    }

    public Model getModel() {
        return this.model;
    }
    private void setModel(Model model) {
        if (this.player != null) {
            if (this.player.isFlipped()) {
                this.model = model;
                if (!this.model.isFlipped()) this.model.flip();

                return;
            }
        }

        this.model = model;
    }

    public void addAnimation(String name, int durationInMillis, Model... models) {
        //System.out.println("Name: " + name + ", array: " + Arrays.toString(models));
        ArrayList<Model> list = new ArrayList<>(Arrays.asList(models));
        AnimationWrapper wrapper = new AnimationWrapper(durationInMillis, list);
        this.animations.put(name.toLowerCase(), wrapper);
    }
    public void setAnimationPlayState(String name, boolean value) {
        this.animationPlayStates.put(name.toLowerCase(), value);
        this.timer = System.currentTimeMillis();
    }
    private void playAnimation() {
        if (!this.animationPlayStates.isEmpty()) {
            String playingAnimationName = Animation.findAnimation(this.animationPlayStates);

            if (playingAnimationName != null) {
                AnimationWrapper anim = this.animations.get(playingAnimationName);
                if (System.currentTimeMillis() - this.timer > anim.msPerFrame) {
                    this.setModel(Animation.getFrame(playingAnimationName, this.currentlyPlayingAnimation, anim, this.animationPlayStates));
                    this.updateBoundingBox();
                    this.timer += anim.msPerFrame;
                }
            }
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

    public void update() {
        if (this.player != null) {
            if(!this.player.isFlipped()) {
                this.x = this.player.getX() + this.offsetX;
                this.y = this.player.getY() + this.offsetY;
            } else {
                this.x = player.getX() - (player.getBoundingBox().width - this.offsetX);
                this.y = player.getY() + this.offsetY;
            }

            this.updateBoundingBox();
        }

        if (layer.isObstructed(this, this.x, this.y)) {
            Collidable intersectionObject = null;

            if (this.player.isFlipped()) {
                intersectionObject = layer.getObjectAt(this.x - this.model.getWidth(), this.y, this.model.getWidth(), this.model.getHeight());
            } else {
                intersectionObject = layer.getObjectAt(this.x, this.y, this.model.getWidth(), this.model.getHeight());
            }

            if (intersectionObject != null) {
                if (this.player != null && this.player == intersectionObject) return;

                if (this.collisionTimer == 0) {
                    this.collisionTimer = System.currentTimeMillis();
                }
                int damageCooldown = 1000;
                if (System.currentTimeMillis() - this.collisionTimer > damageCooldown) {
                    System.out.println("Collided with: " + intersectionObject);

                    this.collisionTimer += damageCooldown;
                }
            }
        }
    }
    public void draw(BoardController controller) {
        this.playAnimation();
        if (this.model.isFlipped()) {
            this.model.draw(controller, this.x - this.model.getWidth(), this.y);
        } else {
            this.model.draw(controller, this.x, this.y);
        }
    }
}
