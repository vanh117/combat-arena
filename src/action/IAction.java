package action;

import domain.Combatant;
import engine.BattleEngine;
import ui.IGameView;

public interface IAction {
    String getName();
    void execute(Combatant user, BattleEngine engine, IGameView ui);
}