package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.DefaultSword;
import app.ledprojekt.traits.*;

public class DefaultPlayer extends Player {
    public DefaultPlayer(int x, int y) {
        super(x, y, new Model(new int[][][]{
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
                {{127, 0, 0, 1}, {0, 0, 0, 1}, {127, 0 ,0, 1}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 90 ,0, 1}},
                {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 , 0, 1}},
                {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
                {{127, 0, 0, 1}, {0, 0, 0, 0}, {127, 0 ,0, 1}},
        }));

        this.addTraits(new Go(), new Turn(), new Jump(), new Gravity(), new AttackWithWeapon("attack"));
        this.addWeapon(new DefaultSword(3, 1, this));
    }
}
