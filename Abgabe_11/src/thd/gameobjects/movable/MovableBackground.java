package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * Background.
 */
public class MovableBackground extends GameObject {


    private final String city;

    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param city            is the name of the png file.
     */
    public MovableBackground(GameView gameView, GamePlayManager gamePlayManager, String city) {
        super(gameView, gamePlayManager);
        this.city = city;
        initializePositionY();

    }

    private void initializePositionY() {
        int size = 0;
        if (city.equals("CityNew.png")) {
            size = 644;
        } else if (city.equals("CanyonNew.png")) {
            size = 405;
        }
        position.y = GameView.HEIGHT - size;
    }

    @Override
    public void updateStatus() {
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(city, position.x, position.y, 1, 0);
    }
}

