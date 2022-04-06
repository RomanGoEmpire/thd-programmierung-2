package game;


public class Ufo {
    Position position;
    double speedInPixel;
    GameView gameView;

    Ufo(GameView gameView) {
        this.gameView = gameView;
        position = new Position(0, (GameView.HEIGHT / 2d));
        speedInPixel = 1;
    }

    public void addToCanvas(double size, double rotation) {
        gameView.addBlockImageToCanvas("B", position.x, position.y, size, rotation);
        gameView.addImageToCanvas("Ufo.png", position.x, position.y, size, rotation);
    }

    void updatePosition() {
        position.right(speedInPixel);
    }

    @Override
    public String toString() {
        return "Ufo:" + position;
    }
}
