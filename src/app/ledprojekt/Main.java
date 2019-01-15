package app.ledprojekt;

import ledControl.BoardController;
import ledControl.LedConfiguration;

public class Main {
    static States state = States.START;

    public static void main(String[] args) {
        Game game = new Game();
        game.run();

        BoardController controller = BoardController.getBoardController(LedConfiguration.LED_20x20_EMULATOR, true, 60);

        ScreenRenderer renderer = new ScreenRenderer(false);
        renderer.run(controller);
    }
}
