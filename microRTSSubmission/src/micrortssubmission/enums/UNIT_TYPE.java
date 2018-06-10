/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micrortssubmission.enums;

/**
 *
 * @author leo
 */
public enum UNIT_TYPE {
    RESSOURCE(0), BASE(1), WORKER(2), LIGHT(3);

    private int unitId;

    UNIT_TYPE(int id) {
        this.unitId = id;
    }

    public int getUnitId() {
        return this.unitId;
    }
}
