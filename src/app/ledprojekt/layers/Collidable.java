package app.ledprojekt.layers;

import app.ledprojekt.BoundingBox;
import app.ledprojekt.Model;
import ledControl.BoardController;

public interface Collidable extends Drawable {
    void updateCollisionLayerRef(CollisionLayer layer);
    CollisionLayer getCollisionLayerRef();
    Model getModel();
    int getX();
    int getY();
    void draw(BoardController controller);
    void update();
    boolean isSolid();
    BoundingBox getBoundingBox();
}
