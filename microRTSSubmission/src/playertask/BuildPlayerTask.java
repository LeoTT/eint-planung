/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playertask;

import java.awt.Point;
import rts.GameState;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public class BuildPlayerTask implements IPlayerTask{

    private Point targetPosition;
    private int buildingType;

    BuildPlayerTask(int buildingType, Point targetPosition) {
        
        this.buildingType = buildingType;
        this.targetPosition = targetPosition;
    }

    /**
     * Get the value of targetPosition
     *
     * @return the value of targetPosition
     */
    public Point getTargetPosition() {
        return targetPosition;
    }

    /**
     * Get the value of buildingType
     *
     * @return the value of buildingType
     */
    public int getBuildingType() {
        return buildingType;
    }
    
    @Override
    public float eval(GameState gs, Unit playerUnit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
