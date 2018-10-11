/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playertask;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import micrortssubmission.enums.UNIT_TYPE;
import rts.GameState;
import rts.UnitAction;
import rts.units.Unit;
import rts.units.UnitTypeTable;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author l
 */
public class TrainUnitPlayerTask extends AbstractPlayerTask {
    
    private UNIT_TYPE unitType;
    
    @Override
    public int estimateTime() {
        return 100;
    }
    
    @Override public int requiredRessources() {
        return 2;
    }
    
    public TrainUnitPlayerTask(UNIT_TYPE unitType) {
        this.unitType = unitType;
    }
    
    @Override
    public float eval(GameState gs, Unit playerUnit) {
        UnitQuery unitQuery = new UnitQuery(unitType, playerUnit.getPlayer());
        List<Unit> workers = GameStateAnalyser.getUnits(gs, unitQuery);
        
        return workers.size();
    }

    @Override
    public Set<Integer> getPermittedActionIDs() {
        HashSet set = new HashSet();
        set.add(UnitAction.TYPE_PRODUCE);
        return set;
    }
    
}
