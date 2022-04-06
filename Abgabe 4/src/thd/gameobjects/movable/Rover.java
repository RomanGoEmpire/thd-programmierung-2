package thd.gameobjects.movable;

import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

/**
 * An object which can be controlled by the Player. It can drive and shoot.
 */
public class Rover extends GameObject {

    /**
     * An Rover is generated.
     *
     * @param gameView is the window in which it gets displayed
     */
    public Rover(GameView gameView) {
        super(gameView);
        position = new Position(0, (GameView.HEIGHT - 52));
        speedInPixel = 1;
        size = 0.1;
    }

    @Override
    public void addToCanvas() {
        if (position.x >= 1000) {
            position.x = -120;
        }
        gameView.addImageToCanvas("rover.png", position.x, position.y, size, 0);
        gameView.addImageToCanvas("tire.png", position.x + 32, position.y + 24, size, rotation * 2);
        gameView.addImageToCanvas("tire.png", position.x + 2, position.y + 24, size, 150 + rotation * 2);
        gameView.addImageToCanvas("tire.png", position.x + 100, position.y + 24, size, 100 + rotation * 2);
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
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

}
