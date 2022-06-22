package thd.game.managers;

import thd.game.level.Level;
import thd.game.level.Level1;
import thd.game.level.Level2;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.MovableBackground;
import thd.gameobjects.movable.Triangle;
import thd.gameobjects.movable.Ufo;
import thd.gameobjects.unmovable.Stars;
import thd.gameview.GameView;

/**
 * This class makes sure that all objects are spawning and getting destroyed afterwards.
 */

public class GamePlayManager {

    private final GameView gameView;
    /**
     * lets the game running.
     */
    boolean gameOver;
    /**
     * resets the Game if true.
     */
    public boolean isGameOver;

    private GameObjectManager gameObjectManager;

    private final LevelManager levelManager;

    private Level currentLevel;

    GamePlayManager(GameView gameView) {
        this.gameView = gameView;
        levelManager = new LevelManager(Level.Difficulty.STANDARD);
        currentLevel = levelManager.levels.getFirst();
        gameOver = false;
        isGameOver = false;
    }

    void updateGamePlay() {
        if (gameOver()) {
            if (!gameView.alarmIsSet("gameOver", this)) {
                gameView.setAlarm("gameOver", this, 2000);
                gameObjectManager.overlay.showMessage("Game Over", 2);
            } else if (gameView.alarm("gameOver", this)) {
                initializeGame();
            }
        } else {
            if (!gameView.timerIsActive("level", this)) {
                gameView.activateTimer("level", this, 5000);
                if (currentLevel.name.equals("District 3")) {
                    levelManager.resetLevelCounter();
                }
                currentLevel = levelManager.nextLevel();
                initializeLevel();
            }
            gameObjectManager.moveWorld(2, 0);
        }
    }

    private boolean gameOver() {
        return isGameOver;
    }

    private void initializeGame() {
        isGameOver = false;
        destroyAll();
        initializeLevel();
        currentLevel = levelManager.levels.getFirst();
        levelManager.resetLevelCounter();
    }

    private void initializeLevel() {
        destroyAll();
        gameObjectManager.addGameObject(new Stars(gameView, this));
        gameObjectManager.addGameObject(gameObjectManager.rover);
        gameObjectManager.addGameObject(gameObjectManager.overlay);
        gameObjectManager.overlay.showMessage(currentLevel.name, 2);
        if (currentLevel.getClass() == Level1.class) {
            gameObjectManager.addGameObject(new MovableBackground(gameView, this, "CityNew.png"));
            gameObjectManager.addGameObject(new Ufo(gameView, this, new Position(100, 100)));
        } else if (currentLevel.getClass() == Level2.class) {
            gameObjectManager.addGameObject(new MovableBackground(gameView, this, "CanyonNew.png"));
            gameObjectManager.addGameObject(new Triangle(gameView, this));
        }
    }

    private void destroyAll() {
        for (GameObject o : gameObjectManager.gameObjects) {
            destroy(o);
        }
    }

    void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
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
