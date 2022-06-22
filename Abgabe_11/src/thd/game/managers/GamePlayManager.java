package thd.game.managers;

import thd.game.level.Level;
import thd.game.level.Level1;
import thd.game.level.Level2;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.Stars;
import thd.gameview.GameView;

import java.util.Random;

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
    private GameObject[] enemies;

    GamePlayManager(GameView gameView) {
        this.gameView = gameView;
        levelManager = new LevelManager(Level.Difficulty.STANDARD);
        currentLevel = levelManager.levels.getFirst();
        gameOver = false;
        isGameOver = false;


        enemies = new GameObject[]{
                new Triangle(gameView, this),
                new Ufo(gameView, this, new Position(100, 100)),
                new Rock(gameView, this),
                new FloorBomb(gameView, this)};
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
                gameView.activateTimer("level", this, 60000);
                if (currentLevel.name.equals("District 3")) {
                    levelManager.resetLevelCounter();
                }
                currentLevel = levelManager.nextLevel();
                initializeLevel();
            }
            gameObjectManager.moveWorld(2, 0);
            spawnRandomEnemies();
        }
    }

    private boolean gameOver() {
        return isGameOver;
    }

    private void initializeGame() {
        Level.Difficulty difficulty = FileManager.readDifficultyFromDisc();

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
        gameObjectManager.rover.setAllowedToShoot(true);
        gameObjectManager.addGameObject(gameObjectManager.overlay);
        gameObjectManager.overlay.showMessage(currentLevel.name, 2);
        if (currentLevel.getClass() == Level1.class) {
            gameObjectManager.addGameObject(new MovableBackground(gameView, this, "CityNew.png"));
            gameObjectManager.addGameObject(new Ufo(gameView, this, new Position(100, 100)));
            gameObjectManager.addGameObject(new Triangle(gameView,this));

        } else if (currentLevel.getClass() == Level2.class) {
            gameObjectManager.addGameObject(new MovableBackground(gameView, this, "CanyonNew.png"));
            gameObjectManager.addGameObject(new Triangle(gameView, this));
        }
    }

    private void spawnRandomEnemies() {
        if (!gameView.timerIsActive("spawnEnemy", this)) {
            gameView.activateTimer("spawnEnemy", this, 5000);
            System.out.println("test");
            //gameObjectManager.addGameObject(enemies[(int) (Math.random() * 0)]);
            Random random = new Random();
            if(random.nextBoolean()){
                gameObjectManager.addGameObject(new Triangle(gameView,this));
            }else{
                gameObjectManager.addGameObject(new Ufo(gameView,this,new Position(100,100)));
            }
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
