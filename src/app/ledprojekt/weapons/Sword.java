package app.ledprojekt.weapons;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Weapon;

public class Sword extends Weapon {
    private static Model model = new Model(new int[][][]{
            {{0,34,24,1}},
            {{0,86,34,1}},
            {{0,42,47,1}},
            {{0,23,97,1}}
    });

    public Sword(int x, int y, Player player) {
        super(model, x, y, player);

        Model frame2 = new Model(new int[][][]{
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0,23,24,1}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0,34,24,1}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0,45,24,1}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0,65,24,1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
        });
        Model frame3 = new Model(new int[][][]{
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0,65,24,1}, {0,45,24,1}, {0,34,24,1}, {0,23,24,1}},
        });
        this.addAnimation("attack", 500, model, frame2, frame3, frame2, model);
    }
}
