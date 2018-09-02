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
            System.out.println(unitID);
            if (egs.reserveUnit(unitID)) {
                reserved.add(unitID);
            }
        }

        return super.resolve(egs);
    }

    @Override
    public void execute(ExtendedGameState egs) {
        GameState gs = egs.getGameState();
        for (Long l : reserved) {
            egs.setAssignment(l, new BuildWorkerPlayerTask());
//            Unit closestRessource = GameStateAnalyser.getClosestUnit(gs, new UnitQuery(UNIT_TYPE.RESSOURCE, -1), GameStateAnalyser.getPoint(gs.getUnit(l)));
//            egs.setAssignment(l, new CollectPlayerTask(GameStateAnalyser.getPoint(closestRessource)));
        }
    }
}
