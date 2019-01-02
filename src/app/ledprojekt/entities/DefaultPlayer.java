package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.Sword;
import app.ledprojekt.traits.Go;
import app.ledprojekt.traits.Gravity;
import app.ledprojekt.traits.Jump;
import app.ledprojekt.traits.AttackWithWeapon;

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

        this.addWeapon(new Sword(3, 1));
    }
}
