package app.ledprojekt;

import ledControl.BoardController;

public class Geometry implements Drawable {
    private Model model;
    private int x;
    private int y;

    private BoundingBox bounds;

    Geometry(Model model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;

        this.updateBoundingBox();
    }

    private void updateBoundingBox() {
        this.bounds = new BoundingBox(this.x, this.y, this.model.getWidth(), this.model.getHeight());
    }

    public BoundingBox getBoundingBox() {
        return this.bounds;
    }

    public void draw(BoardController controller) {
        this.model.draw(controller, x, y);
    }
}
