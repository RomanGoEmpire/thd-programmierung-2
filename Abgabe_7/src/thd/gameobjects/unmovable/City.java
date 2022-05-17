package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

public class City extends GameObject {

    private final double scaleFactor;
    private final double counterMovement;
    private final double positionToDestroy;
    private final String cityPicture;

    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public City(GameView gameView, GamePlayManager gamePlayManager, double scaleFactor, double positionX) {
        super(gameView, gamePlayManager);
        position.x = positionX;
        this.scaleFactor = scaleFactor;
        position.y = GameView.HEIGHT - calculateHeightOrWidth(scaleFactor, 644);
        counterMovement = 1 - scaleFactor;
        positionToDestroy = calculateHeightOrWidth(scaleFactor, 1439);
        cityPicture = random.nextBoolean() ? "city.png" : "city2.png";
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

    private double calculateHeightOrWidth(double scaleFactor, int pictureHeightOrWidth) {
        return pictureHeightOrWidth * scaleFactor;
    }
}
