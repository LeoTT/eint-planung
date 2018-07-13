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
    
    
    public abstract float eval(GameState gs, Unit playerUnit);
    public abstract Set<Integer> getPermittedActionIDs();
    
    public static float normalize(float best, float worst, float actual) {
        return 100 * (actual - worst) / (best - worst);
    }
}
