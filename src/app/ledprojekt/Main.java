package app.ledprojekt;

import app.ledprojekt.entities.DefaultPlayer;
import app.ledprojekt.entities.Goku;
import app.ledprojekt.entities.PlayerOne;
import app.ledprojekt.entities.PlayerTwo;
import ledControl.BoardController;
import ledControl.LedConfiguration;

import java.util.ArrayList;

/*
 * "Der Auswahlbildschirm nervt, wie komme ich direkt zum Spiel?"
 * Entferne ggf. die Kommentare von "/*static {...} *\/" adde den/die Player, die du testen willst
 * Änder "static State state = States.START" zu "static State state = States.GAME"
 */

public class Main {
    static States state = States.GAME;

    static final Player[] availablePlayers = new Player[]{
            new DefaultPlayer(1, 1),
            new PlayerOne(1, 1),
            new Goku(1, 1),
            new PlayerTwo(2, 1),
    };

    static ArrayList<Player> players = new ArrayList<>();
    static {
        players.add(new Goku(0, 0));
        players.add(null);
    }

    public static void main(String[] args) {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR, true, 60);

        ScreenRenderer renderer = new ScreenRenderer(false);
        renderer.run(controller);
    }
}
