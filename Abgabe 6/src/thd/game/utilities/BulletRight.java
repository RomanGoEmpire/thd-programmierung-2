package thd.game.utilities;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Rover;
import thd.gameview.GameView;

import java.awt.*;

/**
 * This class is a bullet which gets shot by the Rover. It moves up.
 */
public class BulletRight extends GameObject implements AutoMovable {

    private final Position roverPosition;
    private final Rover rover;

    /**
     * Initializes the BulletRight.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param position        is the position the Bullet should spawn
     * @param rover           helps the Bullet to get destroyed at the right position
     */
    public BulletRight(GameView gameView, GamePlayManager gamePlayManager, Position position, Rover rover) {
        super(gameView, gamePlayManager);
        this.position = position;
        this.rover = rover;
        roverPosition = new Position(rover.getPosition().x, rover.getPosition().y);
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
