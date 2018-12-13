import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Game {

    public static void drawBorder(BoardController controller) {
        for (int i = 0; i < controller.getHeight(); i++) {
            for (int j = 0; j < controller.getWidth(); j++) {
                if((i == 0 || i == controller.getHeight()-1) || (j == 0 || j == controller.getWidth()-1)) {
                    controller.setColor(i, j, 127, 127, 127);
                }
            }
        }
    }

    public static void run() {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
        KeyBuffer buffer = controller.getKeyBuffer();

        Character player = new Character(0, 0);
        player.addTrait(new Go());
        Model weapon = new Model(new int[][][]{
                {{0,127,24,1}},
                {{0,127,24,1}},
                {{0,127,24,1}},
                {{0,127,24,1}}
        });
        player.addWeapon(weapon, 4, 4);

        Model groundModel = new Model(new int[][][]{
                {{0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}}
        });
        Geometry groundGeo = new Geometry(groundModel, 0, controller.getHeight() - 1);

        while(true) {
            KeyEvent event = buffer.pop();
            player.update(event);


            controller.resetColors();

            //Aus Debug-Gründen: Rahmen um das Feld
            Game.drawBorder(controller);

            //Hintergrund
            groundGeo.draw(controller);

            //Vorderground
            player.draw(controller);

            controller.updateBoard();
        }
    }
}
