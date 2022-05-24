package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * Background.
 */
public class MovableBackground extends GameObject {

    private final double scaleFactor;
    private final double counterMovement;
    private final double positionToDestroy;
    private final String cityPicture;

    /**
     * Index for gameObjects.
     */
    public final int gameObjectIndex;

    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param scaleFactor     is the size.
     * @param positionX       is the position.
     * @param cityPicture     is the Png.
     */
    public MovableBackground(GameView gameView, GamePlayManager gamePlayManager, double scaleFactor, double positionX, String cityPicture) {
        super(gameView, gamePlayManager);
        position.x = positionX;
        this.scaleFactor = scaleFactor;

        if (cityPicture.equals("city.png")) {
            position.y = GameView.HEIGHT - calculateHeightOrWidth(644);
            positionToDestroy = calculateHeightOrWidth(1439);
        } else if (cityPicture.equals("canyon.png")) {
            position.y = GameView.HEIGHT - calculateHeightOrWidth(405);
            positionToDestroy = calculateHeightOrWidth(1440);
        } else {
            positionToDestroy = 0;
        }
        counterMovement = 1 - scaleFactor;
        this.cityPicture = cityPicture;
        gameObjectIndex = (int) (scaleFactor * 10) - 5;
    }

    @Override
    public void updateStatus() {
        position.x += counterMovement;
        if (position.x < -positionToDestroy) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(cityPicture, position.x, position.y, scaleFactor, 0);
    }

    private double calculateHeightOrWidth(int pictureHeightOrWidth) {
        return pictureHeightOrWidth * scaleFactor;
    }
}
