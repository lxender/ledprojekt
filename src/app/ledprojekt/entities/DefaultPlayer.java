package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.traits.Go;
import app.ledprojekt.traits.Gravity;
import app.ledprojekt.traits.Jump;
import app.ledprojekt.traits.Attack;

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

        this.addTraits(new Go(), new Jump(), new Gravity(), new Attack("attack"));

        Model weapon = new Model(new int[][][]{
                {{0,34,24,1}},
                {{0,86,34,1}},
                {{0,42,47,1}},
                {{0,23,97,1}}
        });
        this.addWeapon(weapon, 3, 4);

        Model frame1 = model.calculateModelWithAttachment(weapon, 3, 4);
        Model frame2 = model.calculateModelWithAttachment(new Model(new int[][][]{
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0,23,24,1}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0,34,24,1}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0,45,24,1}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0,65,24,1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
        }), 3, 4);
        Model frame3 = model.calculateModelWithAttachment(new Model(new int[][][]{
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0,65,24,1}, {0,45,24,1}, {0,34,24,1}, {0,23,24,1}},
        }), 3, 4);
        this.addAnimation("attack", frame1, frame2, frame3);
    }

    @Override
    public void update() {
        super.update();
    }
}
