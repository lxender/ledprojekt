package app.ledprojekt;

import app.ledprojekt.traits.Trait;
import ledControl.BoardController;

import java.awt.event.KeyEvent;

public interface Entity {
    void setX(int x);
    int getX();
    void setY(int x);
    int getY();
    void addTrait(Trait trait);
    void update(KeyEvent event);
    void draw(BoardController controller);
}
