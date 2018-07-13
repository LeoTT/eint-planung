package micrortssubmission;

import htn.tasks.PrimitiveTask;
import htn.tasks.Task;
import htn.tasks.primitive.SimpleMiningTask;
import java.awt.Point;
import java.util.List;
import util.GameStateAnalyser;
import util.UnitQuery;
import micrortssubmission.enums.UNIT_TYPE;
import playertask.AttackPlayerTask;
import playertask.BuildPlayerTask;
import playertask.CollectPlayerTask;
import playertask.MovePlayerTask;
import rts.GameState;
import rts.PlayerAction;
import rts.UnitAction;
import rts.units.Unit;
import util.ExtendedGameState;

/**
 *
 * @author leo
 */
public class Commander {

    ExtendedGameState egs = null;

    PlayerAction getAction(int player, GameState gs) {
        PlayerAction pa = new PlayerAction();

        if (egs == null) {
            egs = new ExtendedGameState(gs);
        } else {
            egs.updateGameState(gs);
        }
        Task t = new SimpleMiningTask();
        List<PrimitiveTask> resolve = t.resolve(egs);
        for (PrimitiveTask p : resolve) {
            p.execute(egs);
        }
        for (Long l : egs.getManagedUnits()) {
            if (egs.getAssignment(l) != null) {
                if (isUnitIdle(gs.getUnit(l), gs) || egs.isReserved(l)) {
                    MiniMax minmax = new MiniMax(gs.getUnit(l), egs.getAssignment(l), gs, player, 2);
                    pa.addUnitAction(gs.getUnit(l), minmax.getUnitAction());
                }
            }
        }
        return pa;
    }

//    PlayerAction getAction(int player, GameState gs) {
//        PlayerAction pa = new PlayerAction();
//        Unit u = GameStateAnalyser.getUnits(gs, new UnitQuery(UNIT_TYPE.WORKER, player)).get(0);
//        
//        if (isUnitIdle(u, gs)) {
//            int enemy = (player==0) ? 1 : 0;
//            Unit enemyBase = GameStateAnalyser.getUnits(gs, new UnitQuery(UNIT_TYPE.BASE, enemy)).get(0);
//            Unit enemyWorker = GameStateAnalyser.getUnits(gs, new UnitQuery(UNIT_TYPE.WORKER, enemy)).get(0);
//            Point enemyBasePosition = new Point(enemyBase.getX(), enemyBase.getY() - 3);
//            //MiniMax minmax = new MiniMax(u, new MovePlayerTask(new Point(enemyWorker.getX(), enemyWorker.getY())), gs, player, 2);    
//            MiniMax minmax = new MiniMax(u, new CollectPlayerTask(), gs, player, 2);
//            //MiniMax minmax = new MiniMax(u, new AttackPlayerTask(enemyBase), gs, player, 2);
//            //MiniMax minmax = new MiniMax(u, new BuildPlayerTask(UNIT_TYPE.BASE, new Point(5,5)), gs, player, 2);
//            //minmax.generateTree(); 
//            pa.addUnitAction(u, minmax.getUnitAction());
//        }
//        return pa;
//    }
    /**
     * Testet, ob eine Einheit in einem Zustand eine Aktion ausführt.
     *
     * @param u Einheit, die geprüft werden soll.
     * @param gs Zustand, der geprüft werden soll.
     * @return <code> true </code>, falls die Einheit nichts tut, <code> false
     * </code>
     */
    private static boolean isUnitIdle(Unit u, GameState gs) {
        UnitAction ua = gs.getUnitAction(u);
        return (ua == null || ua.getType() == UnitAction.TYPE_NONE);
    }
}
