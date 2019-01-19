package app.ledprojekt.screens;

import app.ledprojekt.Geometry;
import app.ledprojekt.Layer;
import app.ledprojekt.Main;
import app.ledprojekt.PlayerManager;
import app.ledprojekt.layers.CollisionLayer;
import app.ledprojekt.layers.DrawingLayer;
import app.ledprojekt.typography.Lettering;
import ledControl.BoardController;
import ledControl.gui.KeyBuffer;

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
        Geometry ground = new Geometry(0, controller.getHeight() - 1, controller.getWidth(), 1, new int[]{0, 80, 0, 1});
        Geometry blockGround = new Geometry(6, controller.getHeight() - 3, 4, 2, new int[]{80, 90, 0, 1});

        this.playerManager = new PlayerManager(controller, Main.players.get(0), Main.players.get(1));

        layers.add(new DrawingLayer(controller, new Lettering("abc", 0, 0, new int[]{0, 127, 0, 1})));
        layers.add(new CollisionLayer(controller, ground, blockGround));
        ((CollisionLayer) layers.get(1)).addObjectsToLayer(this.playerManager.getPlayersAsList());

        this.controller = controller;
        this.keyBuffer = controller.getKeyBuffer();
    }

    public void update(double delta) {
        this.playerManager.update(this.keyBuffer.pop(), delta);
        for (Layer l : this.layers) {
            if (l instanceof CollisionLayer) {
                ((CollisionLayer) l).update();
            }
        }
    }

    public void draw(BoardController controller) {
        controller.resetColors();

        //Aus Debug-Gründen: Rahmen um das Feld
        this.drawBorder(controller);

        // Die Layer unterscheiden sich in der Kollisionsabfrage, also Hintergrund kann nicht mit Vorderung kollidieren
        for (Layer l : this.layers) {
            l.draw();
        }

        this.controller.updateBoard();
    }
}
