package thd.game.managers;

import thd.gameview.GameView;

/**
 * The Class creates a window with help of the {@param Gameview}.
 * In the window the game is displayed.
 */

public class GameLoopManager {

    private final GameView gameView;
    private final GameObjectManager gameObjectManager;

    /**
     * Initializes the window.
     */

    public GameLoopManager() {
        gameView = new GameView();
        gameObjectManager = new GameObjectManager(gameView);

        gameView.setWindowTitle("Moon Patrol");
        gameView.setStatusText("Gerloff Roman - Java Programmierung SS 2022");
        gameView.setWindowIcon("Icon2.png");
    }

    /**
     * Runs the game.
     */

    public void startGame() {
        while (true) {
            gameObjectManager.updateGameObjects();
            gameView.printCanvas();
        }
    }
}

