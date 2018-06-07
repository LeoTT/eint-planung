package playertask;

import java.awt.Point;

/**
 *
 * @author Florian
 */
public class MovePlayerTask {
    
    private Point p;

    public MovePlayerTask(Point p) {
        this.p = p;
    }

    public Point getP() {
        return p;
    } 
}
