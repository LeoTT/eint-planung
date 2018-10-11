/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.condition;

import htn.condition.Condition;
import rts.PhysicalGameState;
import rts.Player;
import util.ExtendedGameState;
import util.GameStateAnalyser;

/**
 *
 * @author l
 */
public class LackOfRessourceCondition extends Condition {
   
    private int requiredRessources;
    
    public LackOfRessourceCondition(int requiredRessources) {
        this.requiredRessources = requiredRessources;
    }

    @Override
    public boolean conditionFulfilled(ExtendedGameState egs) {
        
        // getting the ressources from egs, which remembers cost of previously planned tasks
        int ressources = egs.getRessources();
        boolean tooLittleRessources = (ressources - requiredRessources) < 0;
        return tooLittleRessources;
    }
}