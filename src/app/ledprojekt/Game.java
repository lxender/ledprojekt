package app.ledprojekt;

import app.ledprojekt.traits.*;
import app.ledprojekt.typography.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;

public class Game {

    private void drawBorder(BoardController controller) {
        for (int i = 0; i < controller.getHeight(); i++) {
            for (int j = 0; j < controller.getWidth(); j++) {
                if((i == 0 || i == controller.getHeight()-1) || (j == 0 || j == controller.getWidth()-1)) {
                    controller.setColor(i, j, 127, 127, 127);
                }
            }
        }
    }

    void run() {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR, true, 60);
        KeyBuffer buffer = controller.getKeyBuffer();

        Player player = new Player(0, 0);
        player.addTraits(new Go(), new Jump(), new Gravity(), new Attack());
        Model weapon = new Model(new int[][][]{
                {{0,34,24,1}},
                {{0,86,34,1}},
                {{0,42,47,1}},
                {{0,23,97,1}}
        });
        player.addWeapon(weapon, 3, 4);
        //player.characterModel.flip();

        Model frame1 = player.characterModel.calculateModelWithAttachment(weapon, 3, 4);
        Model frame2 = player.characterModel.calculateModelWithAttachment(new Model(new int[][][]{
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0,23,24,1}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0,34,24,1}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0,45,24,1}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0,65,24,1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
        }), 3, 4);
        Model frame3 = player.characterModel.calculateModelWithAttachment(new Model(new int[][][]{
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0,65,24,1}, {0,45,24,1}, {0,34,24,1}, {0,23,24,1}},
        }), 3, 4);
        player.addAnimation("attack", frame1, frame2, frame3);

        Geometry ground = new Geometry(0, controller.getHeight() - 1, controller.getWidth(), 1, new int[]{0, 80, 0, 1});
        Geometry block = new Geometry(7, controller.getHeight() - 3, 4, 2, new int[]{80, 90, 0, 1});

        Layer backgroundLayer = new Layer(controller, new Word("abc", 0, 0, new int[]{0, 127, 0, 1}));
        CollisionLayer foregroundLayer = new CollisionLayer(controller, ground, block, player);

//        long lastTime = System.nanoTime();
//        double ns = 1000000.0 / 60.0;

        while(true) {
//            long now = System.nanoTime();
//            System.out.println((now - lastTime) / ns);
//            System.exit(0);

            KeyEvent event = buffer.pop();
            player.updateKeyEventRef(event);

            controller.resetColors();

            //Aus Debug-Gründen: Rahmen um das Feld
            this.drawBorder(controller);

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
