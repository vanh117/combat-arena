package domain;

import action.IAction;
import action.ShieldBashAction;

public class Warrior extends Player {
    public Warrior() { super("Warrior", 260, 40, 20, 30); }
    @Override public String getSkillName() { return "Shield Bash"; }
    @Override public IAction getSpecialSkill() { return new ShieldBashAction(); }
}