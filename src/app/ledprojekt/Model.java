package app.ledprojekt;

import ledControl.BoardController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Model {
    private int[][][] model;

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

    public int getWidth() {
        return this.model[0].length;
    }
    public int getHeight() {
        return this.model.length;
    }

    public void flip() {
        for(int i = 0; i < this.model.length; i++) {
            int[][] flipped = new int[this.model[i].length][4];
            for (int j = this.model[i].length - 1; j > 0; j--) {
                flipped[j] = this.model[i][j];
            }
            this.model[i] = flipped;
        }
    }

    public void appendAttachment(Model attachment, int x, int y) {
        int[][][] array = attachment.get2DArray();

        this.model = this.extendArray(this.model, x + 1, y + 1, new int[]{0, 0, 0, 0});

        for (int row = array.length - 1; row >= 0; row--) {
            for (int col = 0; col < array[row].length; col++) {
                this.model[y - row][x] = array[row][col];
            }
        }
    }

    private int[][][] extendArray(int[][][] array, int newLengthX, int newLengthY, int[] newValue) {
        int[][][] copy = Arrays.copyOf(array, array.length);

        if (newLengthY > array.length) {
            int[][][] largerCopy = Arrays.copyOf(copy, newLengthY);
            for (int i = copy.length; i < newLengthY; i++) {
                largerCopy[i] = new int[largerCopy[0].length][];

                for(int j = 0; j < largerCopy[i].length; j++) {
                    largerCopy[i][j] = newValue;
                }

            }
            copy = largerCopy;
        }

        if (newLengthX > array[0].length) {
            System.out.println("x is larger");
            for (int i = 0; i < copy.length; i++) {
                int[][] largerRowCopy = Arrays.copyOf(copy[i], newLengthX);
                for (int j = copy[i].length; j < largerRowCopy.length; j++) {
                    largerRowCopy[j] = newValue;
                }
                copy[i] = largerRowCopy;
            }
        }

        return copy;
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

    public void draw(BoardController controller, int xPos, int yPos) {
        for (int y = 0; y < this.model.length; y++) {
            for (int x = 0; x < this.model[y].length; x++) {
                int[] rgba = this.model[y][x];
                int[] rgb = new int[]{rgba[0], rgba[1], rgba[2]};

                if(rgba[3] != 1) {
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
