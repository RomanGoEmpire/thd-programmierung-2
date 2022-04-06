package game;

public class Start {

    public static void main(String[] args) {
        var gameLoopManager = new GameLoopManager();


        Position position1 = new Position(100, 110);
        var position2 = new Position(200, 300);

        System.out.println(position2);
        position2.down();
        position2.right();
        System.out.println(position2);
        gameLoopManager.startGame();
    }
}
