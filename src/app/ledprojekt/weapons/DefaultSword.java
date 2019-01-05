package app.ledprojekt.weapons;

import app.ledprojekt.Model;
import app.ledprojekt.Player;

public class DefaultSword extends Weapon {
    public DefaultSword(int x, int y, Player player) {
        super(x, y, player);

        Model model = new Model(new int[][][]{
                {{127,127,127,1}},
                {{127,127,127,1}},
                {{127,127,127,1}},
                {{127,127,127,1}},
        });
        this.setModel(model);

        Model frame2 = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{127,127,127,1},{0,0,0,0}},
                {{127,127,127,1},{0,0,0,0},{0,0,0,0}}
        });
        Model frame3 = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{127,127,127,1},{127,127,127,1},{127,127,127,1}, {127,127,127,1}}
        });
        this.addAnimation("attack", 500, model, frame2, frame3, frame2, model);
    }
}
