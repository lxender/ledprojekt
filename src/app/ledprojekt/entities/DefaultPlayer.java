package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.DefaultSword;
import app.ledprojekt.traits.*;

public class DefaultPlayer extends Player {
    public DefaultPlayer(int x, int y) {
        super(x, y);
        Model model = new Model(new int[][][]{
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
                {{127, 0, 0, 1}, {0, 0, 0, 1}, {127, 0 ,0, 1}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
                {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 , 0, 1}},
                {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
                {{127, 0, 0, 1}, {0, 0, 0, 0}, {127, 0 ,0, 1}},
        });
        this.setModel(model);

        Model kick = new Model(new int[][][]{
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
                {{127, 0, 0, 1}, {0, 0, 0, 1}, {127, 0 ,0, 1}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}},
                {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 , 0, 1}},
                {{0, 0, 0, 0}, {127, 0, 0, 1}, {0, 0 , 0, 0}},
                {{127, 0, 0, 1}, {127, 0, 0, 1}, {127, 0 ,0, 1}, {127, 0 ,0, 2}},
                {{127, 0, 0, 1}, {0, 0, 0, 0}, {0, 0 ,0, 1}},
        });
        this.addAnimation("kick", 400, kick, model);

        this.addTraits(new Go(), new Turn(), new Jump(), new Gravity(), new AttackWithWeapon("attack"), new AttackWithModel("kick"));
        this.addWeapon(new DefaultSword(1, 1, this));
    }
}
