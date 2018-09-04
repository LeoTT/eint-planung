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
import rts.GameState;
import rts.units.Unit;
import util.ExtendedGameState;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author l
 */
public class BuildBarracks_primitive extends PrimitiveTask {

    HashSet<Long> reserved = new HashSet<>();   

    @Override
    public void execute(ExtendedGameState egs) {
        GameState gs = egs.getGameState();
        for (Long l : reserved) {
            Unit worker = gs.getUnit(l);
            Point targetPoint = GameStateAnalyser.getNearestFreePoint(gs, GameStateAnalyser.getPoint(worker));
            egs.setAssignment(l, new BuildPlayerTask(UNIT_TYPE.BARRACKS, targetPoint));
        }
    }

    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        Set<Long> tasklessWorkers = egs.getPlayersWithTask(null, UNIT_TYPE.WORKER);
        
        if (tasklessWorkers.size() > 0) {
            Long tasklessWorker = tasklessWorkers.iterator().next();
            if (egs.reserveUnit(tasklessWorker)) {
                reserved.add(tasklessWorker);
            }
        }
        return super.resolve(egs);
    }   
}
