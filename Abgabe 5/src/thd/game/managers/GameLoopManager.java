package thd.game.managers;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.ufo.Ufo;
import thd.gameview.GameView;

import java.util.ArrayList;

/**
 * The Class creates a window with help of the {@param Gameview}.
 * In the window the game is displayed.
 */
public class GameLoopManager {

    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private final InputManager inputManager;
    private final ArrayList<GameObject> createdGameObjects;

    /**
     * Initializes the window.
     */
    public GameLoopManager() {
        gameView = new GameView();
        gameObjectManager = new GameObjectManager(gameView);
        inputManager = new InputManager(gameView, gameObjectManager.x);
        gameView.setWindowTitle("Moon Patrol");
        gameView.setStatusText("Gerloff Roman - Java Programmierung SS 2022");
        gameView.setWindowIcon("Icon2.png");
        createdGameObjects = new ArrayList<>();
    }

    /**
     * Runs the game.
     */
    public void startGame() {
        while (true) {
            updateGamePlay();
            inputManager.updateUserInputs();
            gameObjectManager.updateGameObjects();
            gameView.printCanvas();
        }
    }

    private void updateGamePlay() {
        if (gameView.getGameTimeInMilliseconds() / 1000 == 5) {
            Ufo newUfo = new Ufo(gameView);
            gameObjectManager.addGameObject(newUfo);
            createdGameObjects.add(newUfo);
        }
        if (gameView.getGameTimeInMilliseconds() / 1000 == 7) {
            for (GameObject g : createdGameObjects) {
                gameObjectManager.removeGameObject(g);
            }
        }
    }
}


