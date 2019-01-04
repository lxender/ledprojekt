package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Laser;
import app.ledprojekt.weapons.Sword;
import app.ledprojekt.traits.*;

public class PlayerTwo extends Player {
    static Model model = new Model(new int[][][]{
            {{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{0,0,0,0},{0,0,0,0}},
            {{127,0,0,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{0,0,0,0}},
            {{127,0,0,1},{127,0,0,1},{127,127,127,1},{127,0,0,1},{0,0,0,0},{0,0,0,0}},
            {{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1}},
            {{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{0,0,0,0},{0,0,0,0}},
            {{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{0,0,0,0},{0,0,0,0}},
            {{127,0,0,1},{0,0,0,0},{0,0,0,0},{127,0,0,1},{0,0,0,0},{0,0,0,0}}
    });

    public PlayerTwo(int x, int y) {
        super(x, y, model);

        this.addTraits(new Go(), new Jump(), new Gravity(), new AttackWithWeapon("laser"));

        //this.addWeapon(new Sword(7,2,this));

        this.addWeapon(new Laser(11, 2, this));

        this.flip();
    }
}
