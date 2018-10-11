/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.condition;

import htn.condition.Condition;
import micrortssubmission.enums.UNIT_TYPE;
import util.ExtendedGameState;

/**
 *
 * @author l
 */
public class LackOfUnitCondition extends Condition {
    
    private UNIT_TYPE unitType;
    
    public LackOfUnitCondition(UNIT_TYPE unitType) {
        this.unitType = unitType;
    }

    @Override
    public boolean conditionFulfilled(ExtendedGameState egs) {
        boolean noUnitAvailable = egs.getPlayersWithTask(null, unitType).isEmpty();
        return noUnitAvailable;       
    }
}
