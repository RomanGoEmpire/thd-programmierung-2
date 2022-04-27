package thd.game.managers;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.ufo.Ufo;
import thd.gameview.GameView;

import java.util.LinkedList;
import java.util.Random;

public class GamePlayManager {

    private final GameView gameView;
    private final Random random;
    private GameObjectManager gameObjectManager;
    protected final LinkedList<GameObject> ufos;

    GamePlayManager(GameView gameView) {
        this.gameView = gameView;
        ufos = new LinkedList<>();
        random = new Random();
    }

    void updateGamePlay() {
        spawnAndDestroyUFOs();
    }

    public void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    void spawnAndDestroyUFOs() {
        if (!gameView.timerIsActive("spawn", this)) {
            gameView.activateTimer("spawn", this, 1000);
            Ufo ufo = new Ufo(gameView, this, new Position(100, 100));
            ufos.add(ufo);
            spawn(ufos.get(ufos.size() - 1));
        }
        if (!gameView.timerIsActive("destroy", this)) {
            gameView.activateTimer("destroy", this, 1500);
            destroy(ufos.get(random.nextInt(ufos.size())));
        }
    }

    public void spawn(GameObject gameObject) {
        gameObjectManager.addGameObject(gameObject);
    }

    public void destroy(GameObject gameObject) {
        gameObjectManager.removeGameObject(gameObject);
    }
}
