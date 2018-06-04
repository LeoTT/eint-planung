package micrortssubmission;

/**
 *
 * @author Florian
 */
public class UnitQuery {
    
    private int unitId;
    private int playerId;

    public UnitQuery(int unit_id, int player_id) {
        this.unitId = unit_id;
        this.playerId = player_id;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unit_id) {
        this.unitId = unit_id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int player_id) {
        this.playerId = player_id;
    }
    
    
}
