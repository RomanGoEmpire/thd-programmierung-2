package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.gameview.GameView;

import java.util.Random;

/**
 * Is an Object which moves in the upper half of the screen and tries to shoot the Rover.
 */
public class FlyingObject extends GameObject {

    protected Position targetPosition;
    protected boolean isAtTargetPosition;
    protected Random random;

    /**
     * Initializes the FlyingObject.
     *
     * @param gameView is the window it is displayed.
     */
    public FlyingObject(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        random = new Random();
    }

    protected void generateRandomSpawnPosition() {
    }

    protected void calculateRandomTargetPosition() {
    }

    protected void goToTargetPosition() {
        double distance = position.distance(targetPosition);
        if (distance >= speedInPixel) {
            position.right((targetPosition.x - position.x) / distance * speedInPixel);
            position.down((targetPosition.y - position.y) / distance * speedInPixel);
        } else {
            isAtTargetPosition = true;
        }
    }
}
