package util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import micrortssubmission.enums.UNIT_TYPE;

/**
 *
 * @author Florian
 */
public class UnitQuery {

    private UNIT_TYPE unitType = null;
    private int playerId = -1;
    private Rectangle range;

    public UnitQuery() {
        range = new Rectangle(new Point(-1, -1), new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    public UnitQuery(UNIT_TYPE unitType, int player_id) {
        this();
        this.unitType = unitType;
        this.playerId = player_id;
    }

    public UnitQuery(UNIT_TYPE unitType) {
        this();
        this.unitType = unitType;
        this.playerId = -1;
    }

    public UnitQuery(int player_id) {
        this();
        this.playerId = player_id;
    }

    public UNIT_TYPE getUnitType() {
        return unitType;
    }

    public void setUnitType(UNIT_TYPE unit_id) {
        this.unitType = unit_id;
    }

    public int getTeam() {
        return playerId;
    }

    public void setTeam(int player_id) {
        this.playerId = player_id;
    }

    public void setRange(Point p1, Point p2) {
        range = new Rectangle(p1, new Dimension(p2.x - p1.x, p2.y - p1.y));
    }

    public Rectangle getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "Query for type: " + unitType + " and team: " + playerId;
    }
}
