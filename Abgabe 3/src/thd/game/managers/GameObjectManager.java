package thd.game.managers;

import thd.gameobjects.movable.City;
import thd.gameobjects.movable.FollowerBall;
import thd.gameobjects.movable.RandomBall;
import thd.gameobjects.movable.Rover;
import thd.gameobjects.movable.ufo.Triangle;
import thd.gameobjects.movable.ufo.Ufo;
import thd.gameview.GameView;

class GameObjectManager {

    private final GameView gameView;

    private final Rover rover;
    private final Ufo ufo;
    private final City city;
    private final Triangle triangle;

    private final RandomBall randomBall;
    private final FollowerBall followerBall;


    private int counter;

    GameObjectManager(GameView gameView) {
        this.gameView = gameView;

        city = new City(gameView);
        rover = new Rover(gameView);
        ufo = new Ufo(gameView);
        triangle = new Triangle(gameView);

        //Balls
        randomBall = new RandomBall(gameView);
        followerBall = new FollowerBall(gameView, randomBall);


        counter = 1;
    }

    void updateGameObjects() {
        // Background
        gameView.addImageToCanvas("background.png", 0, 0, 1, 0);
        //city
        city.updatePosition();
        city.addToCanvas();
        //rover
        rover.updateRotation(counter * 2);
        rover.updatePosition();
        rover.addToCanvas();
        //ufo
        ufo.updatePosition();
        ufo.addToCanvas();
        //triangle
        triangle.updatePosition();
        triangle.addToCanvas();

        randomBall.updatePosition();
        randomBall.addToCanvas();
        followerBall.updatePosition();
        followerBall.addToCanvas();

        counter++;
    }


}
