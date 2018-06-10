/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micrortssubmission;

import micrortssubmission.enums.UNIT_TYPE;
import rts.GameState;
import rts.PlayerAction;

/**
 *
 * @author leo
 */
public class Commander {

    PlayerAction getAction(GameState gs) {
        PlayerAction pa = new PlayerAction();
        
        UnitQuery unitQuery = new UnitQuery(UNIT_TYPE.WORKER, 0);
        GameStateAnalyser.getUnits(gs, unitQuery);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
