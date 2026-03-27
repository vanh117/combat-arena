package ui;

import domain.Combatant;
import domain.Player;
import item.IItem;
import java.util.List;
import java.util.Scanner;

public class CLIView implements IGameView {
    private Scanner scanner;

    public CLIView() { scanner = new Scanner(System.in); }

    public void showMessage(String msg) { System.out.println(msg); }

    public void showLoadingScreen() {
        System.out.println("\n=== Welcome to Turn-Based Combat Arena ===");
        System.out.println("PLAYER ATTRIBUTES:");
        System.out.println("- Warrior | HP: 260 | ATK: 40 | DEF: 20 | SPD: 30 | Skill: Shield Bash (Stun)");
        System.out.println("- Wizard  | HP: 200 | ATK: 50 | DEF: 10 | SPD: 20 | Skill: Arcane Blast (AoE & Buff)");
        System.out.println("\nENEMY ATTRIBUTES:");
        System.out.println("- Goblin  | HP: 55  | ATK: 35 | DEF: 15 | SPD: 25");
        System.out.println("- Wolf    | HP: 40  | ATK: 45 | DEF: 5  | SPD: 35");
        System.out.println("\nDIFFICULTIES:");
        System.out.println("- Easy   : 3 Goblins");
        System.out.println("- Medium : 1 Goblin, 1 Wolf (Backup: 2 Wolves)");
        System.out.println("- Hard   : 2 Goblins (Backup: 1 Goblin, 2 Wolves)\n");
    }

    public int selectClass() {
        System.out.println("Select Class: 1. Warrior  2. Wizard");
        return getIntInput(1, 2);
    }

    public int selectItemPrompt(int slot) {
        System.out.println("Select Item " + slot + ": 1. Potion 2. Power Stone 3. Smoke Bomb");
        return getIntInput(1, 3);
    }

    public int selectDifficulty() {
        System.out.println("Select Difficulty: 1. Easy  2. Medium  3. Hard");
        return getIntInput(1, 3);
    }

    public void showRoundHeader(int round) {
        System.out.println("\n================ ROUND " + round + " ================");
    }

    public void showTurnOrder(List<Combatant> order) {
        System.out.print("Turn Order: ");
        for (int i = 0; i < order.size(); i++) {
            System.out.print(order.get(i).getName() + " (SPD " + order.get(i).getSpeed() + ")");
            if (i < order.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    public void showPlayerStats(Player p) {
        System.out.println("\nPLAYER: " + p.getName() + " | HP: " + p.getHp() + "/" + p.getMaxHp() + " | ATK: " + p.getAttack() + " | DEF: " + p.getDefense() + " | CD: " + p.getSkillCooldown());
    }

    public void showEnemyStats(List<Combatant> enemies) {
        System.out.print("ENEMIES: ");
        for (Combatant e : enemies) {
            System.out.print("[" + e.getName() + " HP: " + e.getHp() + "] ");
        }
        System.out.println();
    }

    public int getPlayerActionChoice() {
        System.out.println("Choose Action: 1. Attack  2. Defend  3. Special Skill  4. Item");
        return getIntInput(1, 4);
    }

    public Combatant selectTarget(List<Combatant> enemies) {
        System.out.println("Select Target:");
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println((i + 1) + ". " + enemies.get(i).getName());
        }
        int choice = getIntInput(1, enemies.size());
        return enemies.get(choice - 1);
    }

    public IItem selectItem(List<IItem> inventory) {
        System.out.println("Select Item (0 to cancel):");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).getName());
        }
        int choice = getIntInput(0, inventory.size());
        if (choice == 0) return null;
        return inventory.get(choice - 1);
    }

    public void showGameOver(boolean isVictory, Player p, int totalRounds, int enemiesRemaining) {
        System.out.println("\n================ GAME OVER ================");
        if (isVictory) {
            System.out.println("Player Victory Screen");
            System.out.println("Congratulations, you have defeated all your enemies.");
            System.out.println("Statistics: Remaining HP: " + p.getHp() + " | Total Rounds: " + totalRounds);
        } else {
            System.out.println("Player Defeat Screen");
            System.out.println("Defeated. Don't give up, try again!");
            System.out.println("Statistics: Enemies remaining: " + enemiesRemaining + " | Total Rounds Survived: " + totalRounds);
        }
    }

    public int getEndGameChoice() {
        System.out.println("\nOptions: 1. Replay (Same Settings)  2. New Game  3. Exit");
        return getIntInput(1, 3);
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                System.out.print(">> ");
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val >= min && val <= max) return val;
                System.out.println("Invalid input. Select between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}