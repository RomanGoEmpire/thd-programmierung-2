package thd.game.managers;

/**
 * This Exception is thrown when there is no Level.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {
    NoMoreLevelsAvailableException(String message){
        super(message);
    }
}
