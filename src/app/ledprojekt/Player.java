package app.ledprojekt;

import app.ledprojekt.traits.Trait;
import ledControl.BoardController;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player implements Entity, Drawable, Collidable {
    private double x;
    private double y;

    Model characterModel = new Model(new int[][][]{
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0, 0, 1}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 , 0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0, 0, 0}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
    });
    private Model attachment;

    BoundingBox bounds;

    private List<Trait> traits = new ArrayList<>();
    private KeyEvent keyEvent;
    private CollisionLayer layer;

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

    private void updateBoundingBox() {
        // System.out.println(String.format("Updating bounds, x: %d, y: %d", this.x, this.y));
        this.bounds = new BoundingBox((int) this.x, (int) this.y, this.characterModel.getWidth(), this.characterModel.getHeight());
    }

    public BoundingBox getBoundingBox() {
        return this.bounds;
    }

    void addWeapon(Model weapon, int x, int y) {
        this.characterModel.appendAttachment(weapon, x, y);
        this.attachment = weapon;

        this.updateBoundingBox();
    }

    public void addTrait(Trait trait) {
        this.traits.add(trait);
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

    public void update() {
        for (Trait trait : this.traits) {
            trait.update(this);
        }
    }

    public void draw(BoardController controller) {
        this.characterModel.draw(controller, (int) this.x, (int) this.y);
    }
}