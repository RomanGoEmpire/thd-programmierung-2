package thd.game.managers;

import thd.gameview.GameView;

public class GamePlayManager {

    private final GameView gameView;
    private GameObjectManager gameObjectManager;
    private int oldtime;

    GamePlayManager(GameView gameView) {
        this.gameView = gameView;
        oldtime = 0;
    }

    void updateGameplay() {
        spawnAndDestroyUFOs();
    }

    public void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    void spawnAndDestroyUFOs() {
        System.out.println(gameView.getGameTimeInMilliseconds());
        if (gameView.getGameTimeInMilliseconds() / 1000 > oldtime) {
            gameObjectManager.spawn(gameObjectManager.createUfo());
            oldtime = gameView.getGameTimeInMilliseconds() / 1000;
        }
        if (gameView.getGameTimeInMilliseconds() / 1500 > oldtime) {
            //add destroy
        }
    }
}
