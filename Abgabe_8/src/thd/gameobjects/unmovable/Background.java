package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

public class Background extends GameObject {
    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public Background(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
    }

    @Override
    public void updateStatus() {
        if (position.x < 0) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void addToCanvas() {
         gameView.addImageToCanvas("background.png",0,0,1,0);
    }
}
