package app.ledprojekt;

import app.ledprojekt.layers.Collidable;
import app.ledprojekt.layers.CollisionLayer;
import app.ledprojekt.layers.Drawable;
import ledControl.BoardController;

public class Geometry implements Drawable, Collidable {
    private Model model;
    private int x;
    private int y;

    private BoundingBox bounds;
    private CollisionLayer layer;
    private boolean isSolid = true;

    private String name;

    public Geometry(int x, int y, int width, int height, int[] color) {
        int[][][] planeModelArray = new int[height][width][4];
        for (int i = 0; i < planeModelArray.length; i++) {
            for (int j = 0; j < planeModelArray[i].length; j++) {
                planeModelArray[i][j] = color;
            }
        }

        this.model = new Model(planeModelArray);
        this.x = x;
        this.y = y;

        this.updateBoundingBox();
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public void setSolid(boolean setting) {
        this.isSolid = setting;
    }
    public boolean isSolid() {
        return this.isSolid;
    }

    private void updateBoundingBox() {
        this.bounds = new BoundingBox(this.x, this.y, this.model.getWidth(), this.model.getHeight());
    }
    public BoundingBox getBoundingBox() {
        return this.bounds;
    }

    public Model getModel() {
        return this.model;
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
