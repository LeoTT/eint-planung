package playertask;

import rts.GameState;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public interface IPlayerTask {
    
    
    public float eval(GameState gs, Unit playerUnit);
}
