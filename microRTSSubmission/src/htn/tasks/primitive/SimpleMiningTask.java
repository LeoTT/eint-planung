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
import playertask.CollectPlayerTask;
import rts.GameState;
import rts.units.Unit;
import util.ExtendedGameState;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author marcel
 */
public class SimpleMiningTask extends PrimitiveTask {

    HashSet<Long> reserved;

    public SimpleMiningTask() {
        reserved = new HashSet<>();
    }

    /**
     * Reserviert alle freien Einheiten, die Ernten können.
     * @param egs ExtendedGameState, aud dem reserviert wird.
     * @return Einelementige Liste mit diesem Task als Element.
     */
    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState egs) {
        
        Set<Long> tasklessWorkers = egs.getPlayersWithTask(null, UNIT_TYPE.WORKER);
        
        for (long unitID : tasklessWorkers) {
            if (egs.reserveUnit(unitID)) {
                reserved.add(unitID);
            }
        }

        return super.resolve(egs);
    }

    /**
     * Fügt für alle reservierten Einheiten einen Ernte-Task in den ExtendedGameState hinzu.
     * Der Commander wird diesen anschließend zur Suche benutzen.
     * @param egs der zu manipuliere ExtendedGameState.
     */
    @Override
    public void execute(ExtendedGameState egs) {
        GameState gs = egs.getGameState();
        for (Long l : reserved) {
            Unit closestRessource = GameStateAnalyser.getClosestUnit(gs, new UnitQuery(UNIT_TYPE.RESSOURCE, -1), GameStateAnalyser.getPoint(gs.getUnit(l)));
            egs.setAssignment(l, new CollectPlayerTask(GameStateAnalyser.getPoint(closestRessource)));
        }
    }

}
