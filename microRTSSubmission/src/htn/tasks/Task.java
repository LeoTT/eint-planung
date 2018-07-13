package htn.tasks;

import java.util.List;
import util.ExtendedGameState;

/**
 *
 * @author marcel
 */
public abstract class Task {
    
   public abstract List<PrimitiveTask> resolve(ExtendedGameState o);
    
}
