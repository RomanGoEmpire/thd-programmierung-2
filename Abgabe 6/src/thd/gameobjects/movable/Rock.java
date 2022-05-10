package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.BulletRight;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Rock which is in the way of the Rover. It can be destroyed by the BulletRight.
 */
public class Rock extends CollidableGameObject implements AutoMovable {


    /**
     * Crates a new GameObject.
     *
     * @param gameView        Window to show the GameObject on.
     * @param gamePlayManager Controls the gameplay.
     */
    public Rock(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        width = 40;
        height = 40;
        position.x = GameView.WIDTH;
        position.y = GameView.HEIGHT - 50;
        speedInPixel = 3;
    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 40;
        hitBoxHeight = 40;
        hitBoxOffsetX = 0;
        hitBoxOffsetY = 0;
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == Rover.class || other.getClass() == BulletRight.class) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void updateStatus() {
        if (position.x < 0) {
            gamePlayManager.destroy(this);
        }

    }

    @Override
    public void updatePosition() {
        position.left(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.x, position.y, width, height, 0, true, Color.GRAY);
    }
}
