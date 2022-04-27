package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.awt.*;

public class BulletUP extends GameObject {

    /**
     * Initializes the GameObject.
     *
     * @param gameView is the window it is displayed.
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
