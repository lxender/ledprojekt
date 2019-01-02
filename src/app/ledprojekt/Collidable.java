package app.ledprojekt;

import ledControl.BoardController;

public interface Collidable {
    void updateCollisionLayerRef(CollisionLayer layer);
    CollisionLayer getCollisionLayerRef();
    void draw(BoardController controller);
    void update();
    boolean isSolid();
    BoundingBox getBoundingBox();
}
