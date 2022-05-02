package thd.game.managers;

public class TooManyGameObjectsException extends RuntimeException{

    TooManyGameObjectsException(String tooManyObjects){
        super(tooManyObjects);
    }
}
