package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Message in the middle of the Canvas.
 */

public class Overlay extends GameObject {

    private String text;

    /**
     * Constructor of the overlay.
     * @param gameView window.
     * @param gamePlayManager manager.
     */
    public Overlay(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
    }

    /**
     * makes it possible to enter the message you want to enter.
     * @param message the text which is displayed.
     * @param secondsToShow the amount of seconds it is displayed.
     */
    public void showMessage(String message, int secondsToShow) {
        text = message;
        gameView.activateTimer(text, this, secondsToShow * 1000L);
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        if (gameView.timerIsActive(text, this)) {
            final int size = 50;
            final double xCoordinate = GameView.WIDTH / 2.0 - size * 4;
            final double yCoordinate = GameView.HEIGHT / 2.0 - size / 2.0;
            gameView.addTextToCanvas(text, xCoordinate, yCoordinate, size, Color.white, 0);
        }
    }
}
