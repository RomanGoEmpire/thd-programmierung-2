package thd.gameobjects.movable;

import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;
import java.util.Random;

public class FollowerBall {

    private final Random random;
    private final RandomBall followMe;

    private final Position targetPosition;
    private final Position randomBallPosition;
    private final GameView gameView;
    private final int speedInPixel;

    public FollowerBall(GameView gameView, RandomBall randomBall) {
        followMe = randomBall;
        this.gameView = gameView;
        random = new Random();
        randomBallPosition = new Position(959, 539);
        targetPosition = new Position(followMe.getRandomBallPosition().x, followMe.getRandomBallPosition().y);
        speedInPixel = 2;
    }

    public void addToCanvas(double size) {
        gameView.addOvalToCanvas(randomBallPosition.x, randomBallPosition.y, size, size, 2, true, Color.green);
    }

    public void updatePosition() {
        double distance = randomBallPosition.distance(targetPosition);
        targetPosition.x = followMe.getRandomBallPosition().x;
        targetPosition.y = followMe.getRandomBallPosition().y;
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
}
