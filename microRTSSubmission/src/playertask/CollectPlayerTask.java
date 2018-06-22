/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playertask;

import rts.GameState;
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
    public float eval(GameState gs, Unit playerUnit) {
        // fallunterscheidung ob beladen oder nicht, entsprechend distanz zur nächsten base oder nächsten
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
