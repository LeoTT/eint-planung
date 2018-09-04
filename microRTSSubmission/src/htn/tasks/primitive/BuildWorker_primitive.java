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
import playertask.BuildWorkerPlayerTask;
import playertask.CollectPlayerTask;
import rts.GameState;
import rts.units.Unit;
import util.ExtendedGameState;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author l
 */
public class BuildWorker_primitive extends PrimitiveTask {

    HashSet<Long> reserved;

    public BuildWorker_primitive() {
        reserved = new HashSet<>();
    }

    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        
        Set<Long> tasklessBases = egs.getPlayersWithTask(null, UNIT_TYPE.BASE);
        
        for (long unitID : tasklessBases) {
            if (egs.reserveUnit(unitID)) {
                reserved.add(unitID);
            }
        }

        return super.resolve(egs);
    }

    @Override
    public void execute(ExtendedGameState egs) {
        for (Long l : reserved) {
            egs.setAssignment(l, new BuildWorkerPlayerTask());
        }
    }
}
