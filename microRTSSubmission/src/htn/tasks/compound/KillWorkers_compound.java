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
import htn.tasks.primitive.SimpleMiningTask;
import java.util.Arrays;
import java.util.List;
import micrortssubmission.enums.UNIT_TYPE;
import util.ExtendedGameState;

/**
 *
 * @author l
 */
public class KillWorkers_compound extends CompoundTask{

    @Override
    public List<Method> getMethods() {

        
        Method buildAttackerMethod = Method.constructSingularTaskMethod(new NoAttackUnitCondition(),
                new TrainAttackUnit_compound());


        Method killWorkersMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new KillWorkers_compound());

        List<Method> methods = Arrays.asList(buildAttackerMethod,
                                             killWorkersMethod);

        return methods;
    }
    
}

class NoAttackUnitCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState egs) {
        boolean noAvailableAttackUnit = egs.getPlayersWithTask(null, UNIT_TYPE.LIGHT).isEmpty()
                && egs.getPlayersWithTask(null, UNIT_TYPE.HEAVY).isEmpty()
                && egs.getPlayersWithTask(null, UNIT_TYPE.RANGED).isEmpty();
        return noAvailableAttackUnit;
        
    }
}