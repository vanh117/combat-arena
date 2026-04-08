package domain;

import action.IAction;
import action.ArcaneBlastAction;

public class Wizard extends Player {
    public Wizard() { super("Wizard", 200, 50, 10, 20); }
    @Override public String getSkillName() { return "Arcane Blast"; }
    @Override public IAction getSpecialSkill() { return new ArcaneBlastAction(); }
}