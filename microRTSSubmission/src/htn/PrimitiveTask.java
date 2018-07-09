package htn;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author marcel
 */
abstract class PrimitiveTask extends Task {

    //TODO: Welche Parameter braucht Execute? Oder Parameter lieber in Konstruktor?
    abstract void execute();

    @Override
    public List<PrimitiveTask> resolve(Object o) {
        LinkedList<PrimitiveTask> ll =  new LinkedList();
        ll.add(this);
        return ll;
    }
    
    
    
}
