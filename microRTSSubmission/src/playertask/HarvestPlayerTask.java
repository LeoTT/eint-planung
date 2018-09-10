/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playertask;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import micrortssubmission.enums.UNIT_TYPE;
import rts.GameState;
import rts.UnitAction;
import rts.units.Unit;
import util.GameStateAnalyser;
import util.UnitQuery;

/**
 *
 * @author Florian
 */
public class HarvestPlayerTask extends AbstractPlayerTask {

    // Wie bitte kriegt man den Zustand "Ressource gedroppt" höher als "Noch gar nicht angefangen mit der Arbeit", wenn beide gleich aussehen?
    // Wenn keiner ne Idee hat müssen wir wohl das Interface umschreiben, dass es eine StatefulUnit kriegt, die Ihre Kopie eines Task updated.
    public HarvestPlayerTask() {
        ressource = null;
    }

    public HarvestPlayerTask(Point ressource) {
        this.ressource = ressource;
    }

    private Point ressource;

    private static final int DISTANCE_MODIFIER = 5;
    private static final int HEALTH_MODIFIER = 1;
    private static final int RESSOURCE_MODIFIER = 10;
    private static final int TOTAL_RESSOURCE_MODIFIER = 1000;

    @Override
    public int estimateTime() {
        return 500;
    }
    @Override
    public Set<Integer> getPermittedActionIDs() {
        HashSet set = new HashSet();
        set.add(UnitAction.TYPE_MOVE);
        set.add(UnitAction.TYPE_NONE);
        set.add(UnitAction.TYPE_ATTACK_LOCATION);
        set.add(UnitAction.TYPE_HARVEST);
        set.add(UnitAction.TYPE_RETURN);
        return set;
    }

    @Override
    public float eval(GameState gs, Unit playerUnit) {
        if (playerUnit == null) {
            return -Float.MAX_VALUE;
        }
        if (ressource == null) {
            Unit u = GameStateAnalyser.getClosestUnit(gs, new UnitQuery(UNIT_TYPE.RESSOURCE, -1), GameStateAnalyser.getPoint(playerUnit));
            if (u != null) {
                ressource = GameStateAnalyser.getPoint(u);
            }
        }
        // fallunterscheidung ob beladen oder nicht, entsprechend distanz zur nächsten base oder nächsten
        float benefit = gs.getPlayer(playerUnit.getPlayer()).getResources() * TOTAL_RESSOURCE_MODIFIER;

        benefit += playerUnit.getHarvestAmount() * RESSOURCE_MODIFIER;
        benefit += new MovePlayerTask(ressource).eval(gs, playerUnit);
        
        if (playerUnit.getResources() == playerUnit.getType().harvestAmount) {
            Unit closestBase = GameStateAnalyser.getClosestUnit(gs, new UnitQuery(UNIT_TYPE.BASE, playerUnit.getPlayer()), GameStateAnalyser.getPoint(playerUnit));
            if (closestBase != null) {
                benefit += new MovePlayerTask(GameStateAnalyser.getPoint(closestBase)).eval(gs, playerUnit) * DISTANCE_MODIFIER;
            }
        }
        //benefit += playerUnit.getResources()*RESSOURCE_MODIFIER;

        return benefit;

    }

}
