/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.compound;

import htn.Method;
import htn.condition.AlwaysTrueCondition;
import htn.condition.Condition;
import htn.condition.LackOfRessourceCondition;
import htn.condition.LackOfUnitCondition;
import htn.tasks.CompoundTask;
import htn.tasks.primitive.TrainRandomAttackUnit_primitive;
import htn.tasks.primitive.SimpleMiningTask;
import java.util.Arrays;
import java.util.List;
import micrortssubmission.enums.UNIT_TYPE;
import util.ExtendedGameState;

/**
 *
 * @author l
 */
public class TrainAttackUnit_compound extends CompoundTask {

    @Override
    public List<Method> getMethods() {
        
        Method buildBarracksMethod = Method.constructSingularTaskMethod(new LackOfUnitCondition(UNIT_TYPE.BARRACKS),
                new BuildBarracks_compound());

        Method lackOfRessourceMethod = Method.constructSingularTaskMethod(new LackOfRessourceCondition(2), 
                new Harvest_compound());
        
        Method buildAttackUnitMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new TrainRandomAttackUnit_primitive());

        List<Method> methods = Arrays.asList(buildBarracksMethod,
                lackOfRessourceMethod,
                buildAttackUnitMethod);
        return methods;
    }

}
