package micrortssubmission;

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
    
    static final int RESSOURCE = 0;
    static final int BASE_ID = 1;
    static final int PLAYER_ID_ME = 0;
    static final int PLAYER_ID_ENEMY = 1;

    //
    public static List<Unit> getBases(GameState gs) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        List<Unit> allUnits = pgs.getUnits();
        return allUnits.stream()
                .filter(u -> (u.getType().ID == BASE_ID))
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
                .filter(u -> (u.getPlayer() == query.getPlayerId() && u.getType().ID == query.getUnitId()))
                .collect(Collectors.toList());
    }
}
