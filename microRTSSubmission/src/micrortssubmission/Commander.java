/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micrortssubmission;

import java.awt.Point;
import util.GameStateAnalyser;
import util.UnitQuery;
import micrortssubmission.enums.UNIT_TYPE;
import playertask.AttackPlayerTask;
import playertask.MovePlayerTask;
import rts.GameState;
import rts.PlayerAction;
import rts.UnitAction;
import rts.units.Unit;

/**
 *
 * @author leo
 */
public class Commander {

    PlayerAction getAction(int player, GameState gs) {
        PlayerAction pa = new PlayerAction();
        Unit u = GameStateAnalyser.getUnits(gs, new UnitQuery(UNIT_TYPE.WORKER, player)).get(0);
        
        if (isUnitIdle(u, gs)) {
            int enemy = (player==0) ? 1 : 0;
            Unit enemyBase = GameStateAnalyser.getUnits(gs, new UnitQuery(UNIT_TYPE.BASE, enemy)).get(0);
            Unit enemyWorker = GameStateAnalyser.getUnits(gs, new UnitQuery(UNIT_TYPE.WORKER, enemy)).get(0);
            Point enemyBasePosition = new Point(enemyBase.getX(), enemyBase.getY() - 3);
            MiniMax minmax = new MiniMax(u, new MovePlayerTask(new Point(enemyWorker.getX(), enemyWorker.getY())), gs, player, 2);      
            //MiniMax minmax = new MiniMax(u, new AttackPlayerTask(enemyWorker), gs, player, 2);            
            //minmax.generateTree(); 
            pa.addUnitAction(u, minmax.getUnitAction());
        }
        return pa;
    }

    /**
     * Testet, ob eine Einheit in einem Zustand eine Aktion ausführt.
     * @param u Einheit, die geprüft werden soll.
     * @param gs Zustand, der geprüft werden soll.
     * @return <code> true </code>, falls die Einheit nichts tut, <code> false </code> 
     */
    private static boolean isUnitIdle(Unit u, GameState gs) {
        UnitAction ua = gs.getUnitAction(u);
        return (ua == null || ua.getType() == UnitAction.TYPE_NONE);
    }
}
