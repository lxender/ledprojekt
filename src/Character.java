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
    });

    private Model attachment;

    Character(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public void addWeapon(Model weapon, int x, int y) {
        this.characterModel.appendAttachment(weapon, x, y);
        this.attachment = weapon;
    }

    public boolean hasWeapon() {
        return (this.attachment != null);
    }

    public void update(KeyEvent event) {
        if(event != null) {
            if(event.getID() == KeyEvent.KEY_RELEASED) {
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

    public void draw(BoardController controller) {
        this.characterModel.draw(controller, xPos, yPos);
    }
}