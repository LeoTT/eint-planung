/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micrortssubmission;

import ai.core.AI;
import ai.core.AIWithComputationBudget;
import ai.core.ParameterSpecification;
import java.util.List;
import java.util.ArrayList;
import rts.GameState;
import rts.PhysicalGameState;
import rts.Player;
import rts.PlayerAction;
import rts.units.Unit;
import rts.units.UnitTypeTable;



public class MicroRTSSubmission extends AIWithComputationBudget {
    UnitTypeTable m_utt = null;
            
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
        PlayerAction pa = new PlayerAction();
        pa.fillWithNones(gs, player, 10);
        UnitQuery queryObj = new UnitQuery(GameStateAnalyser.BASE_ID, GameStateAnalyser.PLAYER_ID_ME);        
        System.out.println(GameStateAnalyser.getUnits(gs, queryObj));
        return pa;
    }    
    
    // This will be called by the microRTS GUI to get the
    // list of parameters that this bot wants exposed
    // in the GUI.
    public List<ParameterSpecification> getParameters() {
        return new ArrayList<>();
    }
}
    

