package ui;

import domain.Combatant;
import domain.Player;
import item.IItem;
import java.util.List;

public interface IGameView {
    void showMessage(String msg);
    void showLoadingScreen();
    int selectClass();
    int selectItemPrompt(int slot);
    int selectDifficulty();
    void showRoundHeader(int round);
    void showTurnOrder(List<Combatant> order);
    void showPlayerStats(Player p);
    void showEnemyStats(List<Combatant> enemies);
    int getPlayerActionChoice();
    Combatant selectTarget(List<Combatant> enemies);
    IItem selectItem(List<IItem> inventory);
    void showGameOver(boolean isVictory, Player p, int totalRounds, int enemiesRemaining);
    int getEndGameChoice();
}