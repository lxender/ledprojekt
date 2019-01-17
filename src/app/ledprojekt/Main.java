package app.ledprojekt;

import ledControl.BoardController;
import ledControl.LedConfiguration;

import java.util.ArrayList;

public class Main {
    static States state = States.START;

    static ArrayList<Player> players = new ArrayList<Player>();

    public static void main(String[] args) {
        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR, true, 60);

        ScreenRenderer renderer = new ScreenRenderer(false);
        renderer.run(controller);
    }
}
