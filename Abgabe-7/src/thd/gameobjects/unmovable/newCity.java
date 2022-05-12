package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

public class newCity extends GameObject {

    private double scaleFactor;

    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public newCity(GameView gameView, GamePlayManager gamePlayManager, double scaleFactor) {
        super(gameView, gamePlayManager);
        position.x = 0;
        position.y = 0;
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void updateStatus() {
        position.x += 1 - scaleFactor;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("city.png", position.x, GameView.HEIGHT - calculateHeightOrWidth(scaleFactor, 644), scaleFactor, 0);
    }

    private double calculateHeightOrWidth(double scaleFactor, int pictureHeightOrWidth) {
        return pictureHeightOrWidth * scaleFactor;
    }
}
