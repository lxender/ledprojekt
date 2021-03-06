package app.ledprojekt.layers;

import app.ledprojekt.Layer;
import ledControl.BoardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawingLayer implements Layer {
    List<Drawable> objectsInLayer = new ArrayList<>();

    BoardController controller;

    public DrawingLayer(BoardController controller, Drawable... objects) {
        this.controller = controller;
        this.objectsInLayer.addAll(Arrays.asList(objects));
    }

    public void draw() {
        for(Drawable obj : this.objectsInLayer) {
            obj.draw(this.controller);
        }
    }
}
