/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.primitive;

import htn.tasks.PrimitiveTask;
import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import micrortssubmission.enums.UNIT_TYPE;
import playertask.BuildPlayerTask;
import playertask.BuildWorkerPlayerTask;
import rts.GameState;
import rts.units.Unit;
import util.ExtendedGameState;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author marcel
 */
public class BuildBase_primitive extends PrimitiveTask {

    HashSet<Long> reserved = new HashSet<>();   
    
    @Override
    public void execute(ExtendedGameState egs) {
        GameState gs = egs.getGameState();
        for (Long l : reserved) {
            Unit worker = gs.getUnit(l);
            UnitQuery ressourceQuery = new UnitQuery(UNIT_TYPE.RESSOURCE, -1);
            Unit closestRessource = GameStateAnalyser.getClosestUnit(gs, ressourceQuery, GameStateAnalyser.getPoint(worker));
            Point targetPoint = GameStateAnalyser.getNearestFreePoint(gs, GameStateAnalyser.getPoint(closestRessource));
            egs.setAssignment(l, new BuildPlayerTask(UNIT_TYPE.BASE, targetPoint));
        }
    }

    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        
        Set<Long> tasklessWorkers = egs.getPlayersWithTask(null, UNIT_TYPE.WORKER);
        
        // TODO search for worker close to ressources
        if (tasklessWorkers.size() > 0) {
            Long tasklessWorker = tasklessWorkers.iterator().next();
            if (egs.reserveUnit(tasklessWorker)) {
                reserved.add(tasklessWorker);
            }
        }

        return super.resolve(egs);
    }   
}
