/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.compound;

import htn.Method;
import htn.condition.AlwaysTrueCondition;
import htn.condition.AndCondition;
import htn.condition.LackOfRessourceCondition;
import htn.condition.LackOfUnitCondition;
import htn.condition.LessThanXUnits;
import htn.tasks.CompoundTask;
import htn.tasks.primitive.BuildBarracks_primitive;
import java.util.Arrays;
import java.util.List;
import micrortssubmission.enums.UNIT_TYPE;

/**
 *
 * @author l
 */
public class BuildBarracks_compound extends CompoundTask{

    @Override
    public List<Method> getMethods() {
        Method buildWorkerMethod = Method.constructSingularTaskMethod(
                    new AndCondition(
                        new LackOfUnitCondition(UNIT_TYPE.WORKER),
                        new LessThanXUnits(UNIT_TYPE.WORKER, 3)),
                    new TrainWorker_compound());
        
        Method lackOfRessourceMethod = Method.constructSingularTaskMethod(new LackOfRessourceCondition(5), 
                new Harvest_compound());
        
        Method buildBarracksMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new BuildBarracks_primitive());

        List<Method> methods = Arrays.asList(lackOfRessourceMethod,
                buildWorkerMethod,
                buildBarracksMethod);

        return methods;
    }
    
}

