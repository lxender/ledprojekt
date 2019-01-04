package app.ledprojekt.weapons;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Weapon;

public class SwordLaser extends Weapon {
    private static Model model = new Model(new int[][][]{
            {{127,127,0,1},{127,127,0,1},{0,0,0,0}},
            {{0,0,0,0},{0,0,0,0},{127,127,0,1}},
            {{0,0,0,0},{0,0,0,0},{127,127,0,1}},
            {{127,127,0,1},{127,127,0,1},{0,0,0,0}}
    });

    public SwordLaser(int x, int y, Player player) {
        super(model, x, y, player);

        Model frame2 = new Model(new int[][][]{
                {{127,127,0,1},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}}
        });
        Model frame3 = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{127,127,0,1},{127,127,0,1},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{127,127,0,1}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{127,127,0,1}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{127,127,0,1},{127,127,0,1},{0,0,0,0}}
        });
        this.addAnimation("swordlaser", 500, frame2, frame3,model);
    }
}
