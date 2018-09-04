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
import htn.tasks.primitive.BuildBarracks_primitive;
import java.util.Arrays;
import java.util.List;
import micrortssubmission.enums.UNIT_TYPE;
import util.ExtendedGameState;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author l
 */
public class Attack_compound extends CompoundTask {

    @Override
    public List<Method> getMethods() {
        Method buildWorkerMethod = Method.constructSingularTaskMethod(new ExistingEnemyWorkersCondition(),
                new KillWorkers_compound());
        
        // TODO no ressources
        
        Method buildBarracksMethod = Method.constructSingularTaskMethod(new AlwaysTrueCondition(),
                new BuildBarracks_primitive());

        List<Method> methods = Arrays.asList(buildWorkerMethod,
                                             buildBarracksMethod);

        return methods;
    }
    
}

class ExistingEnemyWorkersCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState egs) {
        UnitQuery unitQuery = new UnitQuery(UNIT_TYPE.WORKER, GameStateAnalyser.ENEMY);
        boolean enemyHasWorkers = GameStateAnalyser.getUnits(egs.getGameState(), unitQuery) == null;
        
        return enemyHasWorkers;
    }
    
}

class ExistingEnemyBasesCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState egs) {
        UnitQuery unitQuery = new UnitQuery(UNIT_TYPE.BASE, GameStateAnalyser.ENEMY);
        boolean enemyHasBase = GameStateAnalyser.getUnits(egs.getGameState(), unitQuery) == null;
        
        return enemyHasBase;
    }
    
}