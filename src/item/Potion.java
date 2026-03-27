package item;

import domain.Player;
import engine.BattleEngine;
import ui.IGameView;

public class Potion implements IItem {
    public String getName() { return "Potion"; }
    public void use(Player user, BattleEngine engine, IGameView ui) {
        user.heal(100);
        ui.showMessage(user.getName() + " used Potion! Healed 100 HP. Current HP: " + user.getHp());
    }
}