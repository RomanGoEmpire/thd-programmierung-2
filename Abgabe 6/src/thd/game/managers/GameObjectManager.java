package thd.game.managers;

import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Rover;
import thd.gameobjects.movable.ufo.Triangle;
import thd.gameobjects.unmovable.City;
import thd.gameview.GameView;

import java.util.ArrayList;
import java.util.LinkedList;

class GameObjectManager {

    private final GameView gameView;
    private final LinkedList<GameObject> gameObjects;
    private final ArrayList<GameObject> toAdd;
    private final ArrayList<GameObject> toRemove;
    Rover rover;

    GameObjectManager(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        gameObjects = new LinkedList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
        rover = new Rover(gameView, gamePlayManager);
        gameObjects.add(new City(gameView, gamePlayManager));
        gameObjects.add(new Triangle(gameView, gamePlayManager));
        gameObjects.add(rover);

    }

    void updateGameObjects() {
        modifyGameObjectsList();
        gameView.addImageToCanvas("background.png", 0, 0, 1, 0);
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            if (gameObject instanceof AutoMovable) {
                ((AutoMovable) gameObject).updatePosition();
            }
            gameObject.addToCanvas();
        }
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

        if (gameObjects.size() > 300) {
            throw new TooManyGameObjectsException("Too Many Objects");
        }
    }
}
