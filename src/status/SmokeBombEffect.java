package status;

import domain.Combatant;
import domain.Enemy;

public class SmokeBombEffect extends BaseStatusEffect {
    public SmokeBombEffect() { super(2); }
    public String getName() { return "Smoke Bomb"; }
    public void onApply(Combatant target) {}
    public void onRemove(Combatant target) {}
    @Override
    public int modifyIncomingDamage(Combatant attacker, Combatant target, int damage) {
        if (attacker instanceof Enemy) {
            return 0;
        }
        return damage;
    }
    
}