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
import static rts.units.UnitTypeTable.VERSION_ORIGINAL;
import static rts.units.UnitTypeTable.VERSION_ORIGINAL_FINETUNED;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author l
 */
public class TrainWorkerPlayerTask extends AbstractPlayerTask {

        @Override
    public int estimateTime() {
        
                /*    case VERSION_ORIGINAL: base.produceTime = 250;
                                   break;
            case VERSION_ORIGINAL_FINETUNED: base.produceTime = 200;
                                   break; */
        return 50;
    }
    @Override
    public float eval(GameState gs, Unit playerUnit) {
        UnitQuery unitQuery = new UnitQuery(UNIT_TYPE.WORKER, playerUnit.getPlayer());
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
