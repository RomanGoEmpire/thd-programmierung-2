package thd.gameobjects.base;

import java.util.Objects;

/**
 * Position describes where a certain point is located on the canvas {@link GameView}
 * Therefore, you have the X and y-axis coordinates.
 * The Position (0,0) is located in the top left corner.
 * The maximum visible pixel:
 * {@link GameView}
 * x from 0 to {@link GameView#HEIGHT} and y from 0 to {@link GameView#WIDTH} as
 *
 * @see GameView
 */

public class Position implements Cloneable, Comparable<Position> {
    /**
     * x-coordinate.
     */
    public double x;
    /**
     * y-coordinate.
     */
    public double y;

    /**
     * Initializes a Position.
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
     * decreases the x value by the instance variable.
     *
     * @param left amount of pixel it changes
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
     * increases x value by instance variable.
     *
     * @param right amount of pixel it changes
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
     * decreases y value by instance variable.
     *
     * @param up amount of pixel it changes
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
     * increases y value by instance variable.
     *
     * @param down amount of pixel it changes
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

    /**
     * Calculates the distance from position to {@param other} by using the theorem of pythagoras.
     *
     * @param other other position
     * @return the distance between two position
     */
    public double distance(Position other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));

    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ')';
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Position o = (Position) other;
        return Double.compare(y, o.y) == 0 && Double.compare(x, o.x) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public Position clone() {
        Position copy = null;
        try {
            copy = (Position) super.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return copy;
    }


    @Override
    public int compareTo(Position o) {
        Position comparator = new Position();
        return Double.compare(this.distance(comparator), o.distance(comparator));
    }
}

