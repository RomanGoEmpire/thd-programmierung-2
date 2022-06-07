package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

import java.awt.*;

/**
 * An object which can be controlled by the Player. It can drive and shoot.
 */
public class Rover extends CollidableGameObject implements AutoMovable {


    boolean allowedToShoot;

    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private JumpState jumpState;
    private DamageState damageState;

    /**
     * An Rover is generated.
     *
     * @param gameView        is the window in which it gets displayed
     * @param gamePlayManager is the manager, which makes sure that the objects are spawned and destroyed.
     */
    public Rover(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.x = GameView.WIDTH / 2d;
        position.y = GameView.HEIGHT / 2d;
        speedInPixel = 3;

        size = 0.1;
        allowedToShoot = true;
        rotation = 0;
        movingLeft = false;
        movingRight = false;
        movingUp = false;
        jumpState = JumpState.STANDARD;
        damageState = DamageState.STANDARD;
    }

    private enum JumpState {
        STANDARD(0), HALF_UP(-20), UP(-40), HALF_DOWN(-20);

        private final int yOffset;

        JumpState(int yOffset) {
            this.yOffset = yOffset;
        }

    }

    private enum DamageState {
        STANDARD("X", 50), DAMAGED1("Y", 90),
        DAMAGED2("Z", 130), DEAD("X", 50);

        private final String display;
        private final int fontSize;

        DamageState(String display, int fontSize) {
            this.display = display;
            this.fontSize = fontSize;
        }
    }

    @Override
    protected void initializeHitbox() {
        hitBoxWidth = 130;
        hitBoxHeight = 50;
        hitBoxOffsetX = 2;
        hitBoxOffsetY = 0;
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {

    }

    @Override
    public void updateStatus() {
        rollingAnimation();
        jumpingAnimation();
        damageAnimation();
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(damageState.display, position.x, position.y + jumpState.yOffset, damageState.fontSize, Color.white, rotation);
        /*
        gameView.addImageToCanvas("tire.png", position.x + 32, position.y + 24, size, rotation * 2);
        gameView.addImageToCanvas("tire.png", position.x + 2, position.y + 24, size, 150 + rotation * 2);
        gameView.addImageToCanvas("tire.png", position.x + 100, position.y + 24, size, 100 + rotation * 2);
         */

    }

    @Override
    public void updatePosition() {
    }


    /**
     * Movement left.
     */
    public void left() {
        position.left(speedInPixel);
        movingLeft = true;
    }

    /**
     * Movement right.
     */
    public void right() {
        position.right(speedInPixel);
        movingRight = true;
    }

    /**
     * Movement up.
     */
    public void up() {
        position.up(speedInPixel);
        movingUp = true;
    }

    /**
     * Movement right.
     */
    public void down() {
        position.down(speedInPixel);
    }

    /**
     * shoots.
     */
    public void shoot() {
    }

    private void rollingAnimation() {

        if (movingLeft) {
            movingLeft = false;
            rotation -= 7;
        } else {
            rotation = 0;
        }

    }

    private void jumpingAnimation() {
        if (movingRight) {
            if (!gameView.alarmIsSet("jumpingAnimation", this)) {
                gameView.setAlarm("jumpingAnimation", this, 60);
            } else if (gameView.alarm("jumpingAnimation", this)) {
                switch (jumpState) {
                    case STANDARD:
                        jumpState = JumpState.HALF_UP;
                        break;
                    case HALF_UP:
                        jumpState = JumpState.UP;
                        break;
                    case UP:
                        jumpState = JumpState.HALF_DOWN;
                        break;
                    case HALF_DOWN:
                        jumpState = JumpState.STANDARD;
                        movingRight = false;
                        break;
                    default:
                }
            }
        }
    }

    private void damageAnimation() {
        if (movingUp) {
            switch (damageState) {
                case STANDARD:
                    damageState = DamageState.DAMAGED1;
                    break;
                case DAMAGED1:
                    if (!gameView.alarmIsSet("damaged1", this)) {
                        gameView.setAlarm("damaged1", this, 100);
                    } else if (gameView.alarm("damaged1", this)) {
                        damageState = DamageState.DAMAGED2;
                    }
                    break;
                case DAMAGED2:
                    if (!gameView.alarmIsSet("damaged2", this)) {
                        gameView.setAlarm("damaged2", this, 200);
                    } else if (gameView.alarm("damaged2", this)) {
                        damageState = DamageState.DEAD;
                    }
                    break;
                case DEAD:
                    if (!gameView.alarmIsSet("dead", this)) {
                        gameView.setAlarm("dead", this, 1000);
                    } else if (gameView.alarm("dead", this)) {
                        damageState = DamageState.STANDARD;
                        movingUp = false;
                    }
                    break;
                default:
            }
        }
    }


}
