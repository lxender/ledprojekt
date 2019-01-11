package app.ledprojekt;

public class BoundingBox {
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;

    public BoundingBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("x %d, y %d, width %d, height %d", x, y, width, height);
    }
}
