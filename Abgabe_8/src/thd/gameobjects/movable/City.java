package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

/**
 * City which is displayed in the background.
 */
public class City extends GameObject {

    private final double scaleFactor;
    private final double counterMovement;
    private final double positionToDestroy;
    private final String cityPicture;

    public final int gameObjectIndex;

    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param scaleFactor     is the size.
     * @param positionX       is the position.
     */
    public City(GameView gameView, GamePlayManager gamePlayManager, double scaleFactor, double positionX) {
        super(gameView, gamePlayManager);
        position.x = positionX;
        this.scaleFactor = scaleFactor;
        position.y = GameView.HEIGHT - calculateHeightOrWidth(644);
        counterMovement = 1 - scaleFactor;
        positionToDestroy = calculateHeightOrWidth(1439);
        cityPicture = random.nextBoolean() ? "city.png" : "city2.png";
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
