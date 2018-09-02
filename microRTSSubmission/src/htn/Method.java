/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn;

import htn.tasks.PrimitiveTask;
import htn.tasks.Task;
import htn.condition.Condition;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import util.ExtendedGameState;

/**
 *
 * @author marcel
 */
public class Method {

    private Condition condition;
    private List<Task> taskList;

    public Method(Condition condition, List<Task> taskList) {
        this.condition = condition;
        this.taskList = taskList;
    }

    /**
     * checks whether method is viable, based on ExtendedGameState.
     * e.g. an attack method may only be viable if you have the required units.
     * @param egs
     * @return 
     */
    public boolean conditionFulfilled(ExtendedGameState egs) {
        
        return condition.conditionFulfilled(egs);
    }

    /**
     * 
     * @param egs
     * @return null if one of the tasks resolves to null, otherwise list of 
     * resolved primitive tasks
     */
    public List<PrimitiveTask> resolveTasks(ExtendedGameState egs) {

        LinkedList primTasks = new LinkedList();
        for (Task t : taskList) {
            List<PrimitiveTask> resolve = t.resolve(egs);
            if(resolve == null) {
                // this happens when a compound task is unresolvable
                return null;
            }
            primTasks.addAll(resolve);
        }
        return primTasks;
    }
    
    public static Method constructSingularTaskMethod(Condition c, Task t) {
        
        return new Method(c, Arrays.asList(t));
    }

}