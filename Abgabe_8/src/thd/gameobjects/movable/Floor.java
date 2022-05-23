package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Floor.
 */
class Floor extends CollidableGameObject {
    /**
     * Crates a new GameObject.
     *
     * @param gameView        Window to show the GameObject on.
     * @param gamePlayManager Controls the gameplay.
     */
    public Floor(GameView gameView, GamePlayManager gamePlayManager,double offset) {
        super(gameView, gamePlayManager);
        position.x = GameView.WIDTH;
        position.y = GameView.HEIGHT - offset;
        width = 3;

    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 30;
        hitBoxHeight = 3;
        hitBoxOffsetX = 0;
        hitBoxOffsetY = 0;

    }

    @Override
    public void reactToCollision(CollidableGameObject other) {

    }

    @Override
    public void updateStatus() {
        if (position.x < -width) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.x, position.y, width, 30, 1, true, Color.green);
    }
}
