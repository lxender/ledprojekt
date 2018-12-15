package app.ledprojekt.traits;

import app.ledprojekt.Entity;

import java.awt.event.KeyEvent;

public interface Trait {
    String getName();
    void update(Entity entity, KeyEvent event);
}
