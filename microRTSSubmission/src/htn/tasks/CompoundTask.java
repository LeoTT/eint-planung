/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks;

import htn.Method;
import htn.UnresolvableException;
import htn.tasks.PrimitiveTask;
import htn.tasks.Task;
import java.util.LinkedList;
import java.util.List;
import util.ExtendedGameState;

/**
 *
 * @author marcel
 */
public class CompoundTask extends Task{
    
    List<Method> availableMethods;

    public CompoundTask()
    {
        availableMethods = new LinkedList<>();
    }   
    
    public CompoundTask(List<Method> availableMethods) {
        this.availableMethods = availableMethods;
    }

    /**
     * 
     * @param egs
     * @return null if no valid method is found or every valid method 
     * resolves to null. Otherwise list of primitive tasks.
     */
    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        
        for(Method m : availableMethods) {
            if (m.conditionFulfilled(egs)) {
                List<PrimitiveTask> primitiveTasks = m.resolveTasks(egs);
                if (primitiveTasks != null) {
                    return m.resolveTasks(egs);
                }
            }              
        }
        return null;
    }    
}
