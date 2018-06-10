package micrortssubmission;

import micrortssubmission.enums.TEAM;
import micrortssubmission.enums.UNIT_TYPE;

/**
 *
 * @author Florian
 */
public class UnitQuery {
    
    private UNIT_TYPE unitType;
    private TEAM playerId;

    public UnitQuery() {
        
    }
    
    public UnitQuery(UNIT_TYPE unitType, TEAM player_id) {
        this.unitType = unitType;
        this.playerId = player_id;
    }

    public UNIT_TYPE getUnitType() {
        return unitType;
    }

    public void setUnitType(UNIT_TYPE unit_id) {
        this.unitType = unit_id;
    }

    public TEAM getTeam() {
        return playerId;
    }

    public void setTeam(TEAM player_id) {
        this.playerId = player_id;
    }
    
    
}
