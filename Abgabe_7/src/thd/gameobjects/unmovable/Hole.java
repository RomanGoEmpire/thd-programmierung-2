package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

public class Hole extends CollidableGameObject {
    /**
     * Crates a new GameObject.
     *
     * @param gameView        Window to show the GameObject on.
     * @param gamePlayManager Controls the gameplay.
     */
    public Hole(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        width = 80;
        height = 20;
    }

    @Override
    protected void initializeHitbox() {

    }

    @Override
    public void reactToCollision(CollidableGameObject other) {

    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void addToCanvas() {

    }
}
