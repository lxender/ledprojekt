import java.awt.event.KeyEvent;

public abstract class Trait {
    public abstract String getName();
    public abstract void update(Entity entity, KeyEvent event);
}
