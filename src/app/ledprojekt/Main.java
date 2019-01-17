package app.ledprojekt;

import app.ledprojekt.entities.DefaultPlayer;
import app.ledprojekt.entities.Goku;
import app.ledprojekt.entities.PlayerOne;
import app.ledprojekt.entities.PlayerTwo;
import ledControl.BoardController;
import ledControl.LedConfiguration;

import java.util.ArrayList;

public class Main {
    static States state = States.START;

    static final Player[] availablePlayers = new Player[]{
            new DefaultPlayer(1, 1),
            new PlayerOne(1, 1),
            new Goku(1, 1),
            new PlayerTwo(2, 1),
    };

    static ArrayList<Player> players = new ArrayList<>();
    /*static {
        players.add(new DefaultPlayer(0, 0));
        players.add(null);
    }*/

    public static void main(String[] args) {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR, true, 60);

        ScreenRenderer renderer = new ScreenRenderer(false);
        renderer.run(controller);
    }
}
