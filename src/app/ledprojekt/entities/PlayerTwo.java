package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Laser;
import app.ledprojekt.weapons.Sword;
import app.ledprojekt.traits.*;

public class PlayerTwo extends Player {
    public PlayerTwo(int x, int y) {
        super(x, y, new Model(new int[][][]{
                {{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{0,0,0,0},{0,0,0,0}},
                {{127,0,0,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{0,0,0,0}},
                {{127,0,0,1},{127,0,0,1},{127,127,127,1},{127,0,0,1},{0,0,0,0},{0,0,0,0}},
                {{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1}},
                {{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{0,0,0,0},{0,0,0,0}},
                {{127,0,0,1},{127,0,0,1},{127,0,0,1},{127,0,0,1},{0,0,0,0},{0,0,0,0}},
                {{127,0,0,1},{0,0,0,0},{0,0,0,0},{127,0,0,1},{0,0,0,0},{0,0,0,0}}
        }), 4, 7);

        this.addTraits(new Go(), new Turn(), new Jump(), new Gravity(), new AttackWithWeapon("laser"));

        //this.addWeapon(new Sword(7,2,this));

        this.addWeapon(new Laser(5, 2, this));
    }
}
