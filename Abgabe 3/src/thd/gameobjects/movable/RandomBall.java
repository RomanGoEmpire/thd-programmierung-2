package thd.gameobjects.movable;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;
import java.util.Random;

public class RandomBall extends GameObject {

    private final Random random;

    private final Position targetPosition;

    public RandomBall(GameView gameView) {
        super(gameView);
        random = new Random();
        position = new Position(0, 0);
        targetPosition = new Position(800, 200);
        speedInPixel = 4;
        size = 50;
    }

    @Override
    public void addToCanvas() {

        gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.yellow);
        gameView.addOvalToCanvas(targetPosition.x, targetPosition.y, size, size, 2, false, Color.white);
    }

    @Override
    public void updatePosition() {
        double distance = position.distance(targetPosition);
        if (distance >= speedInPixel) {
            position.right((targetPosition.x - position.x) / distance * speedInPixel);
            position.down((targetPosition.y - position.y) / distance * speedInPixel);
            rotation++;
        } else {
            calculateRandomTargetPosition();
        }
    }

    private void calculateRandomTargetPosition() {
        targetPosition.x = random.nextInt(959);
        targetPosition.y = random.nextInt(539);

    }
}