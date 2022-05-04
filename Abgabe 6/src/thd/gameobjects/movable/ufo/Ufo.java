package thd.gameobjects.movable.ufo;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.BulletUP;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.FlyingObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

/**
 * * Ufo is an enemy which has the goal to shoot the Rover.
 * It moves in the upper half of the screen.
 */
public class Ufo extends FlyingObject implements AutoMovable {

    private int counterForCurve;
    private boolean goingLeftToRight;

    /**
     * an Ufo is generated.
     *
     * @param gameView        is the window in which it gets displayed
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param position        is the position the Ufo should spawn
     */

    public Ufo(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position = position;
        calculateRandomTargetPosition();
        speedInPixel = 2;
        size = 0.25;
        width = 80;
        height = 40; //change this
        isAtTargetPosition = false;
        goingLeftToRight = true;
        counterForCurve = 0;
    }

    @Override
    public void updateStatus() {

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
     */
    @Override
    public void updatePosition() {
        if (isAtTargetPosition) {
            if (counterForCurve < 20) {
                executeCurve(counterForCurve);
                counterForCurve++;
            } else {
                calculateRandomTargetPosition();
                goingLeftToRight = targetPosition.x >= position.x;
                counterForCurve = 0;
                isAtTargetPosition = false;
            }
        } else {
            goToTargetPosition();
        }
    }

    private void executeCurve(int counter) {
        if (goingLeftToRight) {
            position.left(Math.sin(counter / 10d));
        } else {
            position.right(Math.sin(counter / 10d));
        }
        position.up(Math.cos(counter / 10d));
    }

    @Override
    protected void calculateRandomTargetPosition() {
        targetPosition = new Position(random.nextInt(700), random.nextInt(50) + 100);
        while (position.distance(targetPosition) < 200) {
            targetPosition = new Position(random.nextInt(700), random.nextInt(50) + 100);
        }
        targetPosition = new Position(targetPosition.x, targetPosition.y);
    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 60;
        hitBoxHeight = 25;
        hitBoxOffsetX = 0;
        hitBoxOffsetY = 20;
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == BulletUP.class) {
            gamePlayManager.destroy(this);
        }
    }
}
