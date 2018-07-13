package htn.condition;

import util.ExtendedGameState;

/**
 *
 * @author marcel
 */
public abstract class Condition {
    
    public abstract boolean conditionFulfilled(ExtendedGameState gs);
    
}
