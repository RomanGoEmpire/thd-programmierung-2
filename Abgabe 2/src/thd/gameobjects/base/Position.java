package thd.gameobjects.base;

import thd.gameview.GameView;

/**
 * Position describes where a certain point is located on the canvas {@link GameView}
 * Therefore, you have the x and y-axis coordinates.
 * The Position (0,0) is located in the top left corner.
 * The maximum visible pixel:
 * {@link GameView}
 * x from 0 to {@link GameView#HEIGHT} and y from 0 to {@link GameView#WIDTH} as
 *
 * @see GameView
 */
public class Position {
    /**
     * x-coordinate.
     */
    public double x;
    /**
     * y-coordinate.
     */
    public double y;

    /**
     * Constructor with instance variables.
     *
     * @param x is the x-axis position
     * @param y is the y-axis position
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor with the default location.
     */
    public Position() {
        this(0, 0);
    }

    /**
     * @param left decreases the x value by the instance variable.
     */
    public void left(double left) {
        x -= left;
    }

    /**
     * decreases x value by 1.
     */
    public void left() {
        x -= 1;
    }

    /**
     * @param right increases x value by instance variable.
     */
    public void right(double right) {
        x += right;
    }

    /**
     * increases x value by 1.
     */
    public void right() {
        x += 1;
    }

    /**
     * @param up decreases y value by instance variable.
     */
    public void up(double up) {
        y -= up;
    }

    /**
     * decreases y value by 1.
     */
    public void up() {
        y -= 1;
    }

    /**
     * @param down increases y value by instance variable.
     */
    public void down(double down) {
        y += down;
    }

    /**
     * decreases y value by instance variable.
     */
    public void down() {
        y += 1;
    }

    public double distance(Position other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));

    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ')';
    }
}

