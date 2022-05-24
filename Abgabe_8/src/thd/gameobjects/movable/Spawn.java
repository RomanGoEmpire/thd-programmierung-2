package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Original 0,0.
 */
public class Spawn extends GameObject {
    private final String cityImage;

    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param cityImage       is the name of the Picture.
     */
    public Spawn(GameView gameView, GamePlayManager gamePlayManager, String cityImage) {
        super(gameView, gamePlayManager);
        this.cityImage = cityImage;
    }

    @Override
    public void updateStatus() {
        if (position.x % 2 == 0) {
            double offset = 3;
            gamePlayManager.spawn(new Floor(gameView, gamePlayManager, offset));
        }
        if (position.x % 400 == 0) {
            gamePlayManager.spawnBackground(new MovableBackground(gameView, gamePlayManager, scaleFactor(), GameView.WIDTH, cityImage));
        }
        if (position.x % 1000 == 0) {
            gamePlayManager.spawn(new Rock(gameView, gamePlayManager));
        }
    }

    private double scaleFactor() {
        return random.nextDouble(0.5, 1);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(Math.round(position.x) + "," + Math.round(position.y), 0, 20, 20, Color.white, 0);
    }
}
