package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * temporary.
 */
public class TemporaryRock extends CollidableGameObject  {
    /**
     * Crates a new GameObject.
     *
     * @param gameView        Window to show the GameObject on.
     * @param gamePlayManager Controls the gameplay.
     */
    public TemporaryRock(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.x = 100;
        position.y = 500;
    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 50;
        hitBoxHeight = 50;
        hitBoxOffsetX = 0;
        hitBoxOffsetY = 0;
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {

    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.x,position.y,50,50,1,true, Color.green);
    }
}
