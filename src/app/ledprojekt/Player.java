package app.ledprojekt;

import app.ledprojekt.traits.Trait;
import app.ledprojekt.weapons.Weapon;
import ledControl.BoardController;

import java.awt.event.KeyEvent;
import java.util.*;

public class Player implements Entity {
    private HashMap<String, Integer> keybindings = new HashMap<>();

    private double x;
    private double y;

    private double velocityX;
    private double velocityY;

    private Model characterModel;
    private int modelWidth;
    private int modelHeight;
    private boolean modelIsFlipped = false;
    private Weapon weapon;

    private BoundingBox bounds;

    public Healthbar healthbar = new Healthbar(this, 0, 0);
    private boolean drawHealthbarFlag = true;
    private int health = 20;
    private boolean killable = true;

    private long collisionTimer = System.currentTimeMillis();

    private List<Trait> traits = new ArrayList<>();
    private KeyEvent keyEvent;
    private CollisionLayer layer;

    private double dt;

    private AnimationManager animManager = new AnimationManager();

    public boolean isJumping = false;

    public Player(int x, int y, Model model) {
        this.setDefaultKeyBindings();
        this.x = x;
        this.y = y;

        this.characterModel = model;
        this.modelWidth = this.characterModel.getWidth();
        this.modelHeight = this.characterModel.getHeight();

        this.updateBoundingBox();
    }

    public Player(int x, int y) {
        this.setDefaultKeyBindings();
        this.x = x;
        this.y = y;

        this.bounds = new BoundingBox(x, y, 0, 0);
    }

    private void setDefaultKeyBindings() {
        keybindings.put("left", KeyEvent.VK_LEFT);
        keybindings.put("right", KeyEvent.VK_RIGHT);
        keybindings.put("jump", KeyEvent.VK_SPACE);
        keybindings.put("attack", KeyEvent.VK_E);
        keybindings.put("secondary-attack", KeyEvent.VK_Q);
    }
    public void setKeyBindings(HashMap keybindings) {
        this.keybindings = keybindings;
    }
    public HashMap getKeyBindings() {
        return this.keybindings;
    }

    public void setX(int x) {
        this.x = x;
        this.updateBoundingBox();
    }
    public int getX() { return (int) this.x; }
    public void setXAsDouble(double x) {
        this.x = x;
        this.updateBoundingBox();
    }
    public double getXAsDouble() { return this.x; }

    public void setY(int y) {
        this.y = y;
        this.updateBoundingBox();
    }
    public int getY() { return (int) this.y; }
    public void setYAsDouble(double y) {
        this.y = y;
        this.updateBoundingBox();
    }
    public double getYAsDouble() { return this.y; }

    public double getVelocityX() {
        return this.velocityX;
    }
    public void setVelocityX(double x) { this.velocityX = x; }
    public double getVelocityY() {
        return this.velocityY;
    }
    public void setVelocityY(double y) { this.velocityY = y; }

    public BoundingBox getBoundingBox() {
        return this.bounds;
    }
    private void updateBoundingBox() {
        this.bounds = new BoundingBox((int) this.x, (int) this.y, this.modelWidth, this.modelHeight);
    }

    public Model getModel() {
        return this.characterModel;
    }
    public void setModel(Model model) {
        if(this.modelIsFlipped && !model.isFlipped()) {
            model.flip();
        } else if(!this.modelIsFlipped && model.isFlipped()) {
            model.flip();
        }
        this.characterModel = model;
        this.modelWidth = model.getWidth();
        this.modelHeight = model.getHeight();
        this.updateBoundingBox();
    }

    public void setHealth(int value) {
        this.health = value;
    }
    public void decreaseHealth(int by) {
        this.health -= by;
    }
    public int getHealth() {
        return this.health;
    }

    public boolean isKillable() {
        return this.killable;
    }
    public void toggleKillablility() {
        this.killable = !this.killable;
    }
    public boolean isDead() {
        if (this.killable && this.health <= 0) {
            return true;
        }

        return false;
    }

    public void disableHealthbar() {
        this.drawHealthbarFlag = false;
    }

    public boolean isSolid() {
        return true;
    }

    public void addTraits(Trait... traits) {
        this.traits.addAll(Arrays.asList(traits));
    }
    public boolean hasTrait(String className) {
        for (Trait t : this.traits) {
            if (t.getClass().getSimpleName().equals(className)) {
               return true;
            }
        }
        return false;
    }

    /**
     * Entfernt die Klasse der Eigenschaft.
     * @param className Muss genau der Namen der Klasse sein, z.B. "Go" für die Klasse Go.java, die die Eigenschaft des Gehens verwaltet.
     */
    public void removeTrait(String className) {
        int index = -1;
        for (int i = 0; i < this.traits.size(); i++) {
            if (this.traits.get(i).getClass().getSimpleName().equals(className)) {
                index = i;
            }
        }
        if (index != -1) {
            this.traits.remove(index);
        } else {
            System.out.println("Trait to remove was not found.");
        }
    }

    /**
     * Dreht den Player und seine Waffe (falls vorhanden).
     */
    public void flip() {
        this.modelIsFlipped = !this.modelIsFlipped;
        this.characterModel.flip();
        if (this.weapon != null) {
            this.weapon.checkFlip();
        }
        this.updateBoundingBox();
    }
    public boolean isFlipped() {
        return this.modelIsFlipped;
    }

    public void addWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public void removeWeapon() {
        this.weapon = null;
    }
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * Fügt der Klasse eine Animation hinzu.
     * @param name Der Name der Animation. Sollte am besten lowercase übergeben werden.
     * @param durationInMillis Totale Zeit der Animation in Millisekunden.
     * @param models Bilder/Keyframes der Animation
     */
    public void addAnimation(String name, int durationInMillis, Model... models) {
        this.animManager.addAnimation(name, durationInMillis, models);
    }
    public boolean hasAnimation(String name) {
        return this.animManager.hasAnimation(name);
    }
    /**
     * Setzt den Abspielstatus einer Animation.
     * @param name Der Name der Animation. Am besten lowercase übergeben.
     * @param value true zum Abspielen, false zum Stoppen.
     */
    public void setAnimationPlayState(String name, boolean value) {
        this.animManager.setAnimationPlayState(name, value);
    }

    /**
     * Überprüft, ob es eine Animation gibt, dessen Status true ist.
     * Ist dies der Fall, bekommt sie ein Bild der Animation und setzt das Modell des Players mit dem gleich.
     */
    private void playAnimation() {
        Model frame = this.animManager.playAnimation();
        if (frame != null) {
            this.setModel(frame);
        }
    }

    public void updateKeyEventRef(KeyEvent event) {
        this.keyEvent = event;
    }
    public KeyEvent getKeyEventRef() {
        return this.keyEvent;
    }

    public void updateCollisionLayerRef(CollisionLayer layer) {
        this.layer = layer;
    }
    public CollisionLayer getCollisionLayerRef() {
        return this.layer;
    }

    public void updateDelta(double dt) {
        this.dt = dt;
    }
    public double getDelta() {
        return this.dt;
    }

    private void handleModelAttackCollision() {
        int[][] map = this.layer.createRelativeCollisionMatrix(this);

        if (this.layer.modelCollides(this.characterModel.get2DArray(), (int) this.x, (int) this.y, map)) {
            Collidable[] objects = layer.getObjectsAt(this, (int) this.x, (int) this.y, this.characterModel.getWidthWithZeroRows(), this.characterModel.getHeight());

            if (System.currentTimeMillis() - this.collisionTimer > 1000) {
                for (Collidable obj : objects) {
                    if (obj instanceof Player) {
                        ((Player) obj).decreaseHealth(1);
                    }
                }
                this.collisionTimer = System.currentTimeMillis();
            }
        }
    }

    /*
     * Wenn der Player tot ist, wird dieser aus dem Layer entfernt und die Methode wird abgebrochen.
     * Wenn eine Waffe vorhanden ist, werden ihre Update-Methoden aufgerufen.
     * Alle Update-Methoden der Eigenschaften des Players werden aufgerufen.
     * Wenn Model eine 2 im Array hat, wird die Kollsion zwischen ihm und der Welt abgefragt.
     * Velocity wird angewandt. Auf X wird keine DeltaTime angewandt, weil sich X nur um Ganzzahlen bewegt.
     */
    public void update() {
        if (this.characterModel == null) return;
        if (this.isDead()) {
            this.layer.removeObjectInLayer(this);
            return;
        }

        if (this.weapon != null) {
            this.weapon.updatePlayerRef(this);
            this.weapon.updateCollisionLayerRef(this.layer);
            this.weapon.update();
        }

        for (Trait trait : this.traits) {
            trait.update(this);
        }

        if (this.characterModel.hasValueAsOpacity(2)) {
            this.handleModelAttackCollision();
        }

        this.x += this.velocityX;
        this.y += this.velocityY * this.dt;
    }

    /*
     * Wenn der Player tot ist, wird die Methode abgebrochen.
     * Wenn die Lebensanzeige nicht deaktiviert ist, wird die Lebensanzeige dargestellt.
     * Animationen werden abgespielt.
     * Wenn es eine Waffe gibt, wird ihre draw-Methode aufgerufen.
     * Die draw-Methode des Modells des Players wird mit x und y als Integers aufgerufen.
     */
    public void draw(BoardController controller) {
        if (this.characterModel == null) {
            System.out.println("No model defined yet.");
            return;
        }
        if (this.isDead()) {
            return;
        }
        if (this.drawHealthbarFlag) {
            this.healthbar.draw(controller);
//            this.drawHealthbar(controller, 0, 0);
        }

        this.playAnimation();

        this.characterModel.draw(controller, (int) this.x, (int) this.y);

        if (this.weapon != null) {
            this.weapon.draw(controller);
        }


    }




    public class Healthbar {
        private int x;
        private int y;
        private Player player;

        private int maxLength = 9;

        public Healthbar(Player player, int x, int y) {
            this.x = x;
            this.y = y;
            this.player = player;
        }

        public void setX(int x) { this.x = x; };
        public void setY(int y) { this.y = y; };
        public int getMaxLength() { return this.maxLength; }

        /**
         * Malt die Lebensanzeige des Players. Sie kollidiert nicht.
         * Sie hat eine Breite von 9 und wechselt pro Reihe die Farbe.
         * Gerade Reihen (inkl. 0) bekommen {127, 25, 3}, ungerade {127, 96, 3}.
         * @param controller Board auf dem die Lebensanzeige dargestellt werden soll
         */
        public void draw(BoardController controller) {
            int[] firstColor = new int[]{127, 25, 3};
            int[] secondColor = new int[]{127, 96, 3};

            int currentRow = 0;
            for (int i = 0; i < this.player.health; i++) {
                if (i % this.maxLength == 0) currentRow++;
                int[] color = (currentRow % 2 != 0) ? firstColor : secondColor;
                int xCord = this.x + (i % this.maxLength);
                controller.setColor(xCord, this.y, color);
            }
        }
    }
}