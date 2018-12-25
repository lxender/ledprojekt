package app.ledprojekt;

import app.ledprojekt.traits.Go;
import app.ledprojekt.traits.Gravity;
import app.ledprojekt.typography.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;

public class Game {

    private static void drawBorder(BoardController controller) {
        for (int i = 0; i < controller.getHeight(); i++) {
            for (int j = 0; j < controller.getWidth(); j++) {
                if((i == 0 || i == controller.getHeight()-1) || (j == 0 || j == controller.getWidth()-1)) {
                    controller.setColor(i, j, 127, 127, 127);
                }
            }
        }
    }

    static void run() {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR);
        KeyBuffer buffer = controller.getKeyBuffer();

        Player player = new Player(0, 0);
        player.characterModel.flip();
        player.addTrait(new Go());
        player.addTrait(new Gravity());
        Model weapon = new Model(new int[][][]{
                {{0,127,24,1}},
                {{0,127,24,1}},
                {{0,127,24,1}},
                {{0,127,24,1}}
        });
        player.addWeapon(weapon, 0, 4);

        Model groundModel = new Model(new int[][][]{
                {{0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}, {0, 80, 0, 1}}
        });
        Geometry groundGeo = new Geometry(groundModel, 0, controller.getHeight() - 1);


        Layer backgroundLayer = new Layer(controller, new Word("abc", 0, 0, new int[]{0, 127, 0, 1}));
        CollisionLayer foregroundLayer = new CollisionLayer(controller, groundGeo, player);

        while(true) {
            KeyEvent event = buffer.pop();
            player.updateKeyEventRef(event);

            controller.resetColors();

            //Aus Debug-Gründen: Rahmen um das Feld
            Game.drawBorder(controller);

            // Die Layer unterscheiden sich in der Kollisionsabfrage, also Hintergrund kann nicht mit Vorderung kollidieren
            // Hintergrund
            backgroundLayer.draw();

            // Vorderground
            foregroundLayer.update();
            foregroundLayer.draw();

            controller.updateBoard();
        }
    }
}
