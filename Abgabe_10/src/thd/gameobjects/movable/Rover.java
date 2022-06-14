package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameview.GameView;

/**
 * An object which can be controlled by the Player. It can drive and shoot.
 */
public class Rover extends CollidableGameObject implements AutoMovable {


    boolean allowedToShoot;
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
        position.x = 200;
        position.y = GameView.HEIGHT - 55;
        speedInPixel = 2;

        size = 0.1;
        allowedToShoot = true;
        rotation = 0;
        movingUp = false;
        jumpState = JumpState.STANDARD;
        damageState = DamageState.STANDARD;
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
        if (other.getClass() == BulletDown.class) {
            gamePlayManager.isGameOver = true;
            gamePlayManager.destroy(this);
        }
    }

    @Override
    public void updateStatus() {
        rotation += 2;
        jumpingAnimation();
    }

    @Override
    public void addToCanvas() {
        //gameView.addTextToCanvas(damageState.display, position.x, position.y + jumpState.yOffset, damageState.fontSize, Color.white, rotation);
        gameView.addImageToCanvas("rover.png", position.x, position.y + jumpState.yOffset, size, 0);
        gameView.addImageToCanvas("tire.png", position.x + 32, position.y + jumpState.yOffset + 24, size, rotation * 2);
        gameView.addImageToCanvas("tire.png", position.x + 2, position.y + jumpState.yOffset + 24, size, 150 + rotation * 2);
        gameView.addImageToCanvas("tire.png", position.x + 100, position.y + jumpState.yOffset + 24, size, 100 + rotation * 2);


    }

    @Override
    public void updatePosition() {
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
        movingUp = true;
    }

    /**
     * shoots.
     */
    public void shoot() {

    }

    private enum JumpState {
        STANDARD(0), TEN(-10), TWENTY(-20), THIRTY(-30), FORTY(-40), FIFTY(-50), TEN_DOWN(-10), TWENTY_DOWN(-20), THIRTY_DOWN(-30), FORTY_DOWN(-40);

        private final int yOffset;

        JumpState(int yOffset) {
            this.yOffset = yOffset;
        }

    }

    private void jumpingAnimation() {
        if (movingUp) {
            if (!gameView.alarmIsSet("jumpingAnimation", this)) {
                gameView.setAlarm("jumpingAnimation", this, 60);
            } else if (gameView.alarm("jumpingAnimation", this)) {
                if (jumpState == JumpState.STANDARD) {
                    jumpState = JumpState.TEN;
                } else if (jumpState == JumpState.TEN) {
                    jumpState = JumpState.TWENTY;
                } else if (jumpState == JumpState.TWENTY) {
                    jumpState = JumpState.THIRTY;
                } else if (jumpState == JumpState.THIRTY) {
                    jumpState = JumpState.FORTY;
                } else if (jumpState == JumpState.FORTY) {
                    jumpState = JumpState.FIFTY;
                } else if (jumpState == JumpState.FIFTY) {
                    jumpState = JumpState.FORTY_DOWN;
                } else if (jumpState == JumpState.FORTY_DOWN) {
                    jumpState = JumpState.THIRTY_DOWN;
                } else if (jumpState == JumpState.THIRTY_DOWN) {
                    jumpState = JumpState.TWENTY_DOWN;
                } else if (jumpState == JumpState.TWENTY_DOWN) {
                    jumpState = JumpState.TEN_DOWN;
                } else if (jumpState == JumpState.TEN_DOWN) {
                    jumpState = JumpState.STANDARD;
                    movingUp = false;
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
