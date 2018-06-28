package util;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import micrortssubmission.enums.UNIT_TYPE;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import playertask.IPlayerTask;
import rts.GameState;
import rts.PhysicalGameState;
import rts.UnitAction;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public class GameStateAnalyser {

    public static List<Unit> getBases(GameState gs) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        List<Unit> allUnits = pgs.getUnits();
        return allUnits.stream()
                .filter(u -> (u.getType().ID == UNIT_TYPE.BASE.getUnitId()))
                .collect(Collectors.toList());
    }

    public static List<Unit> getBases(GameState gs, int player) {

        List<Unit> allBases = GameStateAnalyser.getBases(gs);
        return allBases.stream()
                .filter(u -> (u.getPlayer() == player))
                .collect(Collectors.toList());
    }

    public static List<Unit> getUnits(GameState gs, UnitQuery query) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        List<Unit> allUnits = pgs.getUnits();

        return allUnits.stream()
                .filter(u -> {
                    return query.getRange().contains(new Point(u.getX(), u.getY()));
                })
                .filter(u -> {
                    boolean hasRightPlayerId = true;
                    if (query.getTeam() != -1) {
                        hasRightPlayerId = (u.getPlayer() == query.getTeam());
                    }

                    // Default
                    boolean hasRightUnitType = true;
                    if (query.getUnitType() != null) {
                        hasRightUnitType = u.getType().ID == query.getUnitType().getUnitId();
                    }

                    return hasRightPlayerId && hasRightUnitType;
                })
                .collect(Collectors.toList());
    }

    public static int getDistance(GameState gs, long unit1Id, long unit2Id) {
        Unit u1 = gs.getUnit(unit1Id);
        Unit u2 = gs.getUnit(unit2Id);
        return Math.abs(u1.getX() + u2.getX()) + Math.abs(u1.getY() + u2.getY());
    }

    public static List<UnitAction> filterActions(List<UnitAction> actions, IPlayerTask task) {
        Set<Integer> allowed = task.getPermittedActionIDs();
        LinkedList<UnitAction> returnList = new LinkedList<>(actions.stream()
                .filter(
                        (ua) -> (allowed.contains(ua.getType()))
                )
                .collect(Collectors.toList()));
        if(returnList.remove(new UnitAction(UnitAction.TYPE_NONE))) {
            returnList.addFirst(new UnitAction(UnitAction.TYPE_NONE));
        }
        return returnList;
    }

}
