package action;

import domain.Combatant;
import engine.BattleEngine;
import ui.IGameView;
import status.StunEffect;

public class ShieldBashAction implements IAction {
    public String getName() { return "Shield Bash"; }
    public void execute(Combatant user, BattleEngine engine, IGameView ui) {
        Combatant target = ui.selectTarget(engine.getAliveEnemies());
        if (target == null) return;

        int damage = Math.max(0, user.getAttack() - target.getDefense());
        target.takeDamage(damage);
        target.addStatusEffect(new StunEffect());
        
        ui.showMessage(user.getName() + " uses Shield Bash on " + target.getName() + " for " + damage + " damage!");
        ui.showMessage(target.getName() + " is STUNNED for 2 turns!");
        if (!target.isAlive()) ui.showMessage(target.getName() + " has been ELIMINATED!");
    }
}