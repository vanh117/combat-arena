package engine;

import action.IAction;
import domain.Enemy;
import domain.Goblin;
import domain.Wolf;
import strategy.BasicAttackStrategy;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    public static List<Enemy> getInitialSpawns(int level, IAction defaultAttack) {
        List<Enemy> enemies = new ArrayList<>();
        if (level == 1) {
            enemies.add(new Goblin("A")); enemies.add(new Goblin("B")); enemies.add(new Goblin("C"));
        } else if (level == 2) {
            enemies.add(new Goblin("A")); enemies.add(new Wolf("A"));
        } else if (level == 3) {
            enemies.add(new Goblin("A")); enemies.add(new Goblin("B"));
        }
        assignStrategies(enemies, defaultAttack);
        return enemies;
    }

    public static List<Enemy> getBackupSpawns(int level, IAction defaultAttack) {
        List<Enemy> backups = new ArrayList<>();
        if (level == 2) {
            backups.add(new Wolf("A")); backups.add(new Wolf("B")); // Fixed names
        } else if (level == 3) {
            backups.add(new Goblin("C")); backups.add(new Wolf("A")); backups.add(new Wolf("B"));
        }
        assignStrategies(backups, defaultAttack);
        return backups;
    }

    private static void assignStrategies(List<Enemy> enemies, IAction defaultAttack) {
        BasicAttackStrategy strategy = new BasicAttackStrategy(defaultAttack);
        for (Enemy e : enemies) e.setActionStrategy(strategy);
    }
}