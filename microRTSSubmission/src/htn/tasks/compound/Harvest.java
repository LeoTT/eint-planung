/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.compound;

import htn.tasks.CompoundTask;
import htn.Method;
import htn.tasks.PrimitiveTask;
import htn.condition.Condition;
import htn.tasks.Task;
import htn.tasks.primitive.SimpleMiningTask;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import micrortssubmission.enums.UNIT_TYPE;
import playertask.CollectPlayerTask;
import rts.units.Unit;
import util.ExtendedGameState;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author marcel
 */
public class Harvest extends CompoundTask {    
    
    public Harvest() {
        //Stützt sich auf die Standardauflöselogik. Man muss nur die Methodenliste übergeben.
        super(getMethods());
    }
    
    /**
     * Erzeugt die Methoden, die dieser CompoundTask hat.
     * @return Methoden vom Harvest CompoundTask
     */
    private static List<Method> getMethods() {
        
        /**
         * construct first method.
         */
        Condition c = new HarvestCondition(); 
        List<Task> tasks = Arrays.asList(new SimpleMiningTask());
        Method harvestMethod = new Method(c, tasks);
        
        /** 
         * construct list of methods
         */
        List<Method> methods = Arrays.asList(harvestMethod);

        return methods;
    }
    
}

class HarvestCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState gs) {
        Set<Long> set = gs.getPlayersWithTask(null);
        set.addAll(gs.getPlayersWithTask(CollectPlayerTask.class));
        List<Unit> units = GameStateAnalyser.getUnits(gs.getGameState(), new UnitQuery(UNIT_TYPE.RESSOURCE));

        return units.size() < 2 * set.size();
    }
    
}