package app.ledprojekt;

import app.ledprojekt.traits.Go;
import app.ledprojekt.typography.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;

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

        Player player = new Player(0, 0);
        player.characterModel.flip();
        player.addTrait(new Go());
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

        Word message = new Word("jk", 1, 1, new int[]{0, 0, 127, 1});

        while(true) {
            KeyEvent event = buffer.pop();
            player.update(event);


            controller.resetColors();

            //Aus Debug-Gründen: Rahmen um das Feld
            Game.drawBorder(controller);

            // Die Layer werden sich in der Kollisionsabfrage unterscheiden, also Hintergrund kann nicht mit Vorderung kollidieren
            // Hintergrund
            // Hier wird ein Hintergrund-Layer platziert

            // Vorderground
            // Hier wird ein Vordergrund-Layer platziert
            groundGeo.draw(controller);
            player.draw(controller);

            controller.updateBoard();
        }
    }
}
