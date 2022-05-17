package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Bulletdown gets shot by the Ufo. If it hits the Rover it will lose a life.
 */

public class BulletDown extends CollidableGameObject implements AutoMovable {

    /**
     * Initializes the BulletRight.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param position        is the position the Bullet should spawn
     */
    public BulletDown(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position.x = position.x;
        this.position.y = position.y;
        speedInPixel = 1;
        width = 150;
    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 15;
        hitBoxHeight = 15;
        hitBoxOffsetX = 0;
        hitBoxOffsetY = 0;
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == Rover.class || other.getClass() == BulletUP.class) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void updateStatus() {
        if (position.y > GameView.WIDTH) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void updatePosition() {
        position.down(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.x, position.y, 15, 15, 0, true, Color.yellow);
    }
}

