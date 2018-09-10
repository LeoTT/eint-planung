/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.compound;

import htn.tasks.CompoundTask;
import htn.Method;
import htn.condition.AlwaysTrueCondition;
import htn.condition.Condition;
import htn.condition.AndCondition;
import htn.condition.LackOfUnitCondition;
import htn.condition.LessThanXUnits;
import htn.tasks.primitive.SimpleMiningTask;
import java.util.Arrays;
import java.util.List;
import micrortssubmission.enums.UNIT_TYPE;
import util.ExtendedGameState;

/**
 *
 * @author marcel
 */
public class Harvest_compound extends CompoundTask {    
    
    /**
     * Erzeugt die Methoden, die dieser CompoundTask hat.
     * @return Methoden vom Harvest CompoundTask
     */
    public List<Method> getMethods() {

        Method buildWorkerMethod = Method.constructSingularTaskMethod(
                    new AndCondition(
                        new LackOfUnitCondition(UNIT_TYPE.WORKER),
                        new LessThanXUnits(UNIT_TYPE.WORKER, 3)),
                    new TrainWorker_compound());

        Method buildBaseMethod = Method.constructSingularTaskMethod(new NoBaseCondition(),
                new BuildBase_compound());

        // TODO Method noRessourcesMethod? 
        
        Method harvestMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new SimpleMiningTask());

        List<Method> methods = Arrays.asList(buildWorkerMethod,
                                             buildBaseMethod,   
                                             harvestMethod);

        return methods;
    }
    
}

class NoBaseCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState gs) {
        
        boolean noAvailableBase = gs.getPlayersWithTask(null, UNIT_TYPE.BASE).isEmpty();
        return noAvailableBase;
    }
    
}
