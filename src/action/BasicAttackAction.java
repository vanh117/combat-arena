package action;

import domain.Combatant;
import domain.Player;
import engine.BattleEngine;
import ui.IGameView;

public class BasicAttackAction implements IAction {
    public String getName() { return "Basic Attack"; }
    public void execute(Combatant user, BattleEngine engine, IGameView ui) {
        Combatant target;

        if (user instanceof Player) {
            target = ui.selectTarget(engine.getAliveEnemies());
        } else {
            target = engine.getPlayer();
        }

        if (target == null) return;

        int damage = Math.max(0, user.getAttack() - target.getDefense());

        // let target modifying incoming damage...
        damage = target.modifyIncomingDamage(user, damage);

        if (damage == 0) {
            ui.showMessage("Attack was nullified!");
        }

        target.takeDamage(damage);

        ui.showMessage(user.getName() + " attacks " + target.getName() + " for " + damage + " damage!");

        if (!target.isAlive()) {
            ui.showMessage(target.getName() + " has been ELIMINATED!");
        }
    }
}