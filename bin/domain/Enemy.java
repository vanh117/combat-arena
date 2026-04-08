package domain;

import strategy.IEnemyActionStrategy;

public abstract class Enemy extends Combatant {
    private IEnemyActionStrategy actionStrategy;

    public Enemy(String name, int maxHp, int attack, int defense, int speed) {
        super(name, maxHp, attack, defense, speed);
    }

    public void setActionStrategy(IEnemyActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public IEnemyActionStrategy getActionStrategy() {
        return actionStrategy;
    }
}