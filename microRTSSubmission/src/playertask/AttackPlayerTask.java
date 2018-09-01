/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playertask;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import rts.GameState;
import rts.UnitAction;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public class AttackPlayerTask extends AbstractPlayerTask {

    //TODO: Funktioniert mies für RANGED, sollte man fixen, wenn man Zeit hat
    
    private Unit enemyUnit;
    //TODO: Stellschrauben in MOOD packen
    // Stellschrauben für Verhalten
    private static final int DISTANCE_MODIFIER = 1;
    private static final int HEALTH_MODIFIER = 5;

    public AttackPlayerTask(Unit enemyUnit) {
        this.enemyUnit = enemyUnit;
    }    
    
    @Override
    public Set<Integer> getPermittedActionIDs() {
        HashSet set = new HashSet();
        set.add(UnitAction.TYPE_MOVE);
        set.add(UnitAction.TYPE_NONE);
        set.add(UnitAction.TYPE_ATTACK_LOCATION);
        return set;
    }
    
    @Override
    public float eval(GameState gs, Unit playerUnit) {
        int hp = enemyUnit.getHitPoints();
        
        //Teilkosten laufen
        float totalCost = new MovePlayerTask(new Point(enemyUnit.getX(), enemyUnit.getY())).eval(gs, playerUnit) * DISTANCE_MODIFIER;
        //Teilkosten Gegnerische HP
        totalCost -= (-hp * HEALTH_MODIFIER);
        
        return totalCost;
    }
    
}
