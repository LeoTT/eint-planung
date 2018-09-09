/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.compound;

import htn.Method;
import htn.condition.AlwaysTrueCondition;
import htn.condition.Condition;
import htn.tasks.CompoundTask;
import htn.tasks.primitive.TrainWorker_primitive;
import java.util.Arrays;
import java.util.List;
import util.ExtendedGameState;

/**
 *
 * @author l
 */
public class TrainWorker_compound extends CompoundTask {
    
    public List<Method> getMethods() {

        Method buildBaseMethod = Method.constructSingularTaskMethod(new BuildBaseCondition(),
                new BuildBase_compound());

        Method harvestMethod = Method.constructSingularTaskMethod(new HarvestCondition(),
                new Harvest_compound());

        Method buildWorkerMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new TrainWorker_primitive());

        List<Method> methods = Arrays.asList(buildBaseMethod, harvestMethod, buildWorkerMethod);
        
        return methods;
    }
}

class BuildBaseCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState gs) {
        return false;
    }
    
}

class HarvestCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState gs) {
        return false;
    }
    
}