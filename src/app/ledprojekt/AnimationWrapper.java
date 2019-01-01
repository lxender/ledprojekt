package app.ledprojekt;

import java.util.ArrayList;

public class AnimationWrapper {
    public int duration;
    public ArrayList<Model> list;
    public double msPerFrame;

    public AnimationWrapper(int duration, ArrayList<Model> list) {
        this.duration = duration;
        this.list = list;
        this.msPerFrame = (double) duration / list.size();
        // System.out.println(this.msPerFrame);
    }
}
