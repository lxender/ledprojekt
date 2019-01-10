package app.ledprojekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AnimationManager {
    private long timer = 0;

    private HashMap<String, AnimationWrapper> animations = new HashMap<>();
    private HashMap<String, Boolean> animationPlayStates = new HashMap<>();
    private ArrayList<Model> currentlyPlayingAnimation = new ArrayList<>();

    /**
     * Findet eine derzeit abspielende Animation im Manager.
     * @return Den Namen der abspielenden Animation oder null wenn keine gefunden wurde.
     */
    public String findAnimation() {
        if (this.animationPlayStates.containsValue(true)) {
            for(String k : this.animationPlayStates.keySet()) {
                if(this.animationPlayStates.get(k.toLowerCase())) {
                    return k;
                }
            }
        }
        return null;
    }

    /**
     * Methode um ein Bild der abspielenden Animation zu bekommen.
     * @param name Name der Animation
     * @param currentlyPlayingAnimation Referenz zum Objekt, das die gerade spielende Animation beinhaltet
     * @param anim die Animation, die abgespielt werden soll
     * @param animationPlayStates Referenz zum Objekt, das alle Spielzustände beinhaltet
     * @return Das nächste Bild der Animation
     */
    public Model getFrame(String name, ArrayList<Model> currentlyPlayingAnimation, AnimationWrapper anim, HashMap<String, Boolean> animationPlayStates) {
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

    /**
     * Fügt dem animations-Objekt die übergebene Animation hinzu.
     * @param name Name der Animation
     * @param durationInMillis Dauer der Animation
     * @param models Bilder der Animation als Modelle
     */
    public void addAnimation(String name, int durationInMillis, Model... models) {
        ArrayList<Model> list = new ArrayList<>(Arrays.asList(models));
        AnimationWrapper wrapper = new AnimationWrapper(durationInMillis, list);
        this.animations.put(name.toLowerCase(), wrapper);
    }

    /**
     * Setzt den Zustand der namentlich übergebenen Animation (in lowercase).
     * @param name Name der Animation
     * @param value Wert, auf den der Zustand gesetzt werden soll
     */
    public void setAnimationPlayState(String name, boolean value) {
        this.animationPlayStates.put(name.toLowerCase(), value);
        this.timer = System.currentTimeMillis();
    }

    /**
     * Überprüft, ob eine Animation abgespielt werden soll und gibt das Bild der Animation (mit korrekten Timing) zurück.
     * @return Bild der abspielenden Animation oder null wenn keine Animation abgespielt wird
     */
    public Model playAnimation() {
        if (!this.animationPlayStates.isEmpty()) {
            String playingAnimationName = this.findAnimation();

            if (playingAnimationName != null) {
                AnimationWrapper anim = this.animations.get(playingAnimationName);
                if (System.currentTimeMillis() - this.timer > anim.msPerFrame) {
                    Model frame = this.getFrame(playingAnimationName, this.currentlyPlayingAnimation, anim, this.animationPlayStates);
                    this.timer += anim.msPerFrame;

                    return frame;
                }
            }
        }

        return null;
    }
}
