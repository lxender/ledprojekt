package app.ledprojekt.screens;

import app.ledprojekt.Main;
import app.ledprojekt.Player;
import app.ledprojekt.typography.Lettering;
import ledControl.BoardController;

import java.util.ArrayList;

public class EndScreen implements Screen {

    private ArrayList<Lettering> letterings = new ArrayList<>();

    public int getWinningPlayer() {
        for (int i = 0; i < Main.players.size(); i++) {
            if(!Main.players.get(i).isDead()) {
                return i + 1;
            }
        }
        return -1;
    }
    @Override
    public void init(BoardController controller) {
        int winningPlayer = this.getWinningPlayer();

        this.letterings.add(new Lettering("S" + winningPlayer, 0, 0, new int[]{0, 0, 127, 1}));
        this.letterings.add(new Lettering("WON", 0, this.letterings.get(0).getHeight() + 1, new int[]{0, 0, 127, 1}));
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(BoardController controller) {
        controller.resetColors();

        for (Lettering l : this.letterings) {
            l.draw(controller);
        }

        controller.updateBoard();
    }
}
