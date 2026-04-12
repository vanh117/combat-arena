package strategy;

import action.IAction;
import domain.Enemy;
import engine.BattleEngine;

public class BasicAttackStrategy implements IEnemyActionStrategy {
    private IAction basicAttack;

    public BasicAttackStrategy(IAction basicAttack) {
        this.basicAttack = basicAttack;
    }

    @Override
    public IAction determineAction(Enemy enemy, BattleEngine engine) {
        return basicAttack;
    }
}