package app.ledprojekt;

import ledControl.BoardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollisionLayer {
    BoardController controller;
    List<Collidable> objectsInLayer = new ArrayList<>();

    CollisionLayer(BoardController controller, Collidable... objects) {
        this.controller = controller;
        this.objectsInLayer.addAll(Arrays.asList(objects));
    }

    public boolean isObstructedX(Collidable entityRef, int x) {
        for(Collidable obj : this.objectsInLayer) {
            if(obj == entityRef) continue;

            BoundingBox entityRefBound = entityRef.getBoundingBox();
            BoundingBox bound = obj.getBoundingBox();
            // System.out.println(entityRef.getBoundingBox());

            if(x < bound.x + bound.width &&
                    x + entityRefBound.width > bound.x &&
                    entityRefBound.y < bound.y + bound.height &&
                    entityRefBound.height + entityRefBound.y > bound.y) {
                return true;
            }
        }

        return false;
    }

    public boolean isObstructedY(Collidable entityRef, int y) {
        for(Collidable obj : this.objectsInLayer) {
            if(obj == entityRef) continue;

            BoundingBox entityRefBound = entityRef.getBoundingBox();
            BoundingBox bound = obj.getBoundingBox();

            if(entityRefBound.x < bound.x + bound.width &&
                    entityRefBound.x + entityRefBound.width > bound.x &&
                    y < bound.y + bound.height &&
                    entityRefBound.height + y > bound.y) {
                return true;
            }
        }

        return false;
    }

    void update() {
        for (Collidable obj : this.objectsInLayer) {
            obj.updateCollisionLayerRef(this);
            obj.update();
        }
    }

    void draw() {
        for(Collidable obj : this.objectsInLayer) {
            obj.draw(this.controller);
        }
    }
}
