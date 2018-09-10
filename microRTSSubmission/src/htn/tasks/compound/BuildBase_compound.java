/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.compound;

import htn.Method;
import htn.condition.AlwaysTrueCondition;
import htn.condition.LackOfRessourceCondition;
import htn.condition.LackOfUnitCondition;
import htn.tasks.CompoundTask;
import htn.tasks.primitive.BuildBase_primitive;
import htn.tasks.primitive.SimpleMiningTask;
import java.util.Arrays;
import java.util.List;
import micrortssubmission.enums.UNIT_TYPE;

/**
 *
 * @author l
 */
public class BuildBase_compound extends CompoundTask {

    @Override
    public List<Method> getMethods() {
        Method buildWorkerMethod = Method.constructSingularTaskMethod(new LackOfUnitCondition(UNIT_TYPE.WORKER),
                new TrainWorker_compound());

        Method lackOfRessourceMethod = Method.constructSingularTaskMethod(new LackOfRessourceCondition(10),
                new Harvest_compound());

        Method buildBaseMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new BuildBase_primitive());

        List<Method> methods = Arrays.asList(buildWorkerMethod,
                lackOfRessourceMethod,
                buildBaseMethod);

        return methods;
    }

}
