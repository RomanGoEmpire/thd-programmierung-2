package thd.gameobjects.movable.ufo;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.movable.BulletUP;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.FlyingObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

/**
 * * Triangle is an enemy which has the goal to shoot the Rover.
 * It moves in the upper half of the screen.
 */
public class Triangle extends FlyingObject implements AutoMovable {

    private int counterForRotation;

    /**
     * a Triangle-Ufo is generated.
     *
     * @param gameView        is the window in which it gets displayed
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public Triangle(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 3;
        generateRandomSpawnPosition();
        calculateRandomTargetPosition();
        isAtTargetPosition = false;
        counterForRotation = 0;
    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 48;
        hitBoxHeight = 48;
        hitBoxOffsetX = 15;
        hitBoxOffsetY = 15;
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == BulletUP.class) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("circle.png", position.x, position.y, 0.08, rotation);


    }

    /**
     * The object position is updated.
     */
    @Override
    public void updatePosition() {
        if (isAtTargetPosition) {
            if (counterForRotation < 10) {
                rotation += counterForRotation * 2;
                counterForRotation += 1;
            } else {
                isAtTargetPosition = false;
                counterForRotation = 0;
                calculateRandomTargetPosition();
            }
        } else {
            goToTargetPosition();
        }
    }

    @Override
    protected void generateRandomSpawnPosition() {
        position.x = random.nextInt((int) (GameView.WIDTH - width));
        position.y = -100;
    }

    @Override
    protected void calculateRandomTargetPosition() {
        targetPosition = new Position(random.nextInt(959), random.nextInt(50) + 100);
        while (position.distance(targetPosition) < 200) {
            targetPosition = new Position(random.nextInt(959), random.nextInt(50) + 100);
        }
        targetPosition = new Position(targetPosition.x, targetPosition.y);
    }
}
