package app.ledprojekt;

import java.util.ArrayList;
import java.util.HashMap;

public class Animation {
    public static String findAnimation(HashMap<String, Boolean> animationPlayStates) {
        if (animationPlayStates.containsValue(true)) {
            for(String k : animationPlayStates.keySet()) {
                if(animationPlayStates.get(k.toLowerCase())) {
                    return k;
                }
            }
        }
        return null;
    }

    public static Model getFrame(String name, ArrayList<Model> currentlyPlayingAnimation, AnimationWrapper anim, HashMap<String, Boolean> animationPlayStates) {
        if (currentlyPlayingAnimation.isEmpty()) {
            currentlyPlayingAnimation.addAll(anim.list);
        }

        Model frame = currentlyPlayingAnimation.remove(0);

        if(currentlyPlayingAnimation.size() == 0) {
            currentlyPlayingAnimation.clear();
            animationPlayStates.replace(name, false);
        }

        return frame;
    }
}
