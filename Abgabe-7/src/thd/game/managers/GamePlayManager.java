package thd.game.managers;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.FloorBomb;
import thd.gameobjects.movable.Rock;
import thd.gameobjects.movable.ufo.Ufo;
import thd.gameview.GameView;

import java.util.LinkedList;

/**
 * This class makes sure that all objects are spawning and getting destroyed afterwards.
 */

public class GamePlayManager {

    private final GameView gameView;
    private final LinkedList<GameObject> objects;
    private GameObjectManager gameObjectManager;

    GamePlayManager(GameView gameView) {
        this.gameView = gameView;
        objects = new LinkedList<>();
    }

    void updateGamePlay() {
        spawnAndDestroyUFOs();
    }

    void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    private void spawnAndDestroyUFOs() {
        if (!gameView.timerIsActive("spawn", this)) {
            gameView.activateTimer("spawn", this, 1000);
            Ufo ufo = new Ufo(gameView, this, new Position(100, 100));
            objects.add(ufo);
            spawn(objects.get(objects.size() - 1));
        }
        if (!gameView.timerIsActive("floor-bomb", this)) {
            gameView.activateTimer("floor-bomb", this, 10000);
            FloorBomb floorBomb = new FloorBomb(gameView, this);
            objects.add(floorBomb);
            spawn(objects.get(objects.size() - 1));
        }
        if (!gameView.timerIsActive("Rock", this)) {
            gameView.activateTimer("Rock", this, 8000);
            Rock rock = new Rock(gameView, this);
            objects.add(rock);
            spawn(objects.get(objects.size() - 1));
        }
    }

    /**
     * adds the passed object to the List, which spawns all MoveAble objects.
     *
     * @param gameObject is the passed object
     */
    public void spawn(GameObject gameObject) {
        gameObjectManager.addGameObject(gameObject);
    }

    /**
     * adds the passed object to the List, which destroys all MoveAble objects.
     *
     * @param gameObject is the passed object
     */
    public void destroy(GameObject gameObject) {
        gameObjectManager.removeGameObject(gameObject);
    }
}
