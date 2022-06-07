package thd.game.managers;

/**
 * This Exception is activated when there are too many Objects spawned.
 * This prevents the game to lag and makes sure that all displayed objects are implemented correct.
 */
public class TooManyGameObjectsException extends RuntimeException {

    TooManyGameObjectsException(String tooManyObjects) {
        super(tooManyObjects);
    }
}
