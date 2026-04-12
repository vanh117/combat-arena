package item;

import domain.Player;
import engine.BattleEngine;
import ui.IGameView;

public interface IItem {
    String getName();
    void use(Player user, BattleEngine engine, IGameView ui);
}