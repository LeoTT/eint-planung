package playertask;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import micrortssubmission.enums.MOOD;
import rts.GameState;
import rts.PhysicalGameState;
import rts.UnitAction;
import rts.units.Unit;

/**
 *
 * @author Florian
 */
public class MovePlayerTask extends AbstractPlayerTask {

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
    public Set<Integer> getPermittedActionIDs() {
        HashSet set = new HashSet();
        set.add(UnitAction.TYPE_MOVE);
        set.add(UnitAction.TYPE_NONE);
        set.add(UnitAction.TYPE_ATTACK_LOCATION);
        return set;
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
        int worst = gs.getPhysicalGameState().getWidth() + gs.getPhysicalGameState().getHeight();
        int actual = Math.abs(playerUnit.getX() - target.x) + Math.abs(playerUnit.getY() - target.y);
        //return -manhattanDistance - maxDistance;
        return normalize(0,worst,actual);
    }

}
