package app.ledprojekt.typography;

import app.ledprojekt.Model;
import ledControl.BoardController;

public class Letter {
    private Model letter;

    private int[] color;
    private int[] transparent;

    public Letter(String name, int[] color, boolean emptyIsTransparent) {
        this.color = color;
        this.transparent = this.createPixel(new int[]{0, 0, 0}, (emptyIsTransparent) ? 0 : 1);

        switch (name.toUpperCase()) {
            case " ":
                this.letter = new Model(new int[][][]{
                        {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                        {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                        {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                        {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                        {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                });
                break;
            case "A":
                this.letter = new Model(new int[][][] {
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                });
                break;
            case "B":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                });
                break;
            case "C":
                this.letter = new Model(new int[][][]{
                        {this.transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                });
                break;
            case "D":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                });
                break;
            case "E":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                });
                break;
            case "F":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                });
                break;
            case "G":
                this.letter = new Model(new int[][][]{
                        {this.transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                });
                break;
            case "H":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
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
                        {this.transparent, this.createPixel(color, 0), this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 0), this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 0), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                });
                break;
            case "K":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                });
                break;
            case "L":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                });
                break;
            case "M":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.transparent, this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent, this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1), this.createPixel(color, 0), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent, this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent, this.transparent, this.createPixel(color, 1)},
                });
                break;
            case "N":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                });
                break;
            case "O":
                this.letter = new Model(new int[][][]{
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                });
                break;
            case "P":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                });
                break;
            case "Q":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 0), this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 0)},
                        {this.createPixel(color, 1), this.createPixel(color, 0), this.createPixel(color, 0), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 0), this.createPixel(color, 0), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 0), this.createPixel(color, 1), this.createPixel(color, 0)},
                        {this.createPixel(color, 0), this.createPixel(color, 1), this.createPixel(color, 0), this.createPixel(color, 1)},
                });
                break;
            case "R":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                });
                break;
            case "S":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.transparent, this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                });
                break;
            case "T":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                });
                break;
            case "U":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                });
                break;
            case "V":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                });
                break;
            case "W":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.transparent, this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent, this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.transparent, this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.transparent, this.createPixel(color, 1), this.transparent},
                });
                break;
            case "X":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                });
                break;
            case "Y":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 0), this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.transparent, this.createPixel(color, 1)},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                });
                break;
            case "Z":
                this.letter = new Model(new int[][][]{
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                        {this.transparent, this.transparent, this.createPixel(color, 1)},
                        {this.transparent, this.createPixel(color, 1), this.transparent},
                        {this.createPixel(color, 1), this.transparent, this.transparent},
                        {this.createPixel(color, 1), this.createPixel(color, 1), this.createPixel(color, 1)},
                });
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
