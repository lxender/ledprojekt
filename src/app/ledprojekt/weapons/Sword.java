package app.ledprojekt.weapons;

import app.ledprojekt.Model;
import app.ledprojekt.weapons.Weapon;

public class Sword extends Weapon {
    private static Model model = new Model(new int[][][]{
            {{127,127,127,1}},
            {{127,127,127,1}},
            {{127,127,127,1}},
            {{127,127,127,1}}
    });

    public Sword(int x, int y) {
        super(model, x, y);

        Model frame2 = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,0,0,0},{127,127,127,1},{0,0,0,0}},
                {{0,0,0,0},{127,127,127,1},{0,0,0,0},{0,0,0,0}},
                {{127,127,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}}
        });
        Model frame3 = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1}}
        });
        this.addAnimation("attack", 450, model, frame2, frame3, frame2, model);
    }
}
