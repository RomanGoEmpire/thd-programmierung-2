package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Bomb which is on the Floor. If the Rover touches the bomb he looses a Life.w
 */
class FloorBomb extends CollidableGameObject implements AutoMovable {
    /**
     * Crates a new GameObject.
     *
     * @param gameView        Window to show the GameObject on.
     * @param gamePlayManager Controls the gameplay.
     */
    FloorBomb(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.x = GameView.WIDTH;
        position.y = GameView.HEIGHT - 10;
        speedInPixel = 4;
    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 20;
        hitBoxHeight = 5;
        hitBoxOffsetX = 0;
        hitBoxOffsetY = 0;
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == Rover.class) {
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
        gameView.addRectangleToCanvas(position.x, position.y, 20, 5, 0, true, Color.yellow);
    }
}
