package util;

import micrortssubmission.enums.UNIT_TYPE;

/**
 *
 * @author Florian
 */
public class UnitQuery {

    private UNIT_TYPE unitType = null;
    private int playerId = -1;

    public UnitQuery() {

    }

    public UnitQuery(UNIT_TYPE unitType, int player_id) {
        this.unitType = unitType;
        this.playerId = player_id;
    }

    public UnitQuery(int player_id) {
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

}
