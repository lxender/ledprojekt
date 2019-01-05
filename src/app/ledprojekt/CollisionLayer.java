package app.ledprojekt;

import ledControl.BoardController;

import java.util.*;

public class CollisionLayer{
    private BoardController controller;

    private List<Collidable> objectsInLayer = new ArrayList<>();
    private List<Collidable> objectsToRemove = new ArrayList<>();

    CollisionLayer(BoardController controller, Collidable... objects) {
        this.controller = controller;
        this.objectsInLayer.addAll(Arrays.asList(objects));
    }

    public void removeObjectInLayer(Collidable obj) {
        objectsToRemove.add(obj);
    }

    public Collidable getObjectAt(int x, int y, int width, int height) {
        for (Collidable obj : this.objectsInLayer) {
            BoundingBox bound = obj.getBoundingBox();

            if (x < bound.x + bound.width &&
                    x + width > bound.x &&
                    y < bound.y + bound.height &&
                    height + y > bound.y) {
                return obj;
            }
        }
        return null;
    }

    public boolean isObstructed(Collidable entityRef, int x, int y) {
        for(Collidable obj : this.objectsInLayer) {
            if (obj == entityRef) continue;
            if (!obj.isSolid()) continue;

            BoundingBox entityRefBound = entityRef.getBoundingBox();
            BoundingBox bound = obj.getBoundingBox();

//            if (obj instanceof Geometry && ((Geometry) obj).getName() == "block") {
//                if (x + entityRefBound.width < bound.x + bound.width) {
//                    System.out.println("left");
//                }
//                if (x > bound.x + bound.width - 1) {
//                    System.out.println("right");
//                }
//
//                if (y + entityRefBound.height < bound.y + bound.height) {
//                    System.out.println("above");
//                }
//                if (y > bound.y + bound.height) {
//                    System.out.println("below");
//                }
//            }

            if(x < bound.x + bound.width &&
                    x + entityRefBound.width > bound.x &&
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
        this.objectsInLayer.removeAll(this.objectsToRemove);
    }

    void draw() {
        for(Collidable obj : this.objectsInLayer) {
            obj.draw(this.controller);
        }
    }
}
