package playertask;

import java.util.Set;
import rts.GameState;
import rts.UnitAction;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public interface IPlayerTask {
    
    
    public float eval(GameState gs, Unit playerUnit);
    public Set<Integer> getPermittedActionIDs();
}
