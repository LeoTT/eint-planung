/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ai.core.AI;
import ai.*;
import ai.abstraction.WorkerRush;
import ai.abstraction.pathfinding.BFSPathFinding;
import ai.mcts.naivemcts.NaiveMCTS;
import gui.PhysicalGameStatePanel;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import javax.swing.JFrame;
import micrortssubmission.MicroRTSSubmission;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;
import util.XMLWriter;

/**
 *
 * @author santi
 */
public class GameVisualSimulationTest {

    public static void main(String args[]) throws Exception {
        UnitTypeTable utt = new UnitTypeTable();
        PhysicalGameState pgs = PhysicalGameState.load("maps/16x16/basesWorkers16x16.xml", utt);
   //     System.setErr(new PrintStream(new OutputStream() {public void write(int b) {}})); // Errorlog unterdrücken

//        PhysicalGameState pgs = MapGenerator.basesWorkers8x8Obstacle();
        GameState gs = new GameState(pgs, utt);
        int MAXCYCLES = 5000;
        //int PERIOD = 20;
        int PERIOD = 20;
        boolean gameover = false;

        AI ai1 = new MicroRTSSubmission(utt);
        AI ai2 = new PassiveAI();

        JFrame w = PhysicalGameStatePanel.newVisualizer(gs, 640, 640, false, PhysicalGameStatePanel.COLORSCHEME_BLACK);
//        JFrame w = PhysicalGameStatePanel.newVisualizer(gs,640,640,false,PhysicalGameStatePanel.COLORSCHEME_WHITE);

        long nextTimeToUpdate = System.currentTimeMillis() + PERIOD;
        do {
            if (System.currentTimeMillis() >= nextTimeToUpdate) {
                PlayerAction pa1 = ai1.getAction(0, gs);
                PlayerAction pa2 = ai2.getAction(1, gs);
                gs.issueSafe(pa1);
                gs.issueSafe(pa2);

                // simulate:
                gameover = gs.cycle();
                w.repaint();
                nextTimeToUpdate += PERIOD;
            } else {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (!gameover && gs.getTime() < MAXCYCLES);
        ai1.gameOver(gs.winner());
        ai2.gameOver(gs.winner());

        System.out.println("Game Over");
    }
}
