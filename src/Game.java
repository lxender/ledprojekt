import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Game {

    public static void drawBorder(BoardController controller) {
        for (int i = 0; i < controller.getWidth(); i++) {
            for (int j = 0; j < controller.getHeight(); j++) {
                if((i == 0 || i == controller.getWidth()-1) || (j == 0 || j == controller.getHeight()-1)) {
                    controller.setColor(i, j, 127, 127, 127);
                }
            }
        }
    }

    public static void main(String[] args) {

        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
        KeyBuffer buffer = controller.getKeyBuffer();

        Character player = new Character(0, 0, controller);
        Model weapon = new Model(new int[][][]{
                {{0, 127, 0, 1}},
                {{0, 127, 0, 1}},
                {{0, 127, 0, 1}},
                {{0, 127, 0, 1}}
        });
        player.addWeapon(weapon);

        while(true) {
            KeyEvent event = buffer.pop();
            player.update(event);

            controller.resetColors();
            Game.drawBorder(controller);
            player.draw();
            controller.updateBoard();
        }
    }
}
