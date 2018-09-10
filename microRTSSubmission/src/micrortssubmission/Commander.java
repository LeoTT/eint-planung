package micrortssubmission;

import htn.tasks.PrimitiveTask;
import htn.tasks.Task;
import htn.tasks.compound.BuildBarracks_compound;
import htn.tasks.compound.BuildBase_compound;
import htn.tasks.compound.Harvest_compound;
import htn.tasks.compound.TrainAttackUnit_compound;
import htn.tasks.compound.TrainWorker_compound;
import htn.tasks.primitive.AttackWorker_primitive;
import htn.tasks.primitive.BuildBarracks_primitive;
import java.util.List;
import rts.GameState;
import rts.PlayerAction;
import rts.UnitAction;
import rts.units.Unit;
import rts.units.UnitTypeTable;
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
         Task t = new TrainAttackUnit_compound();
      //  Task t = new BuildBarracks_primitive();
      
        // Task t = new SimpleMiningTask(); [htn.tasks.primitive.BuildBarracks_primitive@4f8e5cde]
        
        if (gs.getTime() == 2) {
            System.out.println("error hier");
        }
        List<PrimitiveTask> resolve = t.resolve(egs);

        
        for (PrimitiveTask p : resolve) {
            p.execute(egs);
        }
        System.out.println(resolve);
        for (Long unitID : egs.getManagedUnits()) {
            if (egs.getAssignment(unitID) != null) {
                if (isUnitIdle(gs.getUnit(unitID), gs) || egs.isReserved(unitID)) {
                    System.out.println(gs.getUnit(unitID));
                    MiniMax minmax = new MiniMax(gs.getUnit(unitID), egs.getAssignment(unitID), gs, player, 2);
                    pa.addUnitAction(gs.getUnit(unitID), minmax.getUnitAction());
                }
            }
        }
        System.out.println(pa);
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
