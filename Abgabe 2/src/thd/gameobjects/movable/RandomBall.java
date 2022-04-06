package thd.gameobjects.movable;

import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;
import java.util.Random;

public class RandomBall {

    private final Random random;

    private final Position targetPosition;
    private final Position randomBallPosition;
    private final GameView gameView;
    private final int speedInPixel;

    public RandomBall(GameView gameView) {
        this.gameView = gameView;
        random = new Random();
        randomBallPosition = new Position(0, 0);
        targetPosition = new Position(800, 200);
        speedInPixel = 4;
    }

    public void addToCanvas(double size) {
        gameView.addOvalToCanvas(randomBallPosition.x, randomBallPosition.y, size, size, 2, true, Color.yellow);
        gameView.addOvalToCanvas(targetPosition.x, targetPosition.y, size, size, 2, false, Color.white);
    }

    public void updatePosition() {
        double distance = randomBallPosition.distance(targetPosition);
        if (distance >= speedInPixel) {
            randomBallPosition.right((targetPosition.x - randomBallPosition.x) / distance * speedInPixel);
            randomBallPosition.down((targetPosition.y - randomBallPosition.y) / distance * speedInPixel);
        } else {
            calculateRandomTargetPosition();
        }
    }

    private void calculateRandomTargetPosition() {
        targetPosition.x = random.nextInt(959);
        targetPosition.y = random.nextInt(539);

    }

    public Position getRandomBallPosition() {
        return randomBallPosition;
    }
}