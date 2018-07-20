/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.compound;

import htn.CompoundTask;
import htn.Method;
import htn.tasks.PrimitiveTask;
import htn.condition.Condition;
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
public class Harvest extends CompoundTask{    
    
    public Harvest() {
        //Stützt sich auf die Standardauflöselogik. Man muss nur die Methodenliste übergeben.
        super(getMethods());
    }
    
    /**
     * Erzeugt die Methoden, die dieser CompoundTask hat.
     * @return Methoden vom Harvest CompoundTask
     */
    private static LinkedList<Method> getMethods() {
        LinkedList<Method> methods = new LinkedList<>();
        Condition c = new Condition() {
            // Anonyme Condition, um zu prüfen, ob man seinen Anteil an Minen mit Workern belegen kann
            @Override
            public boolean conditionFulfilled(ExtendedGameState o) {
                Set<Long> set = o.getPlayersWithTask(null);
                set.addAll(o.getPlayersWithTask(CollectPlayerTask.class));
                List<Unit> units = GameStateAnalyser.getUnits(o.getGameState(), new UnitQuery(UNIT_TYPE.RESSOURCE, -1));
                
                return units.size() < 2 * set.size();
            }
        };
        LinkedList<PrimitiveTask> tasks = new LinkedList<>();
        
        //methods.add(new Method(c, ))
        return methods;
    }
    
}
