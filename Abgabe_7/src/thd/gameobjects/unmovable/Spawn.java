package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

import java.awt.Color;

public class Spawn extends GameObject {
    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public Spawn(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
    }

    @Override
    public void updateStatus() {
        if (position.x % 200 == 0) {
            gamePlayManager.spawn(new City(gameView, gamePlayManager, scaleFactor(), GameView.WIDTH));
        }
        if(position.x % 1000 == 0){
            gamePlayManager.spawn(new Rock(gameView,gamePlayManager));
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
