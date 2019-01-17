package app.ledprojekt.entities;

import app.ledprojekt.Model;
import app.ledprojekt.Player;
import app.ledprojekt.weapons.Laser;
import app.ledprojekt.weapons.Sword;
import app.ledprojekt.traits.*;
import app.ledprojekt.weapons.SwordLaser;

public class Goku extends Player {
    public Goku(int x, int y) {
        super(x, y, new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,64,127,1},{0,64,127,1},{127,127,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,64,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,127,127,1},{127,63,31,1},{127,63,31,1}},
                {{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,64,127,1}},
        }));

        Model walkone = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,64,127,1},{0,64,127,1},{127,127,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,64,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,127,127,1},{127,63,31,1}},
                {{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
        });

        Model walktwo = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,64,127,1},{0,64,127,1},{127,127,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,64,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,127,127,1},{127,63,31,1}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1}},
        });

        Model jump = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,64,127,1},{0,64,127,1},{127,127,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,64,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,127,127,1},{127,63,31,1}},
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
        });

        Model punchone = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,64,127,1},{0,64,127,1},{127,127,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,64,127,1},{127,127,127,1},{127,127,127,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,127,127,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,127,127,1},{127,63,31,1}},
                {{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,64,127,1}},
        });

        Model punchtwo = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,64,127,1},{0,64,127,1},{127,127,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,31,63,1},{127,97,84,1},{127,97,84,1},{127,97,84,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,97,84,1},{127,97,84,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,63,31,1}},
                {{0,0,0,0},{0,31,63,1},{127,127,127,1},{127,127,127,1},{0,31,63,1}},
                {{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,64,127,1}},
        });

        Model punchthree = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,64,127,1},{0,64,127,1},{127,127,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,31,63,1},{127,97,84,1},{127,97,84,1},{127,97,84,1},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{127,63,31,1},{127,97,84,1},{127,63,31,1},{127,63,31,1},{127,97,84,1},{0,0,0,0}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,127,127,1},{0,0,0,0}},
                {{0,0,0,0},{0,31,63,1},{127,127,127,1},{127,127,127,1},{0,31,63,1},{127,127,127,1},{127,127,127,1}},
                {{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,64,127,1}},
        });

        Model punchfour = new Model(new int[][][]{
                {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{0,64,127,1},{0,64,127,1},{127,127,127,1},{0,64,127,1},{0,64,127,1}},
                {{0,64,127,1},{127,127,127,1},{127,127,127,1},{0,0,0,0},{127,127,127,1}},
                {{0,0,0,0},{0,31,63,1},{127,97,84,1},{127,97,84,1},{127,97,84,1},{0,0,0,0},{0,0,0,0}},
                {{0,0,0,0},{127,63,31,1},{127,97,84,1},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,97,84,1}},
                {{0,0,0,0},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,63,31,1},{127,127,127,1},{0,0,0,0}},
                {{0,0,0,0},{0,31,63,1},{127,127,127,1},{127,127,127,1},{0,31,63,1},{127,127,127,1},{127,127,127,1}},
                {{0,0,0,0},{0,64,127,1},{0,0,0,0},{0,0,0,0},{0,64,127,1}},
        });





        this.addTraits(new Go(), new Turn(), new Jump(), new Gravity(), new AttackWithWeapon("laser"));
        this.addWeapon(new Laser(-2, 4,this));
        this.addAnimation("Punch", 500, punchone, punchtwo, punchthree, punchfour, punchthree, punchtwo, punchone);
    }
}
