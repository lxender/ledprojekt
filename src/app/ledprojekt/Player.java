package app.ledprojekt;

import app.ledprojekt.traits.Trait;
import ledControl.BoardController;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player implements Entity, Drawable {
    private int x;
    private int y;

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

    private List<Trait> traits = new ArrayList<>();

    Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) { this.x = x; }
    public int getX() { return this.x; }
    public void setY(int y) { this.y = y; }
    public int getY() { return this.y; }

    void addWeapon(Model weapon, int x, int y) {
        this.characterModel.appendAttachment(weapon, x, y);
        this.attachment = weapon;
    }

    public void addTrait(Trait trait) {
        this.traits.add(trait);
    }

    public void update(KeyEvent event) {
        for (Trait trait : this.traits) {
            trait.update(this, event);
        }
    }

    public void draw(BoardController controller) {
        this.characterModel.draw(controller, this.x, this.y);
    }
}