/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import rts.GameState;
import rts.units.Unit;
import playertask.AbstractPlayerTask;

/**
 *
 * @author marcel
 */
public class ExtendedGameState {

    private GameState gs;
    private HashMap<Long, AbstractPlayerTask> unitAssignmentTable;
    
    private HashMap<Long, Integer> lastReservationMap;

    public ExtendedGameState(GameState gs) {
        this.gs = gs;
        unitAssignmentTable = new HashMap<>();
        lastReservationMap = new HashMap<>();
        
        for(Unit u: GameStateAnalyser.getUnits(gs, new UnitQuery(GameStateAnalyser.PLAYER))) {
            unitAssignmentTable.putIfAbsent(u.getID(), null);
            lastReservationMap.putIfAbsent(u.getID(), -1);
        }
    }

    /**
     * Reserviert eine Unit für den aktuellen Cycle.
     * @param unitID ID der zu reservierenden Unit.
     * @return <code> true </code>, falls die Reservierung erfolgreich war, andernfalls <code> false </code>.
     */
    public boolean reserveUnit(Long unitID) {
        if(!isReserved(unitID)) {
            lastReservationMap.put(unitID, gs.getTime());
            return true;
        } else {
            return false;
        }
    }
    
    public void updateGameState(GameState gs) {
        this.gs = gs;
        lastReservationMap = new HashMap();
        
        for(Unit u: GameStateAnalyser.getUnits(gs, new UnitQuery(GameStateAnalyser.PLAYER))) {
            unitAssignmentTable.putIfAbsent(u.getID(), null);
            lastReservationMap.putIfAbsent(u.getID(), -1);
        }
    }

    public GameState getGameState() {
        return gs.clone();
    }

    public Set<Long> getUnreservedManagedUnits() {
        Set<Long> set = new HashSet<>();
        for(Long l: getManagedUnits()) {
            if(!isReserved(l)) {
                set.add(l);
            }
        }
        return set;
    }
    
    public boolean isReserved(Long l) {
        return gs.getTime() == lastReservationMap.get(l);
    }
    
    public void setAssignment(Long l, AbstractPlayerTask task) {
        unitAssignmentTable.put(l, task);
    }

    public AbstractPlayerTask getAssignment(Long l) {
        return unitAssignmentTable.get(l);
    }

    public Set<Long> getManagedUnits() {
        return unitAssignmentTable.keySet();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ExtendedGameState egs = new ExtendedGameState(gs.clone());
        egs.unitAssignmentTable = (HashMap<Long, AbstractPlayerTask>) unitAssignmentTable.clone();
        egs.unitAssignmentTable = this.unitAssignmentTable;
        return egs;
    }
    
    public Set<Long> getUnreservedPlayersWithTask(Class c) {
        Set<Long> set = new HashSet<>();
        for(Long id:getPlayersWithTask(c)) {
            if(!isReserved(id)) {
                set.add(id);
            }
        }
        return set;
    }

    public Set<Long> getPlayersWithTask(Class c) {
        HashSet<Long> set = new HashSet();
        for (Long l : getManagedUnits()) {
            if (c != null && unitAssignmentTable.get(l) != null && unitAssignmentTable.get(l).getClass().equals(c)) {
                set.add(l);
            } else if (unitAssignmentTable.get(l) == null) {
                set.add(l);
            }
        }
        return set;
    }

}
