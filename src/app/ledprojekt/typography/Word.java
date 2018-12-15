package app.ledprojekt.typography;

import app.ledprojekt.Model;
import ledControl.BoardController;

import java.util.ArrayList;
import java.util.List;

public class Word {
    List<Model> sentenceModels = new ArrayList<>();

    public Word(String sentence, int[] color) {
        if(color.length != 4) throw new Error("Color array must be 4");

        String[] splitSentence = sentence.split("");
        for (int i = 0; i < splitSentence.length; i++) {
            this.sentenceModels.add(new Letter(splitSentence[i], color, true).getModel());
        }
    }

    public void draw(BoardController controller, int x, int y) {
        for (int i = 0; i < this.sentenceModels.size(); i++) {
            this.sentenceModels.get(i).draw(controller, x + i * (this.sentenceModels.get(i).getWidth() + 1), y);
        }
    }

    private int getWidth() {
        int w = 0;
        for (int i = 0; i < this.sentenceModels.size(); i++) {
            w = i * (this.sentenceModels.get(i).getWidth() + 1);
        }
        return w;
    }

}
