package thd.game.managers;

import thd.gameview.GameView;

/**
 * The Class creates a window with help of the {@param Gameview}.
 * In the window the game is displayed.
 */
public class GameLoopManager {

    private final GameView gameView;
    private final GamePlayManager gamePlayManager;
    private final GameObjectManager gameObjectManager;
    private final InputManager inputManager;

    /**
     * Initializes the window.
     */
    public GameLoopManager() {
        gameView = new GameView();
        gamePlayManager = new GamePlayManager(gameView);
        gameObjectManager = new GameObjectManager(gameView, gamePlayManager);
        gamePlayManager.setGameObjectManager(gameObjectManager);
        inputManager = new InputManager(gameView, gameObjectManager.x);
        gameView.setWindowTitle("Moon Patrol");
        gameView.setStatusText("Gerloff Roman - Java Programmierung SS 2022");
        gameView.setWindowIcon("Icon2.png");
    }

    /**
     * Runs the game.
     */
    public void startGame() {
        while (true) {
            gamePlayManager.updateGameplay();
            inputManager.updateUserInputs();
            gameObjectManager.updateGameObjects();
            gameView.printCanvas();
        }
    }
}


