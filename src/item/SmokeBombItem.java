package item;

import domain.Player;
import engine.BattleEngine;
import ui.IGameView;
import status.SmokeBombEffect;

public class SmokeBombItem implements IItem {
    public String getName() { return "Smoke Bomb"; }
    public void use(Player user, BattleEngine engine, IGameView ui) {
        user.addStatusEffect(new SmokeBombEffect());
        ui.showMessage(user.getName() + " used Smoke Bomb! Enemy attacks do 0 damage this turn and next.");
    }
}