package thd.game.managers;

import thd.gameobjects.movable.Rover;
import thd.gameview.GameView;

import java.awt.event.KeyEvent;

class InputManager {
    private static final boolean DIAGONAL_MOVEMENT_ALLOWED = true;
    private final GameView gameView;
    private final Rover rover;

    InputManager(GameView gameView, Rover rover) {
        this.gameView = gameView;
        this.rover = rover;
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
            case KeyEvent.VK_D:
                rover.shoot();
                break;
            case KeyEvent.VK_LEFT:
                rover.left();
                break;
            case KeyEvent.VK_RIGHT:
                rover.right();
                break;
            case KeyEvent.VK_SPACE:
                rover.jump();
                break;
            default:
                break;
        }
    }
}
