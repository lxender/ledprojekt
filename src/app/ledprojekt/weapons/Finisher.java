package app.ledprojekt.weapons;

import app.ledprojekt.Model;
import app.ledprojekt.Player;

public class Finisher extends Weapon {
    public Finisher(int x, int y, Player player) {
        super(x, y, player);

        Model model = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1},{0,0,0,0}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1}},
                {{0,0,0,0},{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}

        });
        this.setModel(model);


    }
}