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
import htn.tasks.primitive.BuildRandomAttackUnit_primitive;
import htn.tasks.primitive.SimpleMiningTask;
import java.util.Arrays;
import java.util.List;
import micrortssubmission.enums.UNIT_TYPE;
import util.ExtendedGameState;

/**
 *
 * @author l
 */
public class BuildAttackUnit_compound extends CompoundTask {



    @Override
    public List<Method> getMethods() {
        
        Method buildBarracksMethod = Method.constructSingularTaskMethod(new NoBarracksCondition(),
                new BuildBarracks_compound());

        // TODO no resources

        Method buildAttackUnitMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new BuildRandomAttackUnit_primitive());

        List<Method> methods = Arrays.asList(buildBarracksMethod,
                buildAttackUnitMethod);
        return methods;
    }

}

class NoBarracksCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState egs) {
        boolean noBarracks = egs.getPlayersWithTask(null, UNIT_TYPE.BARRACKS).isEmpty();
        return noBarracks;
    }
    
}