package app.ledprojekt;

import app.ledprojekt.traits.Trait;
import ledControl.BoardController;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class Player implements Entity, Drawable {
    private double x;
    private double y;

    private int velocityX;
    private int velocityY;

    public Model characterModel;
    private int baseModelWidth;
    private int baseModelHeight;
    private boolean modelIsFlipped = false;
    private Model attachment;

    public BoundingBox bounds;

    private List<Trait> traits = new ArrayList<>();
    private KeyEvent keyEvent;
    private CollisionLayer layer;

    private double dt;

    private long timer = 0;
    private HashMap<String, AnimationWrapper> animations = new HashMap<>();
    private HashMap<String, Boolean> animationPlayStates = new HashMap<>();
    private ArrayList<Model> currentlyPlayingAnimation = new ArrayList<>();

    public Player(int x, int y, Model model) {
        this.x = x;
        this.y = y;

        this.characterModel = model;
        this.baseModelWidth = this.characterModel.getWidth();
        this.baseModelHeight = this.characterModel.getHeight();

        this.updateBoundingBox();
    }

    public void setX(int x) {
        this.x = x;
        this.updateBoundingBox();
    }
    public int getX() { return (int) this.x; }
    public void setXAsDouble(double x) {
        this.x = x;
        this.updateBoundingBox();
    }
    public double getXAsDouble() { return this.x; }

    public void setY(int y) {
        this.y = y;
        this.updateBoundingBox();
    }
    public int getY() { return (int) this.y; }
    public void setYAsDouble(double y) {
        this.y = y;
        this.updateBoundingBox();
    }
    public double getYAsDouble() { return this.y; }

    public BoundingBox getBoundingBox() {
        return this.bounds;
    }
    private void updateBoundingBox() {
        // System.out.println(String.format("Updating bounds, x: %d, y: %d", this.x, this.y));
        this.bounds = new BoundingBox((int) this.x, (int) this.y, this.baseModelWidth, this.baseModelHeight);
    }

    public void addTraits(Trait... traits) {
        this.traits.addAll(Arrays.asList(traits));
    }

    public void addWeapon(Model weapon, int x, int y) {
        this.characterModel.appendAttachment(weapon, x, y);
        this.attachment = weapon;

        this.updateBoundingBox();
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
    private String findAnimation() {
        if (this.animationPlayStates.containsValue(true)) {
            for(String k : this.animationPlayStates.keySet()) {
                if(this.animationPlayStates.get(k.toLowerCase())) {
                    return k;
                }
            }
        }
        return null;
    }
    private void playAnimation() {
        if (!this.animationPlayStates.isEmpty()) {
            String playingAnimationName = this.findAnimation();

            if (playingAnimationName != null) {
                if (this.currentlyPlayingAnimation.isEmpty()) {
                    this.currentlyPlayingAnimation.addAll(this.animations.get(playingAnimationName).list);
                }

                if (System.currentTimeMillis() - this.timer > this.animations.get(playingAnimationName).msPerFrame) {
                    this.characterModel = this.currentlyPlayingAnimation.remove(0);
                    // Model.print2DArray(this.characterModel.get2DArray());
                    this.timer += this.animations.get(playingAnimationName).msPerFrame;
                }

                if(this.currentlyPlayingAnimation.size() == 0) {
                    this.currentlyPlayingAnimation.clear();
                    this.animationPlayStates.replace(playingAnimationName, false);
                }
            }

        }
    }

    public void updateKeyEventRef(KeyEvent event) {
        this.keyEvent = event;
    }
    public KeyEvent getKeyEventRef() {
        return this.keyEvent;
    }

    public void updateCollisionLayerRef(CollisionLayer layer) {
        this.layer = layer;
    }
    public CollisionLayer getCollisionLayerRef() {
        return this.layer;
    }

    public void updateDelta(double dt) {
        this.dt = dt;
    }
    public double getDelta() {
        return this.dt;
    }

    public void update() {
        for (Trait trait : this.traits) {
            trait.update(this);
        }
    }
    public void draw(BoardController controller) {
        this.playAnimation();
        this.characterModel.draw(controller, (int) this.x, (int) this.y);
    }
}