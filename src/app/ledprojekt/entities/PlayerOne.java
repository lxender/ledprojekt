package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Laser;
import app.ledprojekt.weapons.Sword;
import app.ledprojekt.traits.*;
import app.ledprojekt.weapons.SwordLaser;

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

        this.addWeapon(new Sword(7, -1, this ));

        this.flip();
    }
}
