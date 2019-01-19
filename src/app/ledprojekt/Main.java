package app.ledprojekt;

import app.ledprojekt.entities.*;
import ledControl.BoardController;
import ledControl.LedConfiguration;

import java.util.ArrayList;

/*
 * "Der Auswahlbildschirm nervt, wie komme ich direkt zum Spiel?"
 * Entferne ggf. die Kommentare von "/*static {...} *\/" adde den/die Player, die du testen willst
 * Änder "static State state = States.START" zu "static State state = States.GAME"
 */

public class Main {
    public static States state = States.GAME;

    public static final Player[] availablePlayers = new Player[]{
            new DefaultPlayer(1, 1),
            new PlayerOne(1, 1),
            new SwampGuy(1, 1),
            new Goku(1, 1),
            new PlayerTwo(2, 1),
    };

    public static ArrayList<Player> players = new ArrayList<>();
    static {
        Player p1 = new DefaultPlayer(0, 0);
        p1.removeWeapon();
        players.add(p1);

        Player p2 = new DefaultPlayer(0, 0);
        p2.removeWeapon();
        players.add(p2);
    }

    public static void main(String[] args) {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR, true, 60);

        ScreenRenderer renderer = new ScreenRenderer(false);
        renderer.run(controller);
    }
}
