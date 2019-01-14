package app.ledprojekt;

public class ScreenRenderer {
    private boolean PRINT_FPS;
    public boolean isRunning = true;

    public ScreenRenderer(boolean print_fps) {
        this.PRINT_FPS = print_fps;
    }

    /*void run(Screen screen) {
        screen.init();

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

            // ### ### ### UPDATING ### ### ###
            while(accumulator > step) {
                screen.update(step);

                if (this.PRINT_FPS) {
                    updates++;
                }

                accumulator -= step;
            }

            // ### ### ### RENDERING ### ### ###
            screen.draw();

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
    }*/
}
