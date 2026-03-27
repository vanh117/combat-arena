package strategy;

import action.IAction;
import domain.Enemy;
import engine.BattleEngine;

public interface IEnemyActionStrategy {
    IAction determineAction(Enemy enemy, BattleEngine engine);
}