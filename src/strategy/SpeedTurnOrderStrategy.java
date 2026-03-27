package strategy;

import domain.Combatant;
import java.util.ArrayList;
import java.util.List;

public class SpeedTurnOrderStrategy implements ITurnOrderStrategy {
    
    @Override
    public List<Combatant> determineTurnOrder(List<Combatant> combatants) {
        List<Combatant> turnOrder = new ArrayList<>(combatants);
        
        turnOrder.sort((c1, c2) -> Integer.compare(c2.getSpeed(), c1.getSpeed()));
        
        return turnOrder;
    }
}