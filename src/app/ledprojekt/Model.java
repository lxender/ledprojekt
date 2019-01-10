package app.ledprojekt;

import ledControl.BoardController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Model {
    /**
     * Gibt ein 3D-Array in der Konsole korrekt formatiert (d.h. mit Zeilenumbrüchen nach jeder Reihe) aus.
     * @param array 3D-Array zum ausgeben.
     */
    public static void print3DArray(int[][][] array) {
        System.out.println("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print("  [");
            int[][] row = array[i];
            for (int j = 0; j < row.length; j++) {
                System.out.print(Arrays.toString(row[j]) + ((j == row.length - 1) ? "" : ", "));
            }
            System.out.println("],");
        }
        System.out.println("]");
    }

    private int[][][] model;
    private boolean isFlipped = false;

    public Model(int[][][] array) {
        this.model = array;
    }
    public Model(int[][] array, int width, int height) {
        this.model = new int[width][height][4];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.model[i][j] = array[i];
            }
        }
    }

    /**
     * Konstruktor zur Nutzung eines Bildes als Modell.
     * @param path Pfad zu einer Bilddatei.
     */
    public Model(String path) {
        File file;
        BufferedImage img;
        try {
            file = new File(path);
            img = ImageIO.read(file);

            int width = img.getWidth();
            int height = img.getHeight();

            int[][][] result = new int[height][width][];
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int p = img.getRGB(col, row);
                    Color c = new Color(p, true);
                    int[] rgba;
                    if (c.getAlpha() == 255) {
                        rgba = new int[]{c.getRed(), c.getGreen(), c.getBlue(), (c.getAlpha() == 255) ? 1 : 0};
                    } else {
                        rgba = new int[]{0, 0, 0, 0};
                    }
                    result[row][col] = rgba;
//                    System.out.print(Arrays.toString(rgba));
                }
//                System.out.print("\n");
            }
            this.model = result;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Sucht nach der längsten Reihe ohne 0 als 4tes Element im Pixel.
     * Geht alle Reihen des übergebenen Arrays durch und vergleicht sie mit der momentan längsten Reihe.
     * Wenn die Momentane länger ist als die Vorherige, wird sie zum neuen "Recordhalter".
     */
    private int getLongestNonZeroRow(int[][][] array) {
        int longest = -1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j][3] != 0) {
                    if (longest == -1) {
                        longest = j;
                    } else if(j > longest) {
                        longest = j;
                    }
                }
            }
        }
        return longest;
    }
    private int getLongestRow(int[][][] array) {
        int[] lengths = Arrays.stream(array).mapToInt(row -> row.length).toArray();
        Arrays.sort(lengths);
        return lengths[lengths.length - 1];
    }
    public int getWidth() {
        return getLongestNonZeroRow(this.model);
    }
    public int getHeight() {
        return this.model.length;
    }

    /*
     * Macht das Array quadratisch und flippt es, indem es
     * jede Reihe durchgeht, sie zur Liste macht und sie mit Collections.reverse dreht.
     */
    public void flip() {
        this.model = this.squareArray(this.model, new int[]{0, 0, 0, 0});
        for (int i = 0; i < this.model.length; i++) {
            List<int[]> flipped = Arrays.asList(this.model[i]);
            Collections.reverse(flipped);
            this.model[i] = flipped.toArray(new int[][]{});
        }
        this.isFlipped = !this.isFlipped;
    }
    public boolean isFlipped() {
        return this.isFlipped;
    }

    /*
     * Macht ein ein "unebenes" Array eben. z.B.
     * [[[5], [6]],
     * [[2]]]
     * zu
     * [[[5], [6]],
     * [[2], newValue]]
     *
     * Es bekommt die längste Reihe, erstellt ein neues quadratisches Array, falls es existiert, wird der Wert aus
     * dem alten Array genommen, wenn dies nicht existiert (i oder j out of bounds ist), wird der neue Wert gesetzt.
     */
    private int[][][] squareArray(int[][][] array, int[] newValue) {
        int longestRowLength = this.getLongestRow(array);

        int[][][] temp = new int[array.length][longestRowLength][];

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                try {
                    temp[i][j] = array[i][j];
                } catch(ArrayIndexOutOfBoundsException e) {
                    temp[i][j] = newValue;
                }
            }
        }

        return temp;
    }

    public int[][][] get2DArray() {
        return this.model;
    }

    public int[][] toSimpleArray() {
        int[][] temp = new int[0][4];
        for (int i = 0; i < this.model.length; i++) {
            for (int j = 0; j < this.model[0].length; j++) {
                temp = Arrays.copyOf(temp, temp.length + 1);
                temp[temp.length - 1] = this.model[i][j];
            }
        }
        return temp;
    }

    /*
     * Wenn das vierte Element des Modells nicht 1 ist, wird die Farbe an der Stelle angefragt.
     *  -> Ist diese Stelle innerhalb des Boards, wird die Farbe an der Stelle genommen, ansonsten die des Pixels.
     * Die Farbe wird an das Board übergeben und an der Stelle xPos + x-Position im Array und yPos + y-Position im Array dargestellt.
     */
    public void draw(BoardController controller, int xPos, int yPos) {
        for (int y = 0; y < this.model.length; y++) {
            for (int x = 0; x < this.model[y].length; x++) {
                int[] rgba = this.model[y][x];
                int[] rgb = new int[]{rgba[0], rgba[1], rgba[2]};

                if(rgba[3] == 0) {
                    int[] color;
                    try {
                        color = controller.getColorAt(x + xPos, y + yPos);
                    } catch(ArrayIndexOutOfBoundsException e) {
                        color = rgb;
                    }
                    controller.setColor(x + xPos, y + yPos, color);
                } else {
                    controller.setColor(x + xPos, y + yPos, rgb);
                }
            }
        }
    }
}
