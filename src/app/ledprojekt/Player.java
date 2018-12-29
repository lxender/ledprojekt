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

    Model characterModel = new Model(new int[][][]{
            {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
            {{127, 0, 0, 1}, {0, 0, 0, 1}, {127, 0 ,0, 1}},
            {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
            {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 , 0, 1}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
            {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
            {{127, 0, 0, 1}, {0, 0, 0, 0}, {127, 0 ,0, 1}},
    });
    private Model attachment;

    BoundingBox bounds;

    private List<Trait> traits = new ArrayList<>();
    private KeyEvent keyEvent;
    private CollisionLayer layer;

    private float dt;

    private HashMap<String, ArrayList<Model>> animations = new HashMap<>();
    private HashMap<String, Boolean> animationPlayStates = new HashMap<>();
    private ArrayList<Model> currentlyPlayingAnimation = new ArrayList<>();

    Player(int x, int y) {
        this.x = x;
        this.y = y;

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
        this.bounds = new BoundingBox((int) this.x, (int) this.y, this.characterModel.getWidth(), this.characterModel.getHeight());
    }

    void addWeapon(Model weapon, int x, int y) {
        this.characterModel.appendAttachment(weapon, x, y);
        this.attachment = weapon;

        this.updateBoundingBox();
    }
    public void addTraits(Trait... traits) {
        this.traits.addAll(Arrays.asList(traits));
    }
    void addAnimation(String name, Model... models) {
        System.out.println("Name: " + name + ", array: " + Arrays.toString(models));
        ArrayList<Model> list = new ArrayList<>(Arrays.asList(models));
        this.animations.put(name.toLowerCase(), list);
    }
    void addToAnimation(String name, Model model) {
        ArrayList<Model> newList = this.animations.get(name);
        newList.add(model);
        this.animations.replace(name, newList);
    }
    public void setAnimationPlayState(String name, boolean value) {
        this.animationPlayStates.put(name.toLowerCase(), value);
    }
    private void findAnimation() {
        if (this.animationPlayStates.containsValue(true)) {
            BiConsumer<String, Boolean> findAnimConsumer = (key, value) -> {
                if(value) {
                    this.currentlyPlayingAnimation = this.animations.get(key.toLowerCase());
                }
            };
            this.animationPlayStates.forEach(findAnimConsumer);
        }
    }
    private void playAnimation() {
        if (!this.animationPlayStates.isEmpty()) {
            this.findAnimation();

            if (!currentlyPlayingAnimation.isEmpty()) {
                this.characterModel = this.currentlyPlayingAnimation.remove(0);
                Model.print2DArray(this.characterModel.get2DArray());
            } else {
                this.animationPlayStates.clear();
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

    public void updateDelta(float dt) {
        this.dt = dt;
    }
    public float getDelta() {
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