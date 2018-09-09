/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.tasks.primitive;

import htn.tasks.PrimitiveTask;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import micrortssubmission.enums.UNIT_TYPE;
import playertask.TrainUnitPlayerTask;
import playertask.TrainWorkerPlayerTask;
import util.ExtendedGameState;

/**
 *
 * @author l
 */
public class TrainRandomAttackUnit_primitive extends PrimitiveTask {

    HashSet<Long> reserved;

    public TrainRandomAttackUnit_primitive() {
        reserved = new HashSet<>();
    }

    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        
        Set<Long> tasklessBarracks = egs.getPlayersWithTask(null, UNIT_TYPE.BARRACKS);
        
        if (tasklessBarracks.size() > 0) {
            Long tasklessBarrack = tasklessBarracks.iterator().next();
            if (egs.reserveUnit(tasklessBarrack)) {
                reserved.add(tasklessBarrack);
            }
        }
        return super.resolve(egs);
    }

    @Override
    public void execute(ExtendedGameState egs) {
        for (Long l : reserved) {
            egs.setAssignment(l, new TrainUnitPlayerTask(UNIT_TYPE.LIGHT));
        }
    }
    
}
