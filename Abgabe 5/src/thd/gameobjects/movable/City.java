package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;
import java.util.Arrays;
import java.util.Random;

/**
 * Part of the background which moves is generated in different levels.
 * Smaller buildings are more in background and move slower that the bigger ones.
 * Is displayed in {@link GameView}
 */
public class City extends GameObject {

    private final Random random;
    private final int[] cityScaleFactors;
    private final Position[] cityPosition;
    private final int[] cityXStartingPosition;

    /**
     * Initializes the City.
     *
     * @param gameView window it is displayed.
     */
    public City(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        random = new Random();
        int amountOfHouses = 10;
        cityXStartingPosition = new int[amountOfHouses];
        cityPosition = new Position[amountOfHouses];
        cityScaleFactors = new int[amountOfHouses];
        fillArrayWithRandomNumbers(cityXStartingPosition, -600, 1000);
        fillArrayWithRandomNumbers(cityScaleFactors, 300, 644);
        Arrays.sort(cityScaleFactors);
        createCityPosition();
    }

    @Override
    public void updatePosition() {
        for (int i = 0; i < cityPosition.length; i++) {
            if (cityPosition[i].x <= -calculateHeightOrWidth(0.664, 1439)) {
                cityPosition[i].x = 960;
            }
            cityPosition[i].left(cityScaleFactors[i] * 0.0015);
        }
    }

    @Override
    public void addToCanvas() {
        for (int i = 0; i < cityPosition.length; i++) {
            if (i % 2 == 0) {
                gameView.addImageToCanvas("city.png", cityPosition[i].x, cityPosition[i].y, cityScaleFactors[i] / 1000d, 0);
            } else {
                gameView.addImageToCanvas("city2.png", cityPosition[i].x, cityPosition[i].y, cityScaleFactors[i] / 1000d, 0);
            }
        }
    }

    private void createCityPosition() {
        for (int i = 0; i < cityPosition.length; i++) {
            cityPosition[i] = new Position(cityXStartingPosition[i], GameView.HEIGHT - calculateHeightOrWidth(cityScaleFactors[i] / 1000d, 644));
        }
    }

    private void fillArrayWithRandomNumbers(int[] array, int min, int max) {
        Arrays.setAll(array, i -> random.nextInt(max - min + 1) + min);
    }

    private double calculateHeightOrWidth(double scaleFactor, int pictureHeightOrWidth) {
        return pictureHeightOrWidth * scaleFactor;
    }
}
