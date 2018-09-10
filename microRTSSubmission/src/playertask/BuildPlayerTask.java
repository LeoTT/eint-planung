/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playertask;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import micrortssubmission.enums.UNIT_TYPE;
import rts.GameState;
import rts.UnitAction;
import rts.units.Unit;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author Florian
 */
public class BuildPlayerTask extends AbstractPlayerTask{

    private Point targetPosition;
    private UNIT_TYPE buildingType;
    
    @Override
    public int estimateTime() {
        return 300;
    }
    private static final int DISTANCE_MODIFIER = 1;

    public BuildPlayerTask(UNIT_TYPE buildingType, Point targetPosition) {
        
        this.buildingType = buildingType;
        this.targetPosition = targetPosition;
    }
    
    @Override
    public Set<Integer> getPermittedActionIDs() {
        HashSet set = new HashSet();
        set.add(UnitAction.TYPE_MOVE);
        set.add(UnitAction.TYPE_NONE);
        set.add(UnitAction.TYPE_ATTACK_LOCATION);
        set.add(UnitAction.TYPE_PRODUCE);
        return set;
    }
    
    @Override
    public float eval(GameState gs, Unit playerUnit) {
        float totalCost = new MovePlayerTask(targetPosition).eval(gs, playerUnit) * DISTANCE_MODIFIER;
        UnitQuery uq = new UnitQuery(playerUnit.getPlayer());
        uq.setRange(targetPosition, new Point(1,1));
        uq.setUnitType(buildingType);
        List<Unit> units = GameStateAnalyser.getUnits(gs, uq);
        if(!units.isEmpty()) {
            totalCost+= 100;
        }
        return totalCost;
    }
    
    @Override
    public String toString() {
        return "BuildPlayerTask - " + targetPosition + " " + buildingType;
    }
}
