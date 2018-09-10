package playertask;

import java.util.Set;
import rts.GameState;
import rts.UnitAction;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public abstract class AbstractPlayerTask {
    
    public int estimateTime() {
        return 5;
    }
    
    public int requiredRessources() {
        return 0;
    }
    
    public abstract float eval(GameState gs, Unit playerUnit);
    public abstract Set<Integer> getPermittedActionIDs();
    
    /**
     * Lineare Normalisierungsfunktion von 0 (Worst) bis 100 (Best).
     * Die Funktion erzeugt die lineare Funktion f mit (best,100) und (worst,0).
     * Daher liefert die Funktion auch sinnvolle Werte ausßerhalb von [best,worst] bzw [worst,best]
     * @param best Optimaler erreichbarer Wert.
     * @param worst Schlechtester möglicher Wert.
     * @param actual Tatsächlich erreichter Wert.
     * @return Normalisierter Wert von actual.
     */
    public static float normalize(float best, float worst, float actual) throws IllegalArgumentException {
        if(best==worst) {
            throw new IllegalArgumentException("Bester und schlechtester Wert müssen unterschiedlich sein!");
        }
        return 100 * (actual - worst) / (best - worst);
    }
}
