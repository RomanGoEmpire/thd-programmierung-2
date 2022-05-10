package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Game objects that are able to collide with something.
 */
public abstract class CollidableGameObject extends GameObject {
    private final Rectangle hitBox;
    protected double hitBoxOffsetX;
    protected double hitBoxOffsetY;
    protected double hitBoxWidth;
    protected double hitBoxHeight;

    /**
     * Crates a new GameObject.
     *
     * @param gameView        Window to show the GameObject on.
     * @param gamePlayManager Controls the gameplay.
     */
    public CollidableGameObject(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        hitBox = new Rectangle(0, 0, 0, 0);
        initializeHitbox();
    }

    protected abstract void initializeHitbox();

    private void updateHitBoxPosition() {
        hitBox.x = (int) (position.x + hitBoxOffsetX);
        hitBox.y = (int) (position.y + hitBoxOffsetY);
        hitBox.width = (int) hitBoxWidth;
        hitBox.height = (int) hitBoxHeight;
        // Nur vorübergehend: Die Hitbox wird angezeigt. Bitte diese Zeile nach der Abgabe auskommentieren/löschen.
        //gameView.addRectangleToCanvas(hitBox.x, hitBox.y, hitBox.width, hitBox.height, 1, false, Color.RED);
    }

    /**
     * Determines if this game object is collided with the other game object.
     *
     * @param other The other game object.
     * @return <code>true</code> if the there was a collision.
     */
    public final boolean collidesWith(CollidableGameObject other) {
        updateHitBoxPosition();
        other.updateHitBoxPosition();
        return hitBox.intersects(other.hitBox);
    }

    /**
     * If a game object is collided with something, it is able to react to the collision.
     *
     * @param other The other GameObject that is involved in the collision.
     */
    public abstract void reactToCollision(CollidableGameObject other);
}