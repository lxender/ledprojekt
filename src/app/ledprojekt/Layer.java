package app.ledprojekt;

import ledControl.BoardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layer {
    List<Drawable> objectsInLayer = new ArrayList<>();

    BoardController controller;

    Layer(BoardController controller, Drawable... objects) {
        this.controller = controller;
        this.objectsInLayer.addAll(Arrays.asList(objects));
    }

    void draw() {
        for(Drawable obj : this.objectsInLayer) {
            obj.draw(this.controller);
        }
    }
}
