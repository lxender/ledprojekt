package app.ledprojekt;

import app.ledprojekt.characters.*;
import app.ledprojekt.input.CustomKeyBuffer;
import app.ledprojekt.input.CustomKeyEvent;
import app.ledprojekt.screens.ScreenRenderer;
import ledControl.BoardController;
import ledControl.LedConfiguration;

import java.util.ArrayList;

/*
 * "Der Auswahlbildschirm nervt, wie komme ich direkt zum Spiel?"
 * Entferne ggf. die Kommentare von "/*static {...} *\/" adde den/die Player, die du testen willst
 * Änder "static State state = States.START" zu "static State state = States.GAME"
 */

public class Main {
    // Soll das Spiel mit einem Server gestartet werden, über den die Kontrolle der Spieler läuft?
    public static final boolean withServer = false;
    /* Anfangs-State der App
     * Wird während der Laufzeit für die Bildschirm-Wechsel benutzt.
     * z.B will StartScreen zu Game übergehen, setzt die Klasse den Wert dieser Variable zu "States.GAME" (ScreenRenderer managed die States)
     */
    public static States state = States.START;

    // Eigener KeyBuffer für Input über den Server
    public static CustomKeyBuffer<CustomKeyEvent> buffer = new CustomKeyBuffer<>();

    // Alle Spieler, die in dem Auswahlbildschirm angezeigt werden sollen
    public static final Player[] availablePlayers = new Player[]{
            new DefaultPlayer(1, 1),
            new PlayerOne(1, 1),
            new SwampGuy(1, 1),
            new Goku(1, 1),
            new PlayerTwo(1, 1),
    };

    public static ArrayList<Player> players = new ArrayList<>();
    /*static {
        // Wenn man nur einen Player sehen möchte, muss der Andere null sein. Die Position ist egal, da sie durch PlayerManager verwaltet wird
        players.add(new Goku(0, 0));
        players.add(null);
    }*/

    public static void main(String[] args) {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR, true, 60);

        ScreenRenderer renderer = new ScreenRenderer(false);
        renderer.run(controller);
    }

    // Stadien, die die App haben kann
    public enum States {
        START,
        GAME,
        GAME_WITH_SERVER,
        END
    }
}
