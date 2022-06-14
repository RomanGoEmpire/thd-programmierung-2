package thd.game.level;

/**
 * Level has all the important information about a level.
 */
public abstract class Level {
    /**
     * Name.
     */
    public String name;
    /**
     * number of Level.
     */
    protected int number;
    /**
     * amount of enemies.
     */
    protected int amountEnemies;
    /**
     * speed of enemies.
     */
    protected int speedEnemies;
    /**
     * shots per second.
     */
    protected int shotsPerSecond;
    /**
     * Picture name.
     */
    protected String backgroundString;
    /**
     * The time between spawns.
     */
    protected int timeBetweenSpawns;
    /**
     * Schwierigkeit.
     */
    private final Difficulty difficulty;

    /**
     * How difficult the level is.
     */
    public enum Difficulty {EASY, STANDARD}

    Level(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

}
