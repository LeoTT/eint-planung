/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn;

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
    
    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState o) {
        for(Method m : availableMethods) {
            try {
                List<PrimitiveTask> ll = m.resolveTasks(o);
                return ll;                
            } catch(UnresolvableException ex) {                
            }
        }
        return null;
    }    
}
