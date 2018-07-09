/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn;

import java.util.LinkedList;
import java.util.List;

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
    public List<PrimitiveTask> resolve(Object o) {
        for(Method m : availableMethods) {
            try {
                List<PrimitiveTask> l = m.resolveTasks(o);
                return l;                
            } catch(UnresolvableException ex) {                
            }
        }
        return null;
    }    
}
