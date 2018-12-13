import java.awt.event.KeyEvent;

public class Go extends Trait {

    private String name = "Go";

    public String getName() {
        return this.name;
    }

    public void update(Entity entity, KeyEvent event) {
        if(event != null) {
            if(event.getID() == KeyEvent.KEY_RELEASED) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        entity.setY(entity.getY() - 1);
                        break;

                    case KeyEvent.VK_DOWN:
                        entity.setY(entity.getY() + 1);
                        break;

                    case KeyEvent.VK_LEFT:
                        entity.setX(entity.getX() - 1);
                        break;

                    case KeyEvent.VK_RIGHT:
                        entity.setX(entity.getX() + 1);
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
