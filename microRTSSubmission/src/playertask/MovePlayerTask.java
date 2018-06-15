package playertask;

import java.awt.Point;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public class MovePlayerTask implements IPlayerTask{
    
    private Point target;

    public MovePlayerTask(Point p) {
        this.target = p;
    }

    public Point getP() {
        return target;
    } 

    @Override
    public float eval(GameState gs, Unit playerUnit) {
        int maxDistance = gs.getPhysicalGameState().getWidth()+gs.getPhysicalGameState().getHeight();
        int manhattanDistance = Math.abs(playerUnit.getX()-target.x) + Math.abs(playerUnit.getY()-target.y);
        return Float.MAX_VALUE - (Float.MAX_VALUE/maxDistance)*manhattanDistance;
    }
    
}
