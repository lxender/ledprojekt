package app.ledprojekt.screens;

import ledControl.BoardController;

public interface Screen {
    void init(BoardController controller);
    void update(double delta);
    void draw(BoardController controller);
}
