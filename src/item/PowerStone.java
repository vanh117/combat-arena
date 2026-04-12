package item;

import domain.Player;
import engine.BattleEngine;
import ui.IGameView;
import action.IAction;

public class PowerStone implements IItem {
    public String getName() { return "Power Stone"; }
    public void use(Player user, BattleEngine engine, IGameView ui) {
        ui.showMessage(user.getName() + " used Power Stone! Triggering free special skill...");
        IAction skill = user.getSpecialSkill();
        skill.execute(user, engine, ui);
    }
}