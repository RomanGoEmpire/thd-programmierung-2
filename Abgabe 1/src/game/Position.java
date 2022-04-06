package game;

public class Position {
    public double x;
    public double y;

    Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Position() {
        this(0, 0);
    }

    public void left(double left) {
        x -= left;
    }

    public void left() {
        x -= 1;
    }

    public void right(double right) {
        x += right;
    }

    public void right() {
        x += 1;
    }

    public void up(double up) {
        y -= up;
    }

    public void up() {
        y -= 1;
    }

    public void down(double down) {
        y += down;
    }

    public void down() {
        y += 1;
    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ')';
    }
}

