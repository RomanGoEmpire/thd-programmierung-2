package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.BulletRight;
import thd.game.utilities.BulletUP;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;

/**
 * An object which can be controlled by the Player. It can drive and shoot.
 */
public class Rover extends GameObject implements AutoMovable {


    private final double shotsPerSecondUp;
    /**
     * the Bullet right is only allowed to be shot if there is no bullet already flying.
     */
    public boolean allowedToShoot;
    private boolean shooting;

    /**
     * An Rover is generated.
     *
     * @param gameView        is the window in which it gets displayed
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public Rover(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position = new Position(0, (GameView.HEIGHT - 52));
        speedInPixel = 1.5;
        shotsPerSecondUp = 5;
        size = 0.1;
        shooting = false;
        allowedToShoot = true;
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        if (position.x >= 1000) {
            position.x = -120;
        }
        if (shooting) {
            gameView.addTextToCanvas("O", position.x, position.y, 50, Color.white, 0);
            shooting = false;
        } else {
            gameView.addImageToCanvas("rover.png", position.x, position.y, size, 0);
            gameView.addImageToCanvas("tire.png", position.x + 32, position.y + 24, size, rotation * 2);
            gameView.addImageToCanvas("tire.png", position.x + 2, position.y + 24, size, 150 + rotation * 2);
            gameView.addImageToCanvas("tire.png", position.x + 100, position.y + 24, size, 100 + rotation * 2);
        }
    }

    @Override
    public void updatePosition() {
        updateRotation(speedInPixel);
    }

    /**
     * The object rotation is updated.
     *
     * @param rotation changing rotation.
     */
    private void updateRotation(double rotation) {
        this.rotation += rotation;
    }

    /**
     * Movement left.
     */
    public void left() {
        position.left(speedInPixel);
    }

    /**
     * Movement right.
     */
    public void right() {
        position.right(speedInPixel);
    }

    /**
     * shoots.
     */
    public void shoot() {
        if (!gameView.timerIsActive("bullet-up", this)) {
            gameView.activateTimer("bullet-up", this, (long) (1000 / shotsPerSecondUp));
            BulletUP roverBulletUP = new BulletUP(gameView, gamePlayManager, new Position(position.x, position.y));
            gamePlayManager.spawn(roverBulletUP);
        }
        if (allowedToShoot) {
            BulletRight roverBulletRight = new BulletRight(gameView, gamePlayManager, new Position(position.x, position.y), this);
            gamePlayManager.spawn(roverBulletRight);
            allowedToShoot = false;
        }
    }

}
