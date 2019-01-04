package app.ledprojekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AnimationManager {
    private long timer = 0;

    private HashMap<String, AnimationWrapper> animations = new HashMap<>();
    private HashMap<String, Boolean> animationPlayStates = new HashMap<>();
    private ArrayList<Model> currentlyPlayingAnimation = new ArrayList<>();

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

    public void addAnimation(String name, int durationInMillis, Model... models) {
        //System.out.println("Name: " + name + ", array: " + Arrays.toString(models));
        ArrayList<Model> list = new ArrayList<>(Arrays.asList(models));
        AnimationWrapper wrapper = new AnimationWrapper(durationInMillis, list);
        this.animations.put(name.toLowerCase(), wrapper);
    }
    public void setAnimationPlayState(String name, boolean value) {
        this.animationPlayStates.put(name.toLowerCase(), value);
        this.timer = System.currentTimeMillis();
    }
    public Model playAnimation() {
        if (!this.animationPlayStates.isEmpty()) {
            String playingAnimationName = AnimationManager.findAnimation(this.animationPlayStates);

            if (playingAnimationName != null) {
                AnimationWrapper anim = this.animations.get(playingAnimationName);
                if (System.currentTimeMillis() - this.timer > anim.msPerFrame) {
                    Model frame = AnimationManager.getFrame(playingAnimationName, this.currentlyPlayingAnimation, anim, this.animationPlayStates);
                    this.timer += anim.msPerFrame;

                    return frame;
                }
            }
        }

        return null;
    }
}
