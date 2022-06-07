package thd.game.managers;

import thd.game.level.Level;
import thd.game.level.Level0;
import thd.game.level.Level1;
import thd.game.level.Level2;

import java.util.LinkedList;

/**
 * manages the different level.
 */
class LevelManager {
    LinkedList<Level> levels;
    private int currentLevel;

    LevelManager(Level.Difficulty difficulty) {
        levels = new LinkedList<>();
        levels.add(new Level0(difficulty));
        levels.add(new Level1(difficulty));
        levels.add(new Level2(difficulty));
        currentLevel = 0;
    }

    boolean hasNextLevel() {
        return levels.size() - 1 >= currentLevel;
    }

    Level nextLevel() {
        if (hasNextLevel()) {
            currentLevel++;
            return levels.get(currentLevel - 1);
        } else {
            throw new NoMoreLevelsAvailableException("There are no more Levels");
        }
    }

    void resetLevelCounter() {
        currentLevel = 0;
    }

}
