package engine;

import domain.Combatant;
import domain.Player;
import domain.Enemy;
import item.IItem;
import action.IAction;
import strategy.ITurnOrderStrategy;
import ui.IGameView;
import java.util.ArrayList;
import java.util.List;

public class BattleEngine {
    private Player player;
    private List<Enemy> activeEnemies;
    private List<Enemy> backupEnemies;
    private ITurnOrderStrategy turnOrderStrategy;
    private IGameView ui;
    private int roundCounter;
    
    private IAction defaultAttackAction;
    private IAction defaultDefendAction;
    private TurnHandler turnHandler;

    public BattleEngine(Player player, int levelDifficulty, IGameView ui, 
                        ITurnOrderStrategy turnOrderStrategy, 
                        IAction defaultAttackAction, IAction defaultDefendAction) {
        this.player = player;
        this.ui = ui;
        
        this.activeEnemies = LevelManager.getInitialSpawns(levelDifficulty, defaultAttackAction);
        this.backupEnemies = LevelManager.getBackupSpawns(levelDifficulty, defaultAttackAction);
        
        this.turnOrderStrategy = turnOrderStrategy;
        this.defaultAttackAction = defaultAttackAction;
        this.defaultDefendAction = defaultDefendAction;
        this.turnHandler = new TurnHandler();
        this.roundCounter = 1;
    }

    public Player getPlayer() { return player; }
    
    public List<Combatant> getAliveEnemies() {
        List<Combatant> alive = new ArrayList<>();
        for (Enemy e : activeEnemies) if (e.isAlive()) alive.add(e);
        return alive;
    }

    public void start() {
        while (player.isAlive() && (!getAliveEnemies().isEmpty() || !backupEnemies.isEmpty())) {
            ui.showRoundHeader(roundCounter);
            
            if (getAliveEnemies().isEmpty() && !backupEnemies.isEmpty()) {
                ui.showMessage("\n*** BACKUP SPAWN TRIGGERED! ***");
                activeEnemies.addAll(backupEnemies);
                backupEnemies.clear();
            }

            List<Combatant> activeCombatants = new ArrayList<>();
            activeCombatants.add(player);
            activeCombatants.addAll(getAliveEnemies());
            List<Combatant> turnOrder = turnOrderStrategy.determineTurnOrder(activeCombatants);

            ui.showTurnOrder(turnOrder);

            for (Combatant current : turnOrder) {
                if (!current.isAlive()) continue; 
                if (!player.isAlive() || getAliveEnemies().isEmpty()) break; 

                ui.showMessage("\n--- " + current.getName() + "'s Turn ---");

                if (!current.isStunned() && current instanceof Player) {
                    ((Player) current).decreaseCooldown(); 
                }

                if (current.isStunned()) {
                    ui.showMessage(current.getName() + " is STUNNED! Turn skipped.");
                } else {
                	turnHandler.takeTurn(current, this, ui);
                }
            }
            
            if (player.isAlive()) player.tickEffects();
            for (Enemy e : activeEnemies) {
                if (e.isAlive()) e.tickEffects();
            }

            roundCounter++;
        }

        ui.showGameOver(player.isAlive(), player, roundCounter - 1, getAliveEnemies().size());
    }

//    private void takeTurn(Combatant combatant) {
//        if (combatant instanceof Player) {
//            boolean actionTaken = false;
//            while (!actionTaken) {
//                ui.showPlayerStats(player);
//                ui.showEnemyStats(getAliveEnemies());
//                int choice = ui.getPlayerActionChoice();
//
//                if (choice == 1) {
//                    defaultAttackAction.execute(player, this, ui); 
//                    actionTaken = true;
//                } else if (choice == 2) {
//                    defaultDefendAction.execute(player, this, ui); 
//                    actionTaken = true;
//                } else if (choice == 3) {
//                    if (player.getSkillCooldown() > 0) {
//                        ui.showMessage("Skill is on cooldown for " + player.getSkillCooldown() + " turns!");
//                    } else {
//                        player.getSpecialSkill().execute(player, this, ui);
//                        player.setSkillCooldown(3);
//                        actionTaken = true;
//                    }
//                } else if (choice == 4) {
//                    if (player.getInventory().isEmpty()) {
//                        ui.showMessage("Inventory is empty!");
//                    } else {
//                        IItem selectedItem = ui.selectItem(player.getInventory());
//                        if (selectedItem != null) {
//                            selectedItem.use(player, this, ui);
//                            player.removeItem(selectedItem);
//                            actionTaken = true;
//                        }
//                    }
//                }
//            }
//        } else {
//            Enemy enemy = (Enemy) combatant;
//            IAction enemyAction = enemy.getActionStrategy().determineAction(enemy, this);
//            enemyAction.execute(enemy, this, ui);
//        }
//    }
}