package app.ledprojekt.traits;

import app.ledprojekt.Player;

public abstract class Trait {
    public Object getClassFromArray(String className, Object[] array) {
        for (Object item : array) {
            if(item != null && item.getClass().getSimpleName().equals(className)) {
                return item;
            }
        }
        return null;
    }

    public abstract void update(Player entity);
}
