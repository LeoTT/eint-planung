package util;

import micrortssubmission.enums.UNIT_TYPE;
import java.util.List;
import java.util.stream.Collectors;
import rts.GameState;
import rts.PhysicalGameState;
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

}
