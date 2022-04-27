package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;

public class BulletRight extends GameObject {

    private Position roverPosition;
    private Rover rover;

    /**
     * Initializes the GameObject.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager
     */
    public BulletRight(GameView gameView, GamePlayManager gamePlayManager, Position position, Rover rover) {
        super(gameView, gamePlayManager);
        this.position = position;
        this.rover = rover;
        roverPosition = new Position(rover.getPosition().x,rover.getPosition().y);
        position.x += 110;
        speedInPixel = 1.5;
    }


    @Override
    public void updateStatus() {
        if (position.x > roverPosition.x + 300) {
            gamePlayManager.destroy(this);
            rover.allowedToShoot = true;
        }
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("-O", position.x, position.y, 30, Color.RED, 0);
    }
}
