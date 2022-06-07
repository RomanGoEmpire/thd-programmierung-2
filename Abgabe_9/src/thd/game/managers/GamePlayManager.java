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
     * is true when the game is over.
     */
    boolean gameOver;
    private GameObjectManager gameObjectManager;

    private final LevelManager levelManager;

    private Level currentLevel;

    GamePlayManager(GameView gameView) {
        this.gameView = gameView;
        levelManager = new LevelManager(Level.Difficulty.STANDARD);
        currentLevel = levelManager.levels.getFirst();
        gameOver = false;
    }

    void updateGamePlay() {
        if (!gameView.timerIsActive("level", this)) {
            gameView.activateTimer("level", this, 10000);
            if (currentLevel.name.equals("last")) {
                levelManager.resetLevelCounter();
            }
            currentLevel = levelManager.nextLevel();
            initializeLevel();
        }
        gameObjectManager.moveWorld(2, 0);
    }

    private void initializeLevel() {
        for (GameObject o : gameObjectManager.gameObjects) {
            destroy(o);
        }
        gameObjectManager.addGameObject(new Stars(gameView, this));
        gameObjectManager.addGameObject(gameObjectManager.rover);
        if (currentLevel.getClass() == Level1.class) {
            gameObjectManager.addGameObject(new MovableBackground(gameView,this,"CityNew.png"));
            gameObjectManager.addGameObject(new Ufo(gameView, this, new Position(100, 100)));
        } else if (currentLevel.getClass() == Level2.class) {
            gameObjectManager.addGameObject(new MovableBackground(gameView, this, "CanyonNew.png"));
            gameObjectManager.addGameObject(new Triangle(gameView, this));
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
