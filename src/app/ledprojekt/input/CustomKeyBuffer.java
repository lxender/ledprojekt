package app.ledprojekt.input;

import java.util.ArrayList;

public class CustomKeyBuffer<T> {
    private ArrayList<T> values = new ArrayList<>();

    public void put(T value) {
        this.values.add(value);
    }

    public T pop() {
        return (this.values.size() > 0) ? this.values.remove(0) : null;
    }
}
