package action;

import domain.Combatant;
import engine.BattleEngine;
import ui.IGameView;
import status.DefendEffect;

public class DefendAction implements IAction {
    public String getName() { return "Defend"; }
    public void execute(Combatant user, BattleEngine engine, IGameView ui) {
        user.addStatusEffect(new DefendEffect());
        ui.showMessage(user.getName() + " is defending! Defense increased by 10.");
    }
}