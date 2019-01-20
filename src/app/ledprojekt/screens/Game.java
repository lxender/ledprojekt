package app.ledprojekt.screens;

import app.ledprojekt.Geometry;
import app.ledprojekt.Layer;
import app.ledprojekt.Main;
import app.ledprojekt.PlayerManager;
import app.ledprojekt.input.CustomKeyEvent;
import app.ledprojekt.layers.CollisionLayer;
import app.ledprojekt.layers.DrawingLayer;
import app.ledprojekt.typography.Lettering;
import ledControl.BoardController;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game implements Screen {
    private void drawBorder(BoardController controller) {
        for (int i = 0; i < controller.getHeight(); i++) {
            for (int j = 0; j < controller.getWidth(); j++) {
                if((i == 0 || i == controller.getHeight()-1) || (j == 0 || j == controller.getWidth()-1)) {
                    controller.setColor(i, j, 127, 127, 127);
                }
            }
        }
    }

    private BoardController controller;
    private KeyBuffer keyBuffer;
    private PlayerManager playerManager;
    private ArrayList<Layer> layers = new ArrayList<>();

    public void init(BoardController controller) {
        Geometry backgroundGround = new Geometry(0, controller.getHeight() - 3, controller.getWidth(), 1, new int[]{0, 50, 0, 1});
        Geometry foregroundGround = new Geometry(0, controller.getHeight() - 1, controller.getWidth(), 1, new int[]{0, 100, 0, 1});
        Geometry ground = new Geometry(0, controller.getHeight() - 2, controller.getWidth(), 1, new int[]{0, 80, 0, 1});
        Geometry blockGround = new Geometry(6, controller.getHeight() - 4, 4, 2, new int[]{80, 90, 0, 1});

        this.playerManager = new PlayerManager(controller, Main.players.get(0), Main.players.get(1));

        layers.add(new DrawingLayer(controller, new Lettering("abc", 0, 0, new int[]{0, 127, 0, 1}), foregroundGround, backgroundGround));
        layers.add(new CollisionLayer(controller, ground, blockGround));
        ((CollisionLayer) layers.get(1)).addObjectsToLayer(this.playerManager.getPlayersAsList());

        this.controller = controller;
        this.keyBuffer = controller.getKeyBuffer();
    }

    public void update(double delta) {

        CustomKeyEvent eventToPassDown;
        if (Main.state == Main.States.GAME_WITH_SERVER) {
            eventToPassDown = Main.buffer.pop();
        } else {
            KeyEvent event = keyBuffer.pop();
            eventToPassDown = (event == null) ? null : new CustomKeyEvent(event.getKeyCode(), event.getID());
        }
        this.playerManager.update(eventToPassDown, delta);

        /*
         * CollisionLayer wird geupdated
         */
        for (Layer l : this.layers) {
            if (l instanceof CollisionLayer) {
                ((CollisionLayer) l).update();
            }
        }
    }

    public void draw(BoardController controller) {
        controller.resetColors();

        //Aus Debug-Gründen: Rahmen um das Feld
        //this.drawBorder(controller);

       /*
        * Die Layers werden gerendert
        */
        for (Layer l : this.layers) {
            l.draw();
        }

        this.controller.updateBoard();
    }
}
