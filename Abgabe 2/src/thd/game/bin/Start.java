package thd.game.bin;

import thd.game.managers.GameLoopManager;

/**
 * Start of Program.
 */

public class Start {

    /**
     * Main methode.
     *
     * @param args
     */
    public static void main(String[] args) {
        var gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();
    }
}
