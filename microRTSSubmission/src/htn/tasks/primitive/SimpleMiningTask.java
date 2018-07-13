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

    @Override
    public List<PrimitiveTask> resolve(ExtendedGameState o) {
        Set<Long> playersWithTask = o.getUnreservedPlayersWithTask(null);
        GameState gs = o.getGameState();
        List<Unit> units = GameStateAnalyser.getUnits(gs, new UnitQuery(UNIT_TYPE.WORKER, GameStateAnalyser.PLAYER));
        for (Unit u : units) {
            if (playersWithTask.contains(u.getID())) {
                if(o.reserveUnit(u.getID())) {
                    reserved.add(u.getID());
                }
            }
        }
        return super.resolve(o); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute(ExtendedGameState egs) {
        GameState gs = egs.getGameState();
        for (Long l : reserved) {
            Unit closestRessource = GameStateAnalyser.getClosestUnit(gs, new UnitQuery(UNIT_TYPE.RESSOURCE, -1), GameStateAnalyser.getPoint(gs.getUnit(l)));
            egs.setAssignment(l, new CollectPlayerTask(GameStateAnalyser.getPoint(closestRessource)));
        }
    }

}
