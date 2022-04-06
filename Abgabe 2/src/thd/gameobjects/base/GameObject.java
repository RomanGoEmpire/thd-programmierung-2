package thd.gameobjects.base;

import thd.gameview.GameView;

public class GameObject {

    public final GameView gameView;
    public Position position;
    public double size;
    public double speedInPixel;
    public double rotation;
    public double width;
    public double height;

    public GameObject(GameView gameView) {
        this.gameView = gameView;
        position = new Position(0, 0);
    }

    public void updatePosition() {
    }

    public void addToCanvas() {
    }

    public Position getPosition() {
        return position;
    }
}
