/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htn.condition;

import htn.condition.Condition;
import micrortssubmission.enums.UNIT_TYPE;
import util.ExtendedGameState;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author l
 */
public class MoreThanXUnits extends Condition {

    private UNIT_TYPE unitType;
    private int number;
    private int team;

    public MoreThanXUnits(UNIT_TYPE unitType, int number) {
        this.unitType = unitType;
        this.number = number;
        this.team = GameStateAnalyser.PLAYER;
    }
    
    public MoreThanXUnits(UNIT_TYPE unitType, int number, int team) {
        this.unitType = unitType;
        this.number = number;
        this.team = team;
    }

    @Override
    public boolean conditionFulfilled(ExtendedGameState egs) {
        int numberOfUnits = GameStateAnalyser.getUnits(egs.getGameState(), new UnitQuery(unitType, team)).size();
        return numberOfUnits > number;       
    }
}
