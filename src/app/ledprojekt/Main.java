package app.ledprojekt;

import app.ledprojekt.characters.*;
import app.ledprojekt.input.CustomKeyBuffer;
import app.ledprojekt.input.CustomKeyEvent;
import app.ledprojekt.screens.ScreenRenderer;
import ledControl.BoardController;
import ledControl.LedConfiguration;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
 * "Der Auswahlbildschirm nervt, wie komme ich direkt zum Spiel?"
 * Entferne ggf. die Kommentare von "/*static {...} *\/" adde den/die Player, die du testen willst
 * Änder "static State state = States.START" zu "static State state = States.GAME"
 */

public class Main {
    public static final boolean withServer = false;

    public static States state = States.GAME_WITH_SERVER;
    public static CustomKeyBuffer<CustomKeyEvent> buffer = new CustomKeyBuffer<>();

    public static final Player[] availablePlayers = new Player[]{
            new DefaultPlayer(1, 1),
            new PlayerOne(1, 1),
            new SwampGuy(1, 1),
            new Goku(1, 1),
            new PlayerTwo(1, 1),
    };

    public static ArrayList<Player> players = new ArrayList<>();
    static {
        players.add(new PlayerTwo(0, 0));
        players.add(new Goku(0, 0));
    }

    public static void main(String[] args) {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR, true, 60);

        ScreenRenderer renderer = new ScreenRenderer(false);
        renderer.run(controller);
    }

    public enum States {
        START,
        GAME,
        GAME_WITH_SERVER,
        END
    }
}
