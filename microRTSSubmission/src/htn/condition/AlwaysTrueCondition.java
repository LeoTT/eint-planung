/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.condition;

import util.ExtendedGameState;

/**
 *
 * @author l
 */
public class AlwaysTrueCondition extends Condition {

    @Override
    public boolean conditionFulfilled(ExtendedGameState gs) {
        return true;
    }
    
}
