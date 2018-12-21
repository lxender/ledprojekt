package app.ledprojekt;

import ledControl.BoardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layer {
    List<Drawable> objects = new ArrayList<>();

    BoardController controller;
    int[][][] collisionMap = new int[0][0][0];

    Layer(BoardController controller, Drawable... objects) {
        this.controller = controller;
        this.objects.addAll(Arrays.asList(objects));
    }

    void draw(BoardController controller) {
        for(Drawable obj : this.objects) {
            obj.draw(controller);
        }
    }
}
