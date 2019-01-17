package app.ledprojekt;

import ledControl.BoardController;

import java.util.*;

public class CollisionLayer implements Layer {
    private BoardController controller;

    public int width;
    public int height;

    private List<Collidable> objectsInLayer = new ArrayList<>();
    private List<Collidable> objectsToRemove = new ArrayList<>();

    boolean PRINT_LAYER = false;

    CollisionLayer(BoardController controller, Collidable... objects) {
        this.controller = controller;
        this.width = controller.getWidth();
        this.height = controller.getHeight();

        this.objectsInLayer.addAll(Arrays.asList(objects));
    }

    public void addObjectsToLayer(Collidable... objs) {
        this.objectsInLayer.addAll(Arrays.asList(objs));
    }
    public void addObjectsToLayer(List list) {
        this.objectsInLayer.addAll(list);
    }

    /**
     * Entfernt Objekte nachdem die Update-Methode jedes Objekt durch gegangen ist.
     * @param obj Objekt, das entfernt werden soll.
     */
    public void removeObjectInLayer(Collidable obj) {
        objectsToRemove.add(obj);
    }

    /**
     * Erstellt ein 2D-Array mit übergebener Breite und Höhe.
     * @param width Breite des Arrays
     * @param height Höhe des Arrays
     * @return Gibt die Matrix/das 2D-Array zurück
     */
    private int[][] createMatrix(int width, int height) {
        int[][] matrix = new int[height][width];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    /**
     * Fügt das Modell eines Objektes in ein 2D-Array ein.
     * @param obj Objekt mit Modell zum Einfügen
     * @param map Array, in die das Modell eingefügt werden soll
     */
    private void mergeIntoMatrix(Collidable obj, int[][] map) {
        int[][][] matrix = obj.getModel().get2DArray();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j][3] != 0) {
                    try {
                        map[obj.getY() + i][obj.getX() + j] = matrix[i][j][3];
                    } catch(ArrayIndexOutOfBoundsException e) {
                        //Ignore it
                    }
                }
            }
        }
    }

    /*
     * Überprüft Kollision, indem jedes Element des übergebenen Objektes durchgegangen wird
     *  -> wenn y + obj.y und x + obj.x innerhalb des Board ist
     *      -> und wenn das Modell an der Stelle nicht 0 ist und das übergebene Array an der Stelle auch nicht null ist
     *          ist etwas an der Stelle
     *  -> wenn y + obj.y und x + obj.x außerhalb des Boards ist
     *      ist etwas an der Stelle
     */
    public boolean collides(int[][][] model, int x, int y, int[][] map) {
        for (int i = 0; i < model.length; ++i) {
            for (int j = 0; j < model[i].length; ++j) {
                try {
                    if(model[i][j][3] != 0 && map[i + y][j + x] != 0) {
                        /*if (model[i][j][3] == 2 && map[i + y][j + x] != 2) {

                        }*/
                        return true;
                    }
                } catch(ArrayIndexOutOfBoundsException e) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Erstellt ein 2D-Array mit allen Collidables in dem Layer ohne den Caller
     * @param caller Selbst-Referenz zum Aufrufenden Objekt
     * @return 2D-Array
     */
    public int[][] createRelativeCollisionMatrix(Collidable caller) {
        int[][] matrix = this.createMatrix(this.width, this.height);
        for (Collidable obj : this.objectsInLayer) {
            if (obj != caller) {
                this.mergeIntoMatrix(obj, matrix);
            }
        }
        return matrix;
    }

    /**
     * Gibt das Objekt zurück, das an der übergebenen Stelle innerhalb der übergebenen Breite und Höhe liegt
     * @param x Anfangswert auf der X-Achse an dem ein Objekt sein kann
     * @param y Anfangswert auf der Y-Achse an dem ein Objekt sein kann
     * @param width Breite des Rechtecks, das überprüft werden soll
     * @param height Höhe des Rechtecks, das überprüft werden soll
     * @return null wenn nichts innerhalb der Stelle ist oder das Objekt innerhalb der Stelle
     */
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

    /**
     * Methode zum Überprüfen, ob eine Stelle in dem Layer belegt ist.
     * @param entityRef Für das Abrufen der Breite und Höhe und zum Überspringen des Objektes im Speicher des Layers.
     * @param x Anfangswert auf der X-Achse an dem ein Objekt sein kann
     * @param y Anfangswert auf der Y-Achse an dem ein Objekt sein kann
     * @return true wenn die Stelle belegt ist, false wenn nicht
     */
    public boolean isObstructed(Collidable entityRef, int x, int y) {
        for(Collidable obj : this.objectsInLayer) {
            if (obj == entityRef) continue;
            if (!obj.isSolid()) continue;

            BoundingBox entityRefBound = entityRef.getBoundingBox();
            BoundingBox bound = obj.getBoundingBox();

            if(x < bound.x + bound.width &&
                    x + entityRefBound.width > bound.x &&
                    y < bound.y + bound.height &&
                    entityRefBound.height + y > bound.y) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gibt ein 3D-Array mit jedem Objekt im Layer aus.
     */
    private void printLayer() {
        int[][] map = this.createMatrix(this.width, this.height);
        int[][][] displayMap = new int[map.length][map[0].length][4];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                displayMap[i][j] = new int[]{map[i][j]};
            }
        }
        Model.print3DArray(displayMap);
    }

    public void update() {
        for (Collidable obj : this.objectsInLayer) {
            obj.updateCollisionLayerRef(this);
            obj.update();
        }
        this.objectsInLayer.removeAll(this.objectsToRemove);
    }
    public void draw() {
        if (this.PRINT_LAYER) {
            this.printLayer();
        }
        for(Collidable obj : this.objectsInLayer) {
            obj.draw(this.controller);
        }
    }
}
