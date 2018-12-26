package app.ledprojekt;

import ledControl.BoardController;

public class Geometry implements Drawable, Collidable {
    private Model model;
    private int x;
    private int y;

    private BoundingBox bounds;
    private CollisionLayer layer;

    public static Geometry generate(int x, int y, int width, int height, int[] color) {
        int[][][] planeModelArray = new int[height][width][4];
        for (int i = 0; i < planeModelArray.length; i++) {
            for (int j = 0; j < planeModelArray[i].length; j++) {
                planeModelArray[i][j] = color;
            }
        }
        return new Geometry(new Model(planeModelArray), x, y);
    }

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

    public void updateCollisionLayerRef(CollisionLayer layer) {
        this.layer = layer;
    }
    public CollisionLayer getCollisionLayerRef() {
        return this.layer;
    }

    public void update() {

    }

    public void draw(BoardController controller) {
        this.model.draw(controller, x, y);
    }
}
