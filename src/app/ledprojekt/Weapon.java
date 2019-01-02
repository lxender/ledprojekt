package app.ledprojekt;

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

    private long timer = 0;
    private HashMap<String, AnimationWrapper> animations = new HashMap<>();
    private HashMap<String, Boolean> animationPlayStates = new HashMap<>();
    private ArrayList<Model> currentlyPlayingAnimation = new ArrayList<>();

    public Weapon(Model model, int x, int y) {
        this.model = model;
        this.offsetX = x;
        this.offsetY = y;

        this.updateBoundingBox();
    }

    public int getX() {
        return this.x;
    }
    public void setX(int x) { this.x = x; }
    public int getY() {
        return this.y;
    }
    public void setY(int y) { this.y = y; }

    public Model getModel() {
        return this.model;
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
                    this.model = Animation.getFrame(playingAnimationName, this.currentlyPlayingAnimation, anim, animationPlayStates);
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
        return true;
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
        this.x = this.player.getX() + this.offsetX;
        this.y = this.player.getY() + this.offsetY;

        if (layer.isObstructed(this, this.x, this.y)) {
            System.out.println("Weapon intersected!");
        }
    }
    public void draw(BoardController controller) {
        this.playAnimation();
        this.model.draw(controller, this.x, this.y);
    }
}
