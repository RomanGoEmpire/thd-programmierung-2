package thd.game.managers;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.City;
import thd.gameobjects.movable.Rover;
import thd.gameobjects.movable.X;
import thd.gameobjects.movable.ufo.Triangle;
import thd.gameview.GameView;

import java.util.ArrayList;
import java.util.LinkedList;

class GameObjectManager {

    final X x;
    private final GameView gameView;
    private final LinkedList<GameObject> gameObjects;

    private final ArrayList<GameObject> toAdd;
    private final ArrayList<GameObject> toRemove;
    private int counter;

    GameObjectManager(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        gameObjects = new LinkedList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
        gameObjects.add(new City(gameView, gamePlayManager));
        gameObjects.add(new Triangle(gameView, gamePlayManager));
        gameObjects.add(new Rover(gameView, gamePlayManager));
        x = new X(gameView, gamePlayManager);
        counter = 1;

    }

    void updateGameObjects() {
        modifyGameObjectsList();
        gameView.addImageToCanvas("background.png", 0, 0, 1, 0);
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            gameObject.updatePosition();
            gameObject.addToCanvas();
        }
        x.updateStatus();
        x.addToCanvas();
        counter++;
    }

    /**
     * Adds object to gameObjects.
     *
     * @param gameObject is the object which gets added
     */
    void addGameObject(GameObject gameObject) {
        toAdd.add(gameObject);
    }

    /**
     * Removes object to gameObjects.
     *
     * @param gameObject is the object which gets removed
     */
    void removeGameObject(GameObject gameObject) {
        toRemove.add(gameObject);
    }

    private void modifyGameObjectsList() {
        gameObjects.addAll(toAdd);
        gameObjects.removeAll(toRemove);
        toAdd.clear();
        toRemove.clear();
    }
    void spawn(GameObject gameObject) {
        toAdd.add(gameObject);
    }

    void destroy(GameObject gameObject) {
        toRemove.add(gameObject);
    }
}
