/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn;

import htn.condition.Condition;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author marcel
 */
class Method {

    Condition condition;
    List<Task> taskList;

    public Method(Condition condition, List<Task> taskList) {
        this.condition = condition;
        this.taskList = taskList;
    }

    public List<PrimitiveTask> resolveTasks(Object o) throws UnresolvableException {
        if (!condition.conditionFulfilled(o)) {
            // Methode ist nicht anwendbar
            throw new UnresolvableException();
        }

        LinkedList primTasks = new LinkedList();
        for (Task t : taskList) {
            List<PrimitiveTask> resolve = t.resolve(o);
            if(resolve == null) {
                // Ein CompoundTask lässt sich auf keine Art auflösen, Methode failed
                throw new UnresolvableException();
            }
            primTasks.addAll(resolve);
        }
        return primTasks;
    }

}
