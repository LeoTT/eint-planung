package htn;

import java.util.List;

/**
 *
 * @author marcel
 */
public abstract class Task {
    
   public abstract List<PrimitiveTask> resolve(Object o);
    
}
