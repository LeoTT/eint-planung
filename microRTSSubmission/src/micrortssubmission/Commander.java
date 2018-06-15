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
import playertask.MovePlayerTask;
import rts.GameState;
import rts.PlayerAction;
import rts.units.Unit;

/**
 *
 * @author leo
 */
public class Commander {

    PlayerAction getAction(int player, GameState gs) {
        PlayerAction pa = new PlayerAction();
        Unit u = GameStateAnalyser.getUnits(gs, new UnitQuery(UNIT_TYPE.WORKER, player)).get(0);
        MiniMax minmax = new MiniMax(u, new MovePlayerTask(new Point(5, 5)), gs, player, 3);
        pa.addUnitAction(u, minmax.getUnitAction());
        return pa;
    }
}