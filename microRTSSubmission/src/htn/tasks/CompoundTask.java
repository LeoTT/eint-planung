
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks;

import htn.Method;
import htn.tasks.PrimitiveTask;
import htn.tasks.Task;
import java.util.LinkedList;
import java.util.List;
import util.ExtendedGameState;

/**
 *
 * @author marcel
 */
public abstract class CompoundTask extends Task{
    
    public abstract List<Method> getMethods();
    
    /**
     * 
     * @param egs
     * @return null if no valid method is found or every valid method 
     * resolves to null. Otherwise list of primitive tasks.
     */
    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        
        List<Method> availableMethods = getMethods();
        
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