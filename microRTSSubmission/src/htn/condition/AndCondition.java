/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.condition;

import htn.condition.Condition;
import util.ExtendedGameState;

/**
 *
 * @author l
 */
public class AndCondition extends Condition {
    
    private Condition a;
    private Condition b;
    
    public AndCondition(Condition a, Condition b) {

        this.a = a;
        this.b = b;
    }

    @Override
    public boolean conditionFulfilled(ExtendedGameState egs) {
        boolean bothAreTrue = a.conditionFulfilled(egs) && b.conditionFulfilled(egs);
                
        return bothAreTrue;
    }
}
