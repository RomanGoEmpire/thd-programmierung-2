package thd.game.managers;

import thd.game.level.Level;
import thd.game.level.Level1;
import thd.game.level.Level2;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.*;
import thd.gameview.GameView;

/**
 * This class makes sure that all objects are spawning and getting destroyed afterwards.
 */

public class GamePlayManager {

    private final GameView gameView;
    private GameObjectManager gameObjectManager;

    private final LevelManager levelManager;

    private Level currentLevel;

    GamePlayManager(GameView gameView) {
        this.gameView = gameView;
        levelManager = new LevelManager(Level.Difficulty.STANDARD);
        currentLevel = levelManager.levels.getFirst();
    }

    void updateGamePlay() {
        if (!gameView.timerIsActive("level", this)) {
            gameView.activateTimer("level", this, 10000);
            if(currentLevel.name.equals("last")){
                levelManager.resetLevelCounter();
            }
            currentLevel = levelManager.nextLevel();
            setGameObjectManager(gameObjectManager);
        }
        gameObjectManager.moveWorld(2, 0);
    }

    private void initializeLevel() {
        for (GameObject o : gameObjectManager.getGameObjects()) {
            if (!(o instanceof Rover)) {
                destroy(o);
            }
        }
        if (currentLevel.getClass() == Level1.class) {

            gameObjectManager.addGameObject(gameObjectManager.rover);
            gameObjectManager.addGameObject(new Ufo(gameView, this, new Position(100, 100)));
            gameObjectManager.addGameObject(new Spawn(gameView, this, currentLevel.backgroundString));
        } else if (currentLevel.getClass() == Level2.class) {

            gameObjectManager.addGameObject(gameObjectManager.rover);
            gameObjectManager.addGameObject(new Triangle(gameView, this));
            gameObjectManager.addGameObject(new Spawn(gameView, this, currentLevel.backgroundString));
        }
    }

    void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
        initializeLevel();
    }
/*
    private void spawnAndDestroyUFOs(int timeBetweenSpawns) {
        if (!gameView.timerIsActive("spawn", this)) {
            gameView.activateTimer("spawn", this, timeBetweenSpawns);
            Ufo ufo = new Ufo(gameView, this, new Position(100, 100));
            spawn(ufo);
        }

    }

    private void spawnFloorBomb(int time) {
        if (!gameView.timerIsActive("floor-bomb", this)) {
            gameView.activateTimer("floor-bomb", this, time);
            FloorBomb floorBomb = new FloorBomb(gameView, this);
            spawn(floorBomb);
        }
    }

 */

    /**
     * adds the passed object to the List, which spawns all MoveAble objects.
     *
     * @param gameObject is the passed object
     */
    public void spawn(GameObject gameObject) {
        gameObjectManager.addGameObject(gameObject);
    }

    /**
     * adds the passed object to the List, which spawns all MoveAble objects.
     *
     * @param gameObject is the passed object
     */
    public void spawnBackground(GameObject gameObject) {
        gameObjectManager.addBackgroundGameObject(gameObject);
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
