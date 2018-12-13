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

    public Model(String path, int[] attachmentLocation) {
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

    public void appendAttachment(Model attachment, int x, int y) {
        int[] attachmentLocation = {x, y};
        int[][][] array = attachment.get2DArray();
        for (int row = array.length - 1; row >= 0; row--) {
            for (int col = 0; col < array[row].length; col++) {
                this.model[attachmentLocation[1] - row][attachmentLocation[0]] = array[row][col];
            }
        }
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
