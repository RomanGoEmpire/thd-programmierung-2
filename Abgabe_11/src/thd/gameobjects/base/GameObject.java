package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.gameview.GameView;

import java.util.Random;

/**
 * Upperclass for game objects which will get displayed on {@link GameView}.
 */
public abstract class GameObject {
    /**
     * window the object is displayed in.
     */
    protected final GameView gameView;

    protected final GamePlayManager gamePlayManager;
    /**
     * Position of the object.
     */
    protected final Position position;
    /**
     * orientation of the object.
     */
    protected final Random random;
    protected double rotation;
    /**
     * size of the object. 1 is original scale.
     */
    protected double size;
    /**
     * speed of the object.
     */
    protected double speedInPixel;
    /**
     * width of the object.
     */
    protected double width;
    /**
     * height of the object.
     */
    protected double height;

    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        random = new Random();
        position = new Position(0, 0);
    }

    /**
     * The status of the object is updated.
     */
    public abstract void updateStatus();

    /**
     * The object is added to the Canvas.
     */
    public abstract void addToCanvas();

    /**
     * Gets the position of the object.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * corrects position.
     *
     * @param shiftX amount of shift in x direction.
     * @param shiftY amount of shift in y direction.
     */
    public void worldHasMoved(double shiftX, double shiftY) {
        position.x -= shiftX;
        position.y -= shiftY;
    }
}
