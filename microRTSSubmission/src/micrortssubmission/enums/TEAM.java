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
public enum TEAM {
    ME(0), ENEMY(1);

    private int playerId;

    TEAM(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }
}
