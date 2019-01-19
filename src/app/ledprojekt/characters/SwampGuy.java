package app.ledprojekt.characters;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Sword;
import app.ledprojekt.traits.*;

public class SwampGuy extends Player {
    public SwampGuy(int x, int y) {
        super(x, y, new Model(new int[][][]{
                {{0,63,0,1},{0,127,0,1},{0,63,0,1},{0,63,0,1}},
                {{0,63,63,1},{95,95,95,1},{31,63,63,1},{95,95,95,1}},
                {{0,63,63,1},{63,127,0,1},{0,63,31,1},{0,63,63,1}},
                {{0,63,31,1},{0,63,31,1},{0,63,31,1},{0,63,0,1},{0,63,0,1},{0,63,0,1},{0,63,63,1}},
                {{63,127,0,1},{0,63,63,1},{0,63,63,1},{0,63,63,1}},
                {{0,63,0,1},{0,63,0,1},{0,63,0,1},{63,127,0,1}},
                {{63,127,0,1},{0,0,0,0},{0,0,0,0},{0,63,0,1}},
        }));

        this.addTraits(new Go(), new Turn(), new Jump(), new Gravity(), new AttackWithWeapon("attack"));
        this.addWeapon(new Sword(0, -1,this));
    }
}
