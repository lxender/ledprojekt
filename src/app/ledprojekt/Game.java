package app.ledprojekt;

import app.ledprojekt.entities.DefaultPlayer;
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

        Geometry ground = new Geometry(0, controller.getHeight() - 1, controller.getWidth(), 1, new int[]{0, 80, 0, 1});
        Geometry block = new Geometry(7, controller.getHeight() - 12, 4, 2, new int[]{80, 90, 0, 1});
        block.setName("block");
        Geometry wall = new Geometry(13, controller.getHeight() - 1 - 5, 1, 5, new int[]{80, 90, 0, 1});

        Player player = new DefaultPlayer(0, 0);

        Layer backgroundLayer = new Layer(controller, new Word("abc", 0, 0, new int[]{0, 127, 0, 1}));
        CollisionLayer foregroundLayer = new CollisionLayer(controller, ground, block, wall, player);

        long lastTime = System.nanoTime();
        double ns = 1000000000.0 / 60.0;
        double delta = 0;

        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            // System.out.println(delta);

            // ### ### ### UPDATING ### ### ###
            while(delta >= 1) {
                KeyEvent event = buffer.pop();
                player.updateKeyEventRef(event);

                foregroundLayer.update();

                delta--;
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
        }
    }
}
