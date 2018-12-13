package app.ledprojekt;

import app.ledprojekt.traits.Trait;
import ledControl.BoardController;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    public int x;
    public int y;

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

    List<Trait> traits = new ArrayList<>();

    Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) { this.x = x; }
    public int getX() { return this.x; }
    public void setY(int y) { this.y = y; }
    public int getY() { return this.y; }

    public void addWeapon(Model weapon, int x, int y) {
        this.characterModel.appendAttachment(weapon, x, y);
        this.attachment = weapon;
    }

    public boolean hasWeapon() {
        return (this.attachment != null);
    }

    public void addTrait(Trait trait) {
        this.traits.add(trait);
    }

    private Trait getTrait(String name) {
        for (int i = 0; i < this.traits.size(); i++) {
            if(this.traits.get(i).getName().equals(name)) {
                return this.traits.get(i);
            }
        }
        return null;
    }

    public void update(KeyEvent event) {
        this.getTrait("Go").update(this, event);
    }

    public void draw(BoardController controller) {
        this.characterModel.draw(controller, this.x, this.y);
    }
}