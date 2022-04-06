package thd.game.managers;

import thd.gameobjects.base.Position;
import thd.gameobjects.movable.*;
import thd.gameview.GameView;

import java.awt.*;

class GameObjectManager {

    private final GameView gameView;

    private final Rover rover;
    private final Ufo ufo;
    private final City city;

    private final Position trianglePosition;
    private final double height;
    private final double width;
    private final RandomBall randomBall;
    private final FollowerBall followerBall;
    private boolean flyFromLeftToRight;
    private int counter;

    GameObjectManager(GameView gameView) {
        this.gameView = gameView;


        city = new City(gameView);
        rover = new Rover(gameView);
        ufo = new Ufo(gameView);

        //Balls
        randomBall = new RandomBall(gameView);
        followerBall = new FollowerBall(gameView, randomBall);

        height = 38;
        width = 40;
        trianglePosition = new Position(GameView.WIDTH - (width / 2d) - 1, GameView.HEIGHT - height);
        flyFromLeftToRight = false;

        counter = 1;
    }

    void updateGameObjects() {
        // Background
        gameView.addImageToCanvas("background.png", 0, 0, 1, 0);

        //canyon
        //gameView.addImageToCanvas("canyon.png", 1008 + cityPosition[1].x * 0.5, calculateHeightOrWidth(0.7, 405), 0.7, 0);
        //gameView.addImageToCanvas("canyon.png", 0 + cityPosition[1].x * 0.5, calculateHeightOrWidth(0.7, 405), 0.7, 0);

        //city
        city.updatePosition();
        city.addToCanvas();

        //rover
        rover.setRotation(counter * 2);
        rover.updatePosition();
        rover.addToCanvas();
        //ufo
        ufo.updatePosition(counter);
        ufo.addToCanvas();

        ///second object
        gameView.addOvalToCanvas(trianglePosition.x - 10, trianglePosition.y, 20, 20, 1, true, Color.red);
        gameView.addOvalToCanvas(trianglePosition.x + 10, trianglePosition.y, 20, 20, 1, true, Color.red);
        gameView.addOvalToCanvas(trianglePosition.x, trianglePosition.y + 18, 20, 20, 1, true, Color.red);

        gameView.addTextToCanvas("" + counter, 0, 0, 50, Color.white, 0);

        randomBall.updatePosition();
        randomBall.addToCanvas(50);
        followerBall.updatePosition();
        followerBall.addToCanvas(50);

        updatePosition();
        counter++;
    }

    private void updatePosition() {
        if (flyFromLeftToRight) {
            trianglePosition.right();
        } else {
            trianglePosition.left();
        }
        if (trianglePosition.x <= width / 2d) {
            trianglePosition.right();
            flyFromLeftToRight = true;
        } else if (trianglePosition.x >= GameView.WIDTH - width / 2d) {
            trianglePosition.left();
            flyFromLeftToRight = false;
        }
    }
}
