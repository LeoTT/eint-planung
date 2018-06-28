/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playertask;

import java.util.HashSet;
import java.util.Set;
import rts.GameState;
import rts.UnitAction;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public class CollectPlayerTask implements IPlayerTask {

    // Wie bitte kriegt man den Zustand "Ressource gedroppt" höher als "Noch gar nicht angefangen mit der Arbeit", wenn beide gleich aussehen?
    // Wenn keiner ne Idee hat müssen wir wohl das Interface umschreiben, dass es eine StatefulUnit kriegt, die Ihre Kopie eines Task updated.
    
    public CollectPlayerTask() {
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
        // fallunterscheidung ob beladen oder nicht, entsprechend distanz zur nächsten base oder nächsten
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
