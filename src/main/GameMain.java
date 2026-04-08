package main;

import action.BasicAttackAction;
import action.DefendAction;
import action.IAction;
import domain.Player;
import domain.Warrior;
import domain.Wizard;
import engine.BattleEngine;
import item.IItem;
import item.Potion;
import item.PowerStone;
import item.SmokeBombItem;
import strategy.SpeedTurnOrderStrategy;
import ui.CLIView;
import ui.IGameView;

public class GameMain {
    public static void main(String[] args) {
        IGameView ui = new CLIView();
        
        int state = 2; // 1 = Replay, 2 = New Game, 3 = Exit
        
        int classChoice = 1;
        int item1Choice = 1;
        int item2Choice = 1;
        int diffChoice = 1;

        while (state != 3) {
            if (state == 2) {
                ui.showLoadingScreen();
                classChoice = ui.selectClass();
                item1Choice = ui.selectItemPrompt(1);
                item2Choice = ui.selectItemPrompt(2);
                diffChoice = ui.selectDifficulty();
            }

            Player player = (classChoice == 1) ? new Warrior() : new Wizard();
            player.addItem(createItem(item1Choice));
            player.addItem(createItem(item2Choice));

            IAction attackAction = new BasicAttackAction();
            IAction defendAction = new DefendAction();

            BattleEngine engine = new BattleEngine(
                player, diffChoice, ui, new SpeedTurnOrderStrategy(), attackAction, defendAction
            );
            
            engine.start();

            state = ui.getEndGameChoice();
        }
        
        ui.showMessage("Thank you for playing!");
    }

    private static IItem createItem(int choice) {
        if (choice == 1) return new Potion();
        if (choice == 2) return new PowerStone();
        return new SmokeBombItem();
    }
}