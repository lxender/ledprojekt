package app.ledprojekt;

import app.ledprojekt.traits.Trait;
import ledControl.BoardController;

import java.awt.event.KeyEvent;

public interface Entity extends Collidable {
    void setX(int x);
    int getX();
    void setY(int x);
    int getY();

    void setXAsDouble(double x);
    double getXAsDouble();
    void setYAsDouble(double x);
    double getYAsDouble();

    void addTrait(Trait trait);
    void updateKeyEventRef(KeyEvent event);
    KeyEvent getKeyEventRef();
    void draw(BoardController controller);
}
