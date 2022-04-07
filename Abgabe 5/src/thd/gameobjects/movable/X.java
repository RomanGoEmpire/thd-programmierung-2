package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;

/**
 * X is a model class for the main player.
 */
public class X extends GameObject {

    private boolean shooting;
    private boolean visible;

    /**
     * Initializes a X.
     *
     * @param gameView is the window it gets displayed.
     */
    public X(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        speedInPixel = 5;
        position = new Position(GameView.WIDTH / 2d, GameView.HEIGHT / 2d);
        visible = true;
    }

    /**
     * Makes X invisible when it moves out of the window.
     */
    @Override
    public void updateStatus() {
        if (position.y > GameView.HEIGHT) {
            visible = false;
        }
    }

    /**
     * Adds the object to the canvas.
     */
    @Override
    public void addToCanvas() {
        if (visible) {
            if (shooting) {
                gameView.addTextToCanvas("O", position.x, position.y, 50, Color.WHITE, 0);
                shooting = false;
            } else {
                gameView.addTextToCanvas("X", position.x, position.y, 50, Color.WHITE, 0);
            }
        }
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
     * Movement up.
     */
    public void up() {
        position.up(speedInPixel);
    }


    /**
     * Movement down.
     */
    public void down() {
        position.down(speedInPixel);
    }

    /**
     * shoots.
     */
    public void shoot() {
        shooting = true;
    }
}
