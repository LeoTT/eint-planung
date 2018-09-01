/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.LinkedList;
import java.util.List;
import rts.PlayerAction;

/**
 *
 * @author marcel
 */
public class MinMaxTree {

    private PlayerAction pa;
    private List<MinMaxTree> subtrees;
    private float score;
    private int distanceFromLeaf;
    private boolean maxNode;
    private int maxDepth;

    public MinMaxTree(PlayerAction pa, boolean max, int depth, int maxDepth) {
        this.pa = pa;
        this.maxNode = max;
        this.distanceFromLeaf = depth;
        score = Float.MAX_VALUE;
        this.maxDepth = maxDepth;
        if (max) {
            score *= -1;
        }
        subtrees = new LinkedList<>();
    }

    public void addNode(MinMaxTree tree) {
        subtrees.add(tree);
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isMaxNode() {
        return maxNode;
    }

    public float getScore() {
        return score;
    }

    public void evaluate() {
        if (distanceFromLeaf != 0) {
            for (MinMaxTree t : subtrees) {
                t.evaluate();
                if (maxNode) {
                    score = Math.max(score, t.getScore());
                } else {
                    score = Math.min(score, t.getScore());
                }
            }
        }
    }

    public int getDistanceFromLeaf() {
        return distanceFromLeaf;
    }

    public PlayerAction getPlayerAction() {
        return pa;
    }

    public String toString() {
        String s = "";
        for (int i = distanceFromLeaf; i <= maxDepth - 1; i++) {
            s += "\t";
        }
        if (distanceFromLeaf != maxDepth) {
            s += (pa.toString() + " / " + score + "\n");

        } else {
            s += ("No previous Action" + " / " + score + "\n");
        }
        if (distanceFromLeaf != 0) {
            for (MinMaxTree subtree : subtrees) {
                s += subtree.toString();
            }
        }
        return s;
    }
}
