package micrortssubmission;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import micrortssubmission.enums.UNIT_TYPE;
import playertask.IPlayerTask;
import rts.GameState;
import rts.PlayerAction;
import rts.UnitAction;
import rts.units.Unit;
import util.GameStateAnalyser;
import util.Pair;
import util.UnitQuery;

/**
 *
 * @author Florian
 */
public class MiniMax {


    private long unit;
    private IPlayerTask task;
    private GameState state;
    private int maxDepth;
    private UnitAction bestAction;

    private final int you;
    private final int enemy;

    public MiniMax(Unit unit, IPlayerTask task, GameState state, int player, int maxDepth) {
        this.unit = unit.getID();
        this.task = task;
        this.state = state;
        this.you = player;
        this.maxDepth = maxDepth;
        this.enemy = (you == 1 ? 0 : 1);
    }

    public UnitAction getUnitAction() {
        bestAction = null;
        System.out.println("MiniMax.getUnitAction() = " + bestAction);
        max(state.clone(), maxDepth);
        System.out.println("MiniMax.getUnitAction() = " + bestAction);
        return bestAction;
    }

    //TODO: execute durch issue+cycle ersetzen, da sonst EInheiten durch Wände laufen können
    private float max(GameState gs, int depth) {
        Unit u = gs.getUnit(unit);
        if (depth == 0 || u.getHitPoints() <= 0) {
            return eval(gs);
        }
        float maxVal = Float.MIN_VALUE;
        for (UnitAction ua : u.getUnitActions(gs)) {
            GameState cloned = gs.clone();
            ua.execute(u, gs);
            float wert = min(cloned, depth - 1);
            if (wert > maxVal) {
                maxVal = wert;
                if (depth == maxDepth) {
                    bestAction = ua;
                }
            }
        }

        return maxVal;
    }

    private float min(GameState gs, int depth) {
        if (depth == 0) {
            return eval(gs);
        }
        float minVal = Float.MAX_VALUE;
        HashSet<PlayerAction> playerActions = getPossibleActions(GameStateAnalyser.getUnits(gs, new UnitQuery(enemy)), gs);
        for (PlayerAction pa : playerActions) {
            GameState cloned = gs.clone();
            try {
                executePlayerAction(pa, cloned);
                float wert = max(cloned, depth - 1);
                if (wert < minVal) {
                    minVal = wert;
                }
            } catch (IllegalArgumentException ex) {
                //System.out.println(ex);
            }
        }
        return minVal;
    }

    /**
     * Bildet das Kreuzprodukt aus allen möglichen Aktionen einer Menge von
     * Gegnern
     *
     * @param units Liste an gegnerischen EInheiten.
     * @param gs GameState zum Zeitpunkt der Aktionen.
     * @return Menge an möglichen Aktionen.
     */
    private HashSet<PlayerAction> getPossibleActions(List<Unit> units, GameState gs) {
        HashSet<PlayerAction> actions = new HashSet();
        HashSet<PlayerAction> outputSet = new HashSet<>();

        // Falls eine leere Liste übergeben wird kommt ein Set mit einer einzige leeren Action zurück
        if (units.isEmpty()) {
            outputSet.add(new PlayerAction());
            return outputSet;
        }

        Unit u = units.remove(0);
        if (!units.isEmpty()) {
            //Rekursiver Aufruf
            actions = getPossibleActions(units, gs);
        } else {
            actions.add(new PlayerAction());
        }
        for (UnitAction ua : u.getUnitActions(gs)) {
            for (PlayerAction pa : actions) {
                //Kreuzprodukt bilden
                PlayerAction pa_new = pa.clone();
                pa_new.addUnitAction(u, ua);
                outputSet.add(pa_new);
            }
        }
        return outputSet;
    }

    private float eval(GameState gs) {
        return task.eval(gs, gs.getUnit(unit));
    }

    private static void executePlayerAction(PlayerAction pa, GameState gs) throws IllegalArgumentException {
        List<Pair<Unit, UnitAction>> actions = pa.getActions();
        for (Pair<Unit, UnitAction> p : actions) {
            p.m_b.execute(p.m_a, gs);
        }
    }

}
