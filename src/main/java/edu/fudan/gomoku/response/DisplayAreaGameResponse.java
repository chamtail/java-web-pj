package edu.fudan.gomoku.response;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author xiaowangzi
 * @date 17-12-19
 */
public class DisplayAreaGameResponse implements Serializable {

    private boolean[][] horizontal;

    private boolean[][] vertical;

    private int[][] square;

    public boolean[][] getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean[][] horizontal) {
        this.horizontal = horizontal;
    }

    public boolean[][] getVertical() {
        return vertical;
    }

    public void setVertical(boolean[][] vertical) {
        this.vertical = vertical;
    }

    public int[][] getSquare() {
        return square;
    }

    public void setSquare(int[][] square) {
        this.square = square;
    }

    @Override
    public String toString() {
        return "DisplayAreaGameResponse{" +
                "horizontal=" + Arrays.toString(horizontal) +
                ", vertical=" + Arrays.toString(vertical) +
                ", square=" + Arrays.toString(square) +
                '}';
    }
}
