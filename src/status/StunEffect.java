package status;

import domain.Combatant;

public class StunEffect extends BaseStatusEffect {
    public StunEffect() { super(2); }
    public String getName() { return "Stun"; }
    public void onApply(Combatant target) {}
    public void onRemove(Combatant target) {}
}