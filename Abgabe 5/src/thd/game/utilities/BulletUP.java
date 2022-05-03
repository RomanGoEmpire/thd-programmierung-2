package thd.game.utilities;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;

/**
 * This class is a bullet which gets shot by the Rover. It moves up.
 */

public class BulletUP extends GameObject implements AutoMovable {

    /**
     * Initializes the BulletUP.
     *
     * @param gameView        is the window it is displayed.
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     * @param position        is the position the Bullet should spawn
     */
    public BulletUP(GameView gameView, GamePlayManager gamePlayManager, Position position) {
        super(gameView, gamePlayManager);
        this.position = position;
        position.y += -20;
        position.x += 70;
        speedInPixel = 3;
    }

    @Override
    public void updateStatus() {
        if (position.y < 0) {
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void updatePosition() {
        position.up(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("*", position.x, position.y, 20, Color.green, 0);
    }
}
