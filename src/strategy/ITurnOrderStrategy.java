package strategy;

import domain.Combatant;
import java.util.List;

public interface ITurnOrderStrategy {
    /**
     * Determine order of turn taking
     */
    List<Combatant> determineTurnOrder(List<Combatant> combatants);
}