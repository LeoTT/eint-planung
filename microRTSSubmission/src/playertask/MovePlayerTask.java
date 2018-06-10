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
        
        Point position = new Point(playerUnit.getX(), playerUnit.getY());
        return (float) target.distance(position);
    }
    
}
