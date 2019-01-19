package app.ledprojekt.typography;

import app.ledprojekt.layers.Drawable;
import app.ledprojekt.Model;
import ledControl.BoardController;

import java.util.ArrayList;
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



    private class Letter {
        private Model letter;

        public Letter(String name, int[] color, boolean emptyIsTransparent) {
            int[] transparent = this.createPixel(new int[]{0, 0, 0}, (emptyIsTransparent) ? 0 : 1);

            switch (name.toUpperCase()) {
                case " ":
                    this.letter = new Model(new int[][][]{
                            {transparent},
                            {transparent},
                            {transparent},
                            {transparent},
                            {transparent},
                    });
                    break;
                case "A":
                    this.letter = new Model(new int[][][] {
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                    });
                    break;
                case "B":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                    });
                    break;
                case "C":
                    this.letter = new Model(new int[][][]{
                            {transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                            {transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                    });
                    break;
                case "D":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                    });
                    break;
                case "E":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                    });
                    break;
                case "F":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                    });
                    break;
                case "G":
                    this.letter = new Model(new int[][][]{
                            {transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                    });
                    break;
                case "H":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                    });
                    break;
                case "I":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1)},
                            {this.createPixel(color, 1)},
                            {this.createPixel(color, 1)},
                            {this.createPixel(color, 1)},
                            {this.createPixel(color, 1)},
                    });
                    break;
                case "J":
                    this.letter = new Model(new int[][][]{
                            {transparent, transparent, this.createPixel(color, 1)},
                            {transparent, transparent, this.createPixel(color, 1)},
                            {transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent},
                    });
                    break;
                case "K":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                    });
                    break;
                case "L":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                    });
                    break;
                case "M":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                    });
                    break;
                case "N":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                    });
                    break;
                case "O":
                    this.letter = new Model(new int[][][]{
                            {transparent, this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent},
                    });
                    break;
                case "P":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                    });
                    break;
                case "Q":
                    this.letter = new Model(new int[][][]{
                            {transparent, this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1), transparent},
                            {transparent, this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                    });
                    break;
                case "R":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                    });
                    break;
                case "S":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                    });
                    break;
                case "T":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent},
                            {transparent, this.createPixel(color, 1), transparent},
                            {transparent, this.createPixel(color, 1), transparent},
                            {transparent, this.createPixel(color, 1), transparent},
                    });
                    break;
                case "U":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent},
                    });
                    break;
                case "V":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent, this.createPixel(color, 1), transparent},
                            {transparent, transparent, this.createPixel(color, 1), transparent, transparent},
                    });
                    break;
                case "W":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, transparent, transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent, this.createPixel(color, 1), transparent},
                    });
                    break;
                case "X":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                    });
                    break;
                case "Y":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent},
                            {transparent, this.createPixel(color, 1), transparent},
                    });
                    break;
                case "Z":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                            {transparent, transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, transparent},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                    });
                    break;
                case "1":
                    this.letter = new Model(new int[][][]{
                            {this.createPixel(color, 1), this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1)},
                    });
                    break;

                case "2":
                    this.letter = new Model(new int[][][]{
                            {transparent, this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), transparent, this.createPixel(color, 1)},
                            {transparent, transparent, this.createPixel(color, 1)},
                            {transparent, this.createPixel(color, 1), transparent},
                            {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                    });
                    break;

                default:
                    break;
            }
        }

        private int[] createPixel(int[] color, int alpha) {
            color = new int[]{color[0], color[1], color[2], alpha};
            return color;
        }

        public Model getModel() {
            return this.letter;
        }

        public int getWidth() {
            return this.letter.getWidth();
        }

        public int getHeight() {
            return this.letter.getHeight();
        }

        public void draw(BoardController controller, int x, int y) {
            this.letter.draw(controller, x, y);
        }
    }
}
