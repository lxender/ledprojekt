package app.ledprojekt;

import ledControl.BoardController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Model {
    public static void print2DArray(int[][][] array) {
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
        for (int i = 0; i < this.model.length; i++) {
            List<int[]> flipped = Arrays.asList(this.model[i]);
            Collections.reverse(flipped);
            this.model[i] = flipped.toArray(new int[][]{});
        }
    }

    public void appendAttachment(Model attachment, int x, int y) {
        this.model = this.calculateArrayWithAttachment(attachment, x, y);
    }
    public Model calculateModelWithAttachment(Model attachment, int x, int y) {
        return new Model(this.calculateArrayWithAttachment(attachment, x, y));
    }
    private int[][][] calculateArrayWithAttachment(Model attachment, int x, int y) {
        int[][][] array = attachment.get2DArray();
        int[][][] modelCopy = Arrays.copyOf(this.model, this.model.length);

        array = squareArray(array, new int[]{0, 0, 0, 0});
        modelCopy = this.extendArray(modelCopy, x + array[0].length, y + array.length, new int[]{0, 0, 0, 0});
        //Model.print2DArray(array);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                // System.out.println(String.format("Model i: %d, model j: %d", modelCopy.length - y - (array.length - 1 - i), x + j));
                modelCopy[modelCopy.length - y - (array.length - 1 - i)][x + j] = array[i][j];
            }
        }

//        for (int row = array.length - 1; row >= 0; row--) {
//            for (int col = 0; col < array[row].length; col++) {
//                modelCopy[y - row][x + col] = array[row][col];
//            }
//        }

        //Model.print2DArray(modelCopy);
        return modelCopy;
    }
    private int getLongestRow(int[][][] array) {
        int[] lengths = Arrays.stream(array).mapToInt(row -> row.length).toArray();
        Arrays.sort(lengths);
        return lengths[lengths.length - 1];
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

        if (newLengthY < 0) {
            int[][][] temp = new int[copy.length + Math.abs(newLengthY)][array[0].length][];

            for (int i = 0; i < copy.length + Math.abs(newLengthY); i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    if(i < Math.abs(newLengthY)) {
                        temp[i][j] = newValue;
                    } else {
                        temp[i][j] = copy[i - Math.abs(newLengthY)][j];
                    }
                }
            }
            copy = temp;
        }

        if (newLengthX > array[0].length) {
            for (int i = 0; i < copy.length; i++) {
                int[][] largerRowCopy = Arrays.copyOf(copy[i], newLengthX);
                for (int j = copy[i].length; j < largerRowCopy.length; j++) {
                    largerRowCopy[j] = newValue;
                }
                copy[i] = largerRowCopy;
            }
        }

        if (newLengthX < 0) {
            for(int i = 0; i < copy.length; i++) {
                int[][] row = new int[Math.abs(newLengthX) + copy[i].length][];
                for (int j = 0; j < row.length; j++) {
                    if (j < Math.abs(newLengthX)) {
                        row[j] = newValue;
                    } else {
                        row[j] = copy[i][j - Math.abs(newLengthX)];
                    }
                }
                copy[i] = row;
            }
        }

        return copy;
    }
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
