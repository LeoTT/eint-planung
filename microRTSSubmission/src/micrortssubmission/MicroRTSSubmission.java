/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micrortssubmission;

import util.GameStateAnalyser;
import util.UnitQuery;
import micrortssubmission.enums.UNIT_TYPE;
import ai.core.AI;
import ai.core.AIWithComputationBudget;
import ai.core.ParameterSpecification;
import java.util.List;
import java.util.ArrayList;
import rts.GameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;



public class MicroRTSSubmission extends AIWithComputationBudget {
    UnitTypeTable m_utt = null;
    Commander commander = new Commander();
    
    // This is the default constructor that microRTS will call:
    public MicroRTSSubmission(UnitTypeTable utt) {
        super(-1,-1);
        m_utt = utt;
    }

    // This will be called by microRTS when it wants to create new instances of this bot (e.g., to play multiple games).
    public AI clone() {
        return new MicroRTSSubmission(m_utt);
    }
    
    // This will be called once at the beginning of each new game:    
    public void reset() {
    }
       
    // Called by microRTS at each game cycle.
    // Returns the action the bot wants to execute.
    public PlayerAction getAction(int player, GameState gs) {
        return this.commander.getAction(player,gs);
    }    
    
    // This will be called by the microRTS GUI to get the
    // list of parameters that this bot wants exposed
    // in the GUI.
    public List<ParameterSpecification> getParameters() {
        return new ArrayList<>();
    }
}
    

