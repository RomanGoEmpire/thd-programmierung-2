package thd.gameobjects.movable;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

/**
 * Ufo is an enemy which has the goal to shoot the Rover.
 */

public class Ufo extends GameObject {

    private boolean changeDirections;

    /**
     * an Ufo is generated.
     *
     * @param gameView is the window in which it gets displayed
     */

    public Ufo(GameView gameView) {
        super(gameView);
        position = new Position(0, 100);
        speedInPixel = 1;
        size = 0.3;
        width = 80;
        changeDirections = false;
    }

    /**
     * The Ufo is added to the Canvas.
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("Ufo.png", position.x, position.y, size, rotation);
    }

    /**
     * Starts a game and updates the picture.
     *
     * @param counter determines how much the position is changed
     */
    public void updatePosition(int counter) {
        if (position.x > 900 - width) {
            changeDirections = true;
        } else if (position.x <= 0) {
            changeDirections = false;
        }
        if (!changeDirections) {
            position.right(speedInPixel);
        } else {
            position.left(speedInPixel);
        }
        position.down(Math.sin(Math.sqrt(Math.pow(counter * 0.01, Math.PI))));
    }

}
