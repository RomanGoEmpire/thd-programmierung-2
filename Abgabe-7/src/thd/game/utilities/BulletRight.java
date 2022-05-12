package thd.game.utilities;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.Rock;
import thd.gameobjects.movable.Rover;
import thd.gameview.GameView;

import java.awt.*;

/**
 * BulletRight gets shot by the Rover. It moves in the right direction.
 */
public class BulletRight extends CollidableGameObject implements AutoMovable {

    private final Rover rover;
    private final double startPoint;

    /**
     * Initializes the BulletRight.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param position        is the position the Bullet should spawn
     * @param rover           helps the Bullet to get destroyed at the right position
     */
    public BulletRight(GameView gameView, GamePlayManager gamePlayManager, Position position, Rover rover) {
        super(gameView, gamePlayManager);
        this.position.x = position.x + 100;
        this.position.y = position.y;
        this.rover = rover;
        position.x += 110;
        startPoint = position.x;
        speedInPixel = 3;
        width = 150;
    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 15;
        hitBoxHeight = 15;
        hitBoxOffsetX = 37;
        hitBoxOffsetY = 8;

    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == Rock.class) {
            gamePlayManager.destroy(this);
            rover.allowedToShoot = true;
        }
    }


    @Override
    public void updateStatus() {
        if (position.x - startPoint > width) {
            gamePlayManager.destroy(this);
            rover.allowedToShoot = true;
        }
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("-O", position.x, position.y, 30, Color.RED, 0);
    }
}
