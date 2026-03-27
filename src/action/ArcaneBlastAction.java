package action;

import domain.Combatant;
import engine.BattleEngine;
import ui.IGameView;
import java.util.ArrayList;
import java.util.List;

public class ArcaneBlastAction implements IAction {
    public String getName() { return "Arcane Blast"; }
    public void execute(Combatant user, BattleEngine engine, IGameView ui) {
        ui.showMessage(user.getName() + " casts Arcane Blast on ALL enemies!");
        List<Combatant> enemies = engine.getAliveEnemies();
        
        for (Combatant target : new ArrayList<>(enemies)) {
            int damage = Math.max(0, user.getAttack() - target.getDefense());
            target.takeDamage(damage);
            ui.showMessage("Dealt " + damage + " damage to " + target.getName());
            
            if (!target.isAlive()) {
                ui.showMessage(target.getName() + " has been ELIMINATED by Arcane Blast!");
                user.addAttack(10);
                ui.showMessage(user.getName() + " gains +10 Attack! (Current: " + user.getAttack() + ")");
            }
        }
    }
}