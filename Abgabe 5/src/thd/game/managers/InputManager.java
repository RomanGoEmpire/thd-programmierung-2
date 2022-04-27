package thd.game.managers;

import thd.gameobjects.movable.X;
import thd.gameview.GameView;

import java.awt.event.KeyEvent;

class InputManager {
    private static final boolean DIAGONAL_MOVEMENT_ALLOWED = false;
    private final GameView gameView;
    private final X x;

    InputManager(GameView gameView, X x) {
        this.gameView = gameView;
        this.x = x;
    }

    void updateUserInputs() {
        Integer[] pressedKeys = gameView.getKeyCodesOfCurrentlyPressedKeys();
        if (DIAGONAL_MOVEMENT_ALLOWED) {
            for (int keyCode : pressedKeys) {
                processKeyCode(keyCode);
            }
        } else {
            if (pressedKeys.length >= 1) {
                processKeyCode(pressedKeys[0]);
            }
        }
    }


    private void processKeyCode(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                x.up();
                break;
            case KeyEvent.VK_DOWN:
                x.down();
                break;
            case KeyEvent.VK_LEFT:
                x.left();
                break;
            case KeyEvent.VK_RIGHT:
                x.right();
                break;
            case KeyEvent.VK_SPACE:
                x.shoot();
                break;
            default:
                break;
        }
    }
}
