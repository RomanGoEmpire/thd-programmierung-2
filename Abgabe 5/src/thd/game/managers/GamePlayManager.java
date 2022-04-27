package thd.game.managers;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.ufo.Ufo;
import thd.gameview.GameView;

import java.util.ArrayList;
import java.util.LinkedList;

public class GamePlayManager {

    private final GameView gameView;
    private GameObjectManager gameObjectManager;
    protected final LinkedList<GameObject> ufos;

    GamePlayManager(GameView gameView) {
        this.gameView = gameView;
        ufos = new LinkedList<>();
    }

    void updateGamePlay() {
        spawnAndDestroyUFOs();
    }

    public void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    void spawnAndDestroyUFOs() {
        final Ufo ufo = new Ufo(gameView, this, new Position(100, 100));
        ufos.add(ufo);

        if (!gameView.timerIsActive("spawn", this)) {
            gameView.activateTimer("spawn", this, 1000);
            gameObjectManager.spawn(ufos.get(ufos.size() - 1));
        }
        if (!gameView.timerIsActive("destroy", this)) {
            gameView.activateTimer("destroy", this, 1500);
            gameObjectManager.destroy(ufos.get(ufos.size() - 1));
        }
    }
}
