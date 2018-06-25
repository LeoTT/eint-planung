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
public class AttackPlayerTask implements IPlayerTask {

    private Unit enemyUnit;

    public AttackPlayerTask(Unit enemyUnit) {
        this.enemyUnit = enemyUnit;
    }    
    
    @Override
    public float eval(GameState gs, Unit playerUnit) {
        int hp = enemyUnit.getHitPoints();
        int manhattanDistance = Math.abs(playerUnit.getX() - enemyUnit.getX()) + Math.abs(playerUnit.getY() - enemyUnit.getY());
        if (manhattanDistance > 1) {
            IPlayerTask pt = new MovePlayerTask(new Point(enemyUnit.getX(), enemyUnit.getY()));
            return pt.eval(gs, playerUnit);
        }         
        return -hp;
    }
    
}
