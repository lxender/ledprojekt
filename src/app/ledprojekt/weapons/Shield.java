package app.ledprojekt.weapons;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Weapon;

public class Shield extends Weapon {
    public Shield(int x, int y, Player player) {
        super(x, y, player);

        Model model = new Model(new int[][][]{
                {{33,33,33,1},{33,33,33,1},{33,33,33,1}},
                {{33,33,33,1},{95,95,95,1},{33,33,33,1}},
                {{33,33,33,1},{33,33,33,1},{33,33,33,1}}
        });
        this.setModel(model);
    }
}
