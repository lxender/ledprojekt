package app.ledprojekt;

import ledControl.BoardController;

import java.awt.event.KeyEvent;

public abstract class Entity {
    public abstract void setX(int x);
    public abstract int getX();
    public abstract void setY(int x);
    public abstract int getY();
    public abstract void addTrait(Trait trait);
    public abstract void update(KeyEvent event);
    public abstract void draw(BoardController controller);
}
