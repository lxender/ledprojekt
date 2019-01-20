package app.ledprojekt.screens;

import app.ledprojekt.Main;
import app.ledprojekt.server.Server;
import ledControl.BoardController;

/*
 * ScreenRenderer rendert und updatet die Bildschirme, also StartScreen, Game, EndScreen
 * Die Klasse managed zudem die Stadien, die die App haben kann, also Start, Game, End
 */
public class ScreenRenderer {
    private boolean PRINT_FPS;
    public boolean isRunning = true;

    private Screen currentScreen = null;

    public ScreenRenderer(boolean print_fps) {
        this.PRINT_FPS = print_fps;
    }

    /*
     * (Die Negation von currentScreen instanceof X sorgt dafür, dass die folgende Anweisung nur ein Mal aufgerufen wird)
     * Funktionsweise am Beispiel von StartScreen bzw. state = States.START:
     * Wenn currentScreen null ist oder der State der App START ist
     *  -> StartScreen wird aufgerufen und initiliasiert
     *
     *  Bei GAME_WITH_SERVER wird zudem ein Server gestartet
     */
    private void screenManager(BoardController controller) {
        if (this.currentScreen == null || (Main.state == Main.States.START && !(this.currentScreen instanceof StartScreen))) {
            this.currentScreen = new StartScreen();
            this.currentScreen.init(controller);
        } else if (Main.state == Main.States.GAME && !(this.currentScreen instanceof Game)) {
            this.currentScreen = new Game();
            this.currentScreen.init(controller);
        } else if (Main.state == Main.States.GAME_WITH_SERVER && !(this.currentScreen instanceof Game)) {
            Thread serverThread = new Thread(new Server());
            this.currentScreen = new Game();

            serverThread.start();
            this.currentScreen.init(controller);
        } else if (Main.state == Main.States.END && !(this.currentScreen instanceof EndScreen)) {
            this.currentScreen = new EndScreen();
            this.currentScreen.init(controller);
        }
    }

    public void run(BoardController controller) {
        long timer = System.currentTimeMillis();
        long lastTime = System.currentTimeMillis();
        double step = 1/60.0;
        double accumulator = 0;

        int frames = 0;
        int updates = 0;

        while(this.isRunning) {
            long now = System.currentTimeMillis();
            accumulator += (now - lastTime) / 1000.0;
            lastTime = now;

            this.screenManager(controller);

            // ### ### ### UPDATING ### ### ###
            while(accumulator > step) {
                this.currentScreen.update(step);

                if (this.PRINT_FPS) {
                    updates++;
                }

                accumulator -= step;
            }

            // ### ### ### RENDERING ### ### ###
            this.currentScreen.draw(controller);

            if (this.PRINT_FPS) {
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("Updates: " + updates + ", " + frames + "fps");
                    updates = 0;
                    frames = 0;
                }
            }
        }
    }
}
