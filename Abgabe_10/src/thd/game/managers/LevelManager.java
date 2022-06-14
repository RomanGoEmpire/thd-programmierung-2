package thd.game.managers;

import thd.game.level.*;

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
        levels.add(new Level3(difficulty));
        currentLevel = 0;
    }

    private boolean hasNextLevel() {
        return levels.size() - 1 >= currentLevel;
    }

    Level nextLevel() {
        if (hasNextLevel()) {
            currentLevel++;
            return levels.get(currentLevel);
        } else {
            throw new NoMoreLevelsAvailableException("There are no more Levels");
        }
    }

    void resetLevelCounter() {
        currentLevel = 0;
    }

}
