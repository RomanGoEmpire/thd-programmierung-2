package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * Unmovable Background.
 */
public class Stars extends GameObject {
    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public Stars(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.x = 0;
        position.y = 0;
    }

    @Override
    public void updateStatus() {
    }

    @Override
    public void addToCanvas() {
         gameView.addImageToCanvas("background.png",0,0,1,0);
    }
}
