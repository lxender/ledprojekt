package app.ledprojekt;

import app.ledprojekt.typography.Word;
import ledControl.BoardController;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class StartScreen implements Screen {
    private class Box implements Drawable {
        private int x;
        private int y;

        private int width;
        private int height;

        private boolean isSelected = false;

        Box(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void draw(BoardController controller) {
            int[] color = (this.isSelected) ? new int[]{127, 127, 127} : new int[]{127, 0, 0};

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    controller.setColor(this.x + x, this.y + y, color);
                }
            }
        }
    }

    private int x = 0;

    private int selectedBox = 0;

    private KeyBuffer buffer = null;
    private Box[] boxes = new Box[0];

    public void init(BoardController controller) {
        // System.out.println("StartScreen has been initiated");
        this.buffer = controller.getKeyBuffer();

        this.boxes = new Box[4];
        for (int i = 0; i < this.boxes.length; i++) {
            this.boxes[i] = new Box((4 + 1) * i, 0, 4, 4);
        }
        this.boxes[this.selectedBox].isSelected = true;
    }

    public void update(double delta) {
        this.boxes[this.selectedBox].isSelected = false;

        KeyEvent event = this.buffer.pop();
        if (event != null) {
            if (event.getID() == KeyEvent.KEY_RELEASED) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("Enter down!");
                } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    this.selectedBox--;
                } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    this.selectedBox++;
                }
            }
        }

        if (this.selectedBox < 0) {
            this.selectedBox = this.boxes.length - 1;
        } else if (this.selectedBox > this.boxes.length - 1) {
            this.selectedBox = 0;
        }

        this.boxes[this.selectedBox].isSelected = true;
    }

    private void drawBoxes(BoardController controller) {
        for (int i = 0; i < this.boxes.length; i++) {
            Box b = this.boxes[i];
            b.x = this.x + (b.width + 1) * i + 1;
            b.draw(controller);
        }
    }

    public void draw(BoardController controller) {
        controller.resetColors();

        if (this.boxes[this.selectedBox].x + this.boxes[this.selectedBox].width > controller.getWidth() / 2 + this.boxes[this.selectedBox].width / 2) {
            this.x--;
        } else if(this.boxes[this.selectedBox].x + this.boxes[this.selectedBox].width < controller.getWidth() / 2 + this.boxes[this.selectedBox].width / 2) {
            this.x++;
        }

        this.drawBoxes(controller);

        controller.updateBoard();
    }
}
