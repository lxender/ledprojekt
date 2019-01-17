package app.ledprojekt.typography;

import app.ledprojekt.Drawable;
import app.ledprojekt.Model;
import ledControl.BoardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lettering implements Drawable {
    private List<Model> sentenceModels = new ArrayList<>();

    private int x;
    private int y;

    private int width = -1;
    private int sumLetterWidth = 0;

    private double counter;
    private double velocity = 0.1;

    public Lettering(String sentence, int x, int y, int[] color) {
        if(color.length != 4) throw new Error("Color array must be of length 4");

        this.x = x;
        this.y = y;

        String[] splitSentence = sentence.split("");
        for (String s : splitSentence) {
            this.sentenceModels.add(new Letter(s, color, true).getModel());
        }

        this.width = this.getWidth();
    }

    public void draw(BoardController controller) {
        this.sumLetterWidth = 0;

        for (int i = 0; i < this.sentenceModels.size(); i++) {
            if(this.width - this.x > controller.getWidth()) {
                //Rendering wenn der Schriftzug zu groß für den Bildschirm ist
                this.sentenceModels.get(i).draw(controller, this.x + sumLetterWidth + i - ((int) Math.floor(this.counter)), this.y);

                if(this.width - this.counter + 1 < 0) {
                    this.counter = 0;
                }
            } else {
                // Rendering wenn der Schriftzug auf den Bilschirm passt
                this.sentenceModels.get(i).draw(controller, this.x + sumLetterWidth + i, this.y);
            }

            this.sumLetterWidth += this.sentenceModels.get(i).getWidthWithZeroRows();
        }

        if(this.width - this.x > controller.getWidth()) {
            this.counter += this.velocity;
        }
    }

    private int getWidth() {
        int w = 0;
        for (Model sentenceModel : this.sentenceModels) {
            w += sentenceModel.getWidthWithZeroRows() + 1;
        }
        return w;
    }

    public int getHeight() {
        int highestHeight = 0;
        for (Model s : this.sentenceModels) {
            if (s.getHeight() > highestHeight) {
                highestHeight = s.getHeight();
            }
        }
        return highestHeight;
    }

}
