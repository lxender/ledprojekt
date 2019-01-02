package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Sword;
import app.ledprojekt.traits.*;

public class PlayerOne extends Player {
    static Model model = new Model(new int[][][]{
            {{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,0,0,0},{0,0,0,0}},
            {{0,127,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{0,0,0,0}},
            {{0,127,127,1},{0,127,127,1},{127,127,127,1},{0,127,127,1},{0,0,0,0},{0,0,0,0}},
            {{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1}},
            {{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,0,0,0},{0,0,0,0}},
            {{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,127,127,1},{0,0,0,0},{0,0,0,0}},
            {{0,127,127,1},{0,0,0,0},{0,0,0,0},{0,127,127,1},{0,0,0,0},{0,0,0,0}}
    });

    public PlayerOne(int x, int y) {
        super(x, y, model);

        this.addTraits(new Go(), new Jump(), new Gravity(), new AttackWithWeapon("attack"));

        this.addWeapon(new Sword(5, -1));

        //this.flip();
    }
}
