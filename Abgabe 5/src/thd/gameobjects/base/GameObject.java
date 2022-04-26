package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.gameview.GameView;

/**
 * Upperclass for game objects which will get displayed on {@link GameView}.
 */
public class GameObject {
    /**
     * window the object is displayed in.
     */
    protected final GameView gameView;

    protected final GamePlayManager gamePlayManager;

    /**
     * orientation of the object.
     */
    public double rotation;
    /**
     * Position of the object.
     */
    protected Position position;
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
     * @param gameView is the window it is displayed.
     */
    public GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        position = new Position(0, 0);
    }

    /**
     * The object position is updated.
     */
    public void updatePosition() {
    }

    /**
     * The status of the object is updated.
     */
    public void updateStatus() {

    }

    /**
     * The object is added to the Canvas.
     */
    public void addToCanvas() {
    }

    /**
     * Gets the position of the object.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }
}
