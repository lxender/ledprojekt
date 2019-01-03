package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Sword;
import app.ledprojekt.traits.*;

public class DefaultPlayer extends Player {
    static Model model = new Model(new int[][][]{
            {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
            {{127, 0, 0, 1}, {0, 0, 0, 1}, {127, 0 ,0, 1}},
            {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
            {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 , 0, 1}},
            {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
            {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
            {{127, 0, 0, 1}, {0, 0, 0, 0}, {127, 0 ,0, 1}},
    });

    public DefaultPlayer(int x, int y) {
        super(x, y, model);

        this.addTraits(new Go(), new Jump(), new Gravity(), new AttackWithWeapon("attack"));
        this.addWeapon(new Sword(3, 1, this));
    }
}
