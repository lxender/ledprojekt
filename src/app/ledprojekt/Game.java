package app.ledprojekt;

import app.ledprojekt.entities.DefaultPlayer;
import app.ledprojekt.traits.*;
import app.ledprojekt.typography.Word;
import ledControl.BoardController;
import ledControl.LedConfiguration;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;

public class Game {
    static boolean PRINT_FPS = true;

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

        Geometry ground = new Geometry(0, controller.getHeight() - 1, controller.getWidth(), 1, new int[]{0, 80, 0, 1});
        Geometry blockGround = new Geometry(7, controller.getHeight() - 3, 4, 2, new int[]{80, 90, 0, 1});
        Geometry block = new Geometry(7, controller.getHeight() - 14, 4, 2, new int[]{80, 90, 0, 1});
        block.setName("block");
        //Geometry wall = new Geometry(1, controller.getHeight() - 1 - 6, 1, 6, new int[]{80, 90, 0, 1});

        Player player = new DefaultPlayer(13, 0);
        Player dummy = new DefaultPlayer(1, 0);
        dummy.removeTrait("Go");
        dummy.removeWeapon();

        Layer backgroundLayer = new Layer(controller, new Word("abc", 0, 0, new int[]{0, 127, 0, 1}));
        CollisionLayer foregroundLayer = new CollisionLayer(controller, ground, blockGround, block, dummy, player);

        long timer = System.currentTimeMillis();
        long lastTime = System.currentTimeMillis();
        double step = 1/240.0;
        //double ns = 1000000000.0 / 60.0;
        double accumulator = 0;

        int frames = 0;
        int updates = 0;

        while(true) {
            long now = System.currentTimeMillis();
            accumulator += (now - lastTime) / 1000.0;
            lastTime = now;

            // System.out.println(delta);

            // ### ### ### UPDATING ### ### ###
            while(accumulator > step) {
                KeyEvent event = buffer.pop();
                player.updateKeyEventRef(event);

                player.updateDelta(step);
                dummy.updateDelta(step);

                foregroundLayer.update();

                if (PRINT_FPS) {
                    updates++;
                }

                accumulator -= step;
            }

            // ### ### ### RENDERING ### ### ###
            controller.resetColors();

            //Aus Debug-Gründen: Rahmen um das Feld
            this.drawBorder(controller);

            // Die Layer unterscheiden sich in der Kollisionsabfrage, also Hintergrund kann nicht mit Vorderung kollidieren
            // Hintergrund
            backgroundLayer.draw();

            // Vorderground
            foregroundLayer.draw();

            controller.updateBoard();

            if (PRINT_FPS) {
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("Updates: " + updates + ", " + frames + "fps");
                    updates = 0;
                    frames = 0;
                }
            }
        }
    }
}
