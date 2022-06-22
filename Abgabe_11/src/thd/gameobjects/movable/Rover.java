package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.Position;
import thd.gameview.GameView;

/**
 * An object which can be controlled by the Player. It can drive and shoot.
 */
public class Rover extends CollidableGameObject implements AutoMovable {


    boolean allowedToShoot;
    private boolean movingUp;
    private DamageState damageState;
    private int shotsPerSecondUp;
    private boolean jumpUP;
    private boolean jumping;

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
        speedInPixel = 1;
        shotsPerSecondUp = 5;
        jumping = false;
        jumpUP = false;
        size = 0.1;
        allowedToShoot = true;
        rotation = 0;
        movingUp = false;
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
        blockMovement();
    }

    private void blockMovement() {
        if(gamePlayManager.isGameOver){
        }
    }

    @Override
    public void addToCanvas() {
        //gameView.addTextToCanvas(damageState.display, position.x, position.y + jumpState.yOffset, damageState.fontSize, Color.white, rotation);
        gameView.addImageToCanvas("rover.png", position.x, position.y, size, 0);
        gameView.addImageToCanvas("tire.png", position.x + 32, position.y + 24, size, rotation * 2);
        gameView.addImageToCanvas("tire.png", position.x + 2, position.y + 24, size, 150 + rotation * 2);
        gameView.addImageToCanvas("tire.png", position.x + 100, position.y + 24, size, 100 + rotation * 2);


    }

    @Override
    public void updatePosition() {
        if (position.x < 100) {
            position.right(speedInPixel);
        }
        if (position.x >= 300) {
            position.left(speedInPixel);
        }
        if (jumping) {
            executeJump();
        }
        updateRotation(speedInPixel);
    }

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

    public void jump() {
        jumping = true;
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

    private void executeJump() {
        if (jumpUP) {
            position.up(speedInPixel);
            if (position.y < 420) {
                jumpUP = false;
            }
        } else {
            position.down(speedInPixel);
            if (position.y > GameView.HEIGHT - 52) {
                jumpUP = true;
                jumping = false;
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

    public void setAllowedToShoot(boolean allowedToShoot){
        this.allowedToShoot = allowedToShoot;
    }


}
