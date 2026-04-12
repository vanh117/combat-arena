package status;

import domain.Combatant;

public interface IStatusEffect {
    String getName();
    void onApply(Combatant target);
    void onRemove(Combatant target);
    void tick();
    boolean isExpired();
    
    // modifying incoming damage is the effects' responsibility    
    default int modifyIncomingDamage(Combatant attacker, Combatant target, int damage) {
        return damage;
    }
}