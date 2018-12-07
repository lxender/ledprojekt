import ledControl.BoardController;

import java.awt.event.KeyEvent;

public class Character {
    int xPos;
    int yPos;

    Model characterModel = new Model(new int[][][]{
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0, 0, 1}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 , 0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0, 0, 0}, {127, 0 ,0, 1}, {0, 0, 0, 0}},
    }, new int[]{4, 4});

    private Model attachment;

    BoardController controller;

    Character(int x, int y, BoardController controller) {
        this.xPos = x;
        this.yPos = y;
        this.controller = controller;
    }

    public void addWeapon(Model weapon) {
        this.characterModel.appendAttachment(weapon);
        this.attachment = weapon;
    }

    public void update(KeyEvent event) {
        if(event != null) {
            if(event.getID() == java.awt.event.KeyEvent.KEY_PRESSED) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        this.yPos -= 1;
                        break;

                    case KeyEvent.VK_DOWN:
                        this.yPos += 1;
                        break;

                    case KeyEvent.VK_LEFT:
                        this.xPos -= 1;
                        break;

                    case KeyEvent.VK_RIGHT:
                        this.xPos += 1;
                        break;

                    default:
                        break;
                }
            }
        }
    }

    public void draw() {
        int[][][] array = characterModel.get2DArray();
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                int[] rgba = array[y][x];
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