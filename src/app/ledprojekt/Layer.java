package app.ledprojekt;

import ledControl.BoardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layer {
    List<Drawable> objects = new ArrayList<>();

    public Layer(Drawable... objects) {
        this.objects.addAll(Arrays.asList(objects));
    }

    public void draw(BoardController controller) {
        for(Drawable obj : this.objects) {
            obj.draw(controller);
        }
    }
}
