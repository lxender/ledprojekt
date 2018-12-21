package app.ledprojekt;

import ledControl.BoardController;

public interface Drawable {
    void draw(BoardController controller);
    BoundingBox getBoundingBox();
}
