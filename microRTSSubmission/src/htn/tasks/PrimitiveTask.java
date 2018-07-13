package htn.tasks;

import java.util.LinkedList;
import java.util.List;
import util.ExtendedGameState;

/**
 *
 * @author marcel
 */
public abstract class PrimitiveTask extends Task {

    public abstract void execute(ExtendedGameState gs);
    
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        LinkedList<PrimitiveTask> ll = new LinkedList<>();
        ll.add(this);
        return ll;
    }

}
