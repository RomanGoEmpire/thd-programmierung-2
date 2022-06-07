package thd.game.level;

/**
 * Level one.
 */

public class Level1 extends Level {

    /**
     * Constructor for the level.
     *
     * @param difficulty how difficult the level is.
     */
    public Level1(Difficulty difficulty) {
        super(difficulty);
        name = "start";
        number = 1;
        timeBetweenSpawns = 1000;
        backgroundString = "city.png";
        amountEnemies = 2;
        speedEnemies = 1;
        shotsPerSecond = 1;
    }


}
