package app.ledprojekt.weapons;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Weapon;

public class Shield extends Weapon {
    private static Model model = new Model(new int[][][]{
            {{33,33,33,1},{33,33,33,1},{33,33,33,1}},
            {{33,33,33,1},{95,95,95,1},{33,33,33,1}},
            {{33,33,33,1},{33,33,33,1},{33,33,33,1}}
    });

    public Shield(int x, int y, Player player) {
        super(model, x, y, player);
    }
}
