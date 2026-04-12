package status;

import domain.Combatant;

public class DefendEffect extends BaseStatusEffect {
    public DefendEffect() { super(2); }
    public String getName() { return "Defending"; }
    public void onApply(Combatant target) { target.setDefense(target.getDefense() + 10); }
    public void onRemove(Combatant target) { target.setDefense(target.getDefense() - 10); }
}