package micrortssubmission;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import playertask.IPlayerTask;
import rts.GameState;
import rts.PlayerAction;
import rts.UnitAction;
import rts.units.Unit;
import util.GameStateAnalyser;
import util.MinMaxTree;
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

    private MinMaxTree tree;
    //Wenn die Einheit "stirbt", möchten wir uns den letzten Zustand merken, wo er noch am leben war.
    private Unit unitLastAlive;

    private final int you;
    private final int enemy;

    public MiniMax(Unit unit, IPlayerTask task, GameState state, int player, int maxDepth) {
        this.unit = unit.getID();
        this.task = task;
        this.state = state;
        this.you = player;
        this.maxDepth = maxDepth;
        this.enemy = (you == 1 ? 0 : 1);
        tree = new MinMaxTree(null, true, maxDepth, maxDepth);
    }

    /**
     * Führt die MinMax-Suche nach dem besten nächsten Zug aus.
     * @return Bester gefundener Zug.
     */
    public UnitAction getUnitAction() {
        bestAction = null;
        System.out.println("-------------------------------");
        max(state.clone(), maxDepth);
        if (bestAction == null) {
            bestAction = new UnitAction(UnitAction.TYPE_NONE);
        }
        return bestAction;
    }

    //TODO: execute durch issue+cycle ersetzen, da sonst Einheiten durch Wände laufen können
    private float max(GameState gs, int depth) {
        Unit u = gs.getUnit(unit);
        if (depth == 0 || u == null || u.getHitPoints() <= 0) {
            return eval(gs);
        }
        float maxVal = -Float.MAX_VALUE;
        for (UnitAction ua : u.getUnitActions(gs)) {
            if (depth == maxDepth) {
                System.out.println("Considering action: " + ua.getActionName() + " / " + ua.getDirection());
            }
            GameState cloned = gs.clone();
            ua.execute(cloned.getUnit(u.getID()), cloned);
            float wert = min(cloned, depth - 1);
            if (wert > maxVal) {
                maxVal = wert;
                if (depth == maxDepth) {
                    bestAction = ua;
                    System.out.println("\tUpdated decision to " + bestAction + " with a score of " + maxVal);
                }
            } else {
                if (depth == maxDepth) {
                    System.out.println("Ignored action " + ua + " with a score of " + wert);
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
        List<Unit> units = GameStateAnalyser.getUnits(gs, new UnitQuery(enemy));
        HashSet<PlayerAction> playerActions = getPossibleActions(units, gs);
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
        try {
            for (UnitAction ua : u.getUnitActions(gs)) {
                for (PlayerAction pa : actions) {
                    //Kreuzprodukt bilden
                    PlayerAction pa_new = pa.clone();
                    pa_new.addUnitAction(u, ua);
                    outputSet.add(pa_new);
                }
            }
        } catch (Exception e) {

        }
        return outputSet;
    }

    private float eval(GameState gs) {
        return task.eval(gs, gs.getUnit(unit));
    }

    /**
     * Zustandsübergangsfunktion.
     * @param pa Zustandsübergang.
     * @param gs Zustand, der verändert wird.
     * @throws IllegalArgumentException 
     */
    private  void executePlayerAction(PlayerAction pa, GameState gs) throws IllegalArgumentException {

        List<Pair<Unit, UnitAction>> actions = pa.getActions();
        for (Pair<Unit, UnitAction> p : actions) {
            if (gs.getUnit(unit) != null) {
                 unitLastAlive = gs.getUnit(unit);
            }
            p.m_b.execute(gs.getUnit(p.m_a.getID()), gs);

        }
    }

    /**
     * Rekursiver Aufbau des Suchbaumes, entsprechend der MiniMax-Suche.
     * @param gs Kopie des aktuellen Zustands.
     * @param superTree Übergeordneter Baum. Ist dieser nicht der Wurzelknoten wird zuerst der Zutsandsübergang, der durch den Baum represäntiert wird, ausgeführt.
     */
    private void generateTree(GameState gs, MinMaxTree superTree) {
        try {
            if (superTree.getDistanceFromLeaf() != maxDepth) {
                // Zustandsübergang versuchen
                executePlayerAction(superTree.getPlayerAction(), gs);
            }
            if (superTree.getDistanceFromLeaf() == 0) {
                // Neuer Zustand ist ein Blatt, Score berechnen
                superTree.setScore(eval(gs));
            } else {
                // MinMax-Logik
                if (superTree.isMaxNode()) {
                    Unit u = gs.getUnit(unit);
                    for (UnitAction ua : u.getUnitActions(gs)) {
                        GameState cloned = gs.clone();
                        PlayerAction action = new PlayerAction();
                        action.addUnitAction(u, ua);
                        MinMaxTree newTree = new MinMaxTree(action, !superTree.isMaxNode(), superTree.getDistanceFromLeaf() - 1, maxDepth);
                        superTree.addNode(newTree);
                        generateTree(cloned, newTree);
                    }
                } else {
                    List<Unit> units = GameStateAnalyser.getUnits(gs, new UnitQuery(enemy));
                    HashSet<PlayerAction> playerActions = getPossibleActions(units, gs);
                    for (PlayerAction pa : playerActions) {
                        GameState cloned = gs.clone();
                        MinMaxTree newTree = new MinMaxTree(pa, !superTree.isMaxNode(), superTree.getDistanceFromLeaf() - 1, maxDepth);
                        superTree.addNode(newTree);
                        generateTree(cloned, newTree);
                    }
                }
            }
        } catch (IllegalArgumentException ex) {
            // Ist der Zustandsübergang ungültig muss der Score des Knoten invertiert werden
            superTree.setScore(superTree.getScore() * -1);
        }
    }

    /**
     * Derzeit Debugmethode. Druckt den Suchbaum nach std::out .
     * Praktisch genauso teuer wie die eigentliche getUnitAction(), da die gleichen Berechnungen gemacht wurden.
     */
    public void generateTree() {
        // Fehlerausgabe deaktivieren
        PrintStream err = System.err;
        System.setErr(new PrintStream(new OutputStream() {public void write(int b) {}}));
        
        generateTree(state.clone(), tree);
        tree.evaluate();
        System.out.println("Vorher: "+eval(state));
        System.out.println(tree);
        
        // Fehlerausgabe wiederherstellen
        System.setErr(err);
    }
}
