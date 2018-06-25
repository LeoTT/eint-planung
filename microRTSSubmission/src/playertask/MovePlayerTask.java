package playertask;

import java.awt.Point;
import micrortssubmission.enums.MOOD;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public class MovePlayerTask implements IPlayerTask {

    private Point target;
    private MOOD mood;

    public MovePlayerTask(Point p) {
        this.target = p;
        this.mood = MOOD.NEUTRAL;
    }
    
    public MovePlayerTask(Point p, MOOD mood) {
        this.target = p;
        this.mood = mood;
    }

    public Point getP() {
        return target;
    }

    @Override
    public float eval(GameState gs, Unit playerUnit) {
//        if (playerUnit == null) {
//            return -Float.MAX_VALUE;
//        }
//        int maxDistance = gs.getPhysicalGameState().getWidth() + gs.getPhysicalGameState().getHeight();
//        int manhattanDistance = Math.abs(playerUnit.getX() - target.x) + Math.abs(playerUnit.getY() - target.y);
//        return Float.MAX_VALUE - (Float.MAX_VALUE / maxDistance) * manhattanDistance;
        if (playerUnit == null) {            
            return -10000000;
        }
        int maxDistance = gs.getPhysicalGameState().getWidth() + gs.getPhysicalGameState().getHeight();
        int manhattanDistance = Math.abs(playerUnit.getX() - target.x) + Math.abs(playerUnit.getY() - target.y);
        return -manhattanDistance - maxDistance;
    }

}
