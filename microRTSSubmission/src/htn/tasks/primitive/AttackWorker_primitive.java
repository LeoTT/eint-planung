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
import playertask.AttackPlayerTask;
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
public class AttackWorker_primitive extends PrimitiveTask {

    HashSet<Long> reserved = new HashSet<>();   

    @Override
    public void execute(ExtendedGameState egs) {
        GameState gs = egs.getGameState();
        for (Long l : reserved) {
            Unit unit = gs.getUnit(l);
            UnitQuery enemyWorkerQuery = new UnitQuery(UNIT_TYPE.WORKER, GameStateAnalyser.ENEMY);
            Unit closestEnemyWorker = GameStateAnalyser.getClosestUnit(gs, enemyWorkerQuery, GameStateAnalyser.getPoint(unit));
            if (closestEnemyWorker != null) {
                egs.setAssignment(l, new AttackPlayerTask(closestEnemyWorker));
            }
        }
    }

    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        
        Set<Long> tasklessFighter = egs.getPlayersWithTask(null, UNIT_TYPE.LIGHT);
        tasklessFighter.addAll(egs.getPlayersWithTask(null, UNIT_TYPE.HEAVY));
        tasklessFighter.addAll(egs.getPlayersWithTask(null, UNIT_TYPE.RANGED));

        
        if (tasklessFighter.size() > 0) {
            Long tasklessWorker = tasklessFighter.iterator().next();
            if (egs.reserveUnit(tasklessWorker)) {
                reserved.add(tasklessWorker);
            }
        }

        return super.resolve(egs);
    }   
}
