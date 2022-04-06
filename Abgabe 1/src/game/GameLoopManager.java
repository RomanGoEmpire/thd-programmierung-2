package game;


public class GameLoopManager {


    private final GameView gameView;
    private final Ufo ufo;

    public GameLoopManager() {
        gameView = new GameView();
        gameView.setWindowTitle("Moon Patrol");
        gameView.setStatusText("Java Programmierung SS 2022");
        gameView.setWindowIcon("Icon2.png");
        this.ufo = new Ufo(gameView);
    }

    public void startGame() {
        while (true) {
            ufo.updatePosition();
            gameView.addImageToCanvas("background.png", 0, 0, 1, 0);
            gameView.addImageToCanvas("city.png", 0, 50, 0.75, 0);
            ufo.addToCanvas(0.5, 0);
            gameView.printCanvas();
        }
    }
}

