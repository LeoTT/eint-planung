/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.compound;

import htn.Method;
import htn.condition.AlwaysTrueCondition;
import htn.tasks.CompoundTask;
import htn.tasks.primitive.BuildBarracks_primitive;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author l
 */
public class BuildBarracks_compound extends CompoundTask{

    @Override
    public List<Method> getMethods() {
        Method buildWorkerMethod = Method.constructSingularTaskMethod(new NoWorkerCondition(),
                new BuildWorker_compound());
        
        // TODO no ressources
        
        Method buildBarracksMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new BuildBarracks_primitive());

        List<Method> methods = Arrays.asList(buildWorkerMethod,
                                             buildBarracksMethod);

        return methods;
    }
    
}
