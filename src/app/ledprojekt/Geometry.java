package app.ledprojekt;

import ledControl.BoardController;

public class Geometry {
    Model model;
    int x;
    int y;

    public Geometry(Model model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;
    }

    public void draw(BoardController controller) {
        this.model.draw(controller, x, y);
    }
}
