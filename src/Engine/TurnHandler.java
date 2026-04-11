package engine;

import domain.Combatant;
import ui.IGameView;
import domain.Player;
import action.BasicAttackAction;
import action.DefendAction;
import domain.Enemy;
import action.IAction;

public class TurnHandler {
	public void takeTurn(Combatant combatant, BattleEngine engine, IGameView ui) {
        if (combatant instanceof Player) {
            handlePlayerTurn((Player) combatant, engine, ui);
        } else {
        	Enemy enemy = (Enemy) combatant;
        	IAction enemyAction = enemy.getActionStrategy().determineAction(enemy, engine);
        	enemyAction.execute(enemy, engine, ui);
        }
    }

    private void handlePlayerTurn(Player player, BattleEngine engine, IGameView ui) {
        boolean actionTaken = false;

        while (!actionTaken) {
            ui.showPlayerStats(player);
            ui.showEnemyStats(engine.getAliveEnemies());
            int choice = ui.getPlayerActionChoice();

            if (choice == 1) {
                new BasicAttackAction().execute(player, engine, ui);
                actionTaken = true;
            } else if (choice == 2) {
                new DefendAction().execute(player, engine, ui);
                actionTaken = true;
            } else if (choice == 3) {
                if (player.getSkillCooldown() > 0) {
                    ui.showMessage("Skill is on cooldown for " + player.getSkillCooldown() + " turns!");
                } else {
                    player.getSpecialSkill().execute(player, engine, ui);
                    player.setSkillCooldown(3);
                    actionTaken = true;
                }
            } else if (choice == 4) {
                if (player.getInventory().isEmpty()) {
                    ui.showMessage("Inventory is empty!");
                } else {
                    var selectedItem = ui.selectItem(player.getInventory());
                    if (selectedItem != null) {
                        selectedItem.use(player, engine, ui);
                        player.removeItem(selectedItem);
                        actionTaken = true;
                    }
                }
            }
        }
    }

}
