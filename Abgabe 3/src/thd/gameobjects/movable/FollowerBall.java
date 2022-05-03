package thd.gameobjects.movable;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.Color;

public class FollowerBall extends GameObject {

    private final RandomBall followMe;
    private final Position targetPosition;

    public FollowerBall(GameView gameView, RandomBall randomBall) {
        super(gameView);
        followMe = randomBall;
        position = new Position(959, 539);
        targetPosition = new Position(followMe.getPosition().x, followMe.getPosition().y);
        speedInPixel = 3;
        size = 50;
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.green);
    }

    @Override
    public void updatePosition() {
        targetPosition.x = followMe.getPosition().x;
        targetPosition.y = followMe.getPosition().y;
        double distance = position.distance(targetPosition);
        position.right((targetPosition.x - position.x) / distance * speedInPixel);
        position.down((targetPosition.y - position.y) / distance * speedInPixel);
    }
}
