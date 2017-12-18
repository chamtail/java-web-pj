package edu.fudan.gomoku.core;

import edu.fudan.gomoku.core.enums.ConnectResultEnum;

/**
 * @author xiaowangzi
 * @date 17-12-19
 */
public class AreaGame {

    private boolean[][] horizontal = new boolean[4][3];

    private boolean[][] vertical = new boolean[3][4];

    private int[][] square = new int[3][3];

    public void restart() {
        this.horizontal = new boolean[4][3];
        this.vertical = new boolean[3][4];
        this.square = new int[3][3];
    }

    public ConnectResultEnum connect(int playerNo, boolean isHorizontal, int row, int col) {
        if (isHorizontal) {
            horizontal[row][col] = true;
        } else {
            vertical[row][col] = true;
        }
        return checkSquare(playerNo) ?
                (done() ? ConnectResultEnum.COMPLETE : ConnectResultEnum.SQUARE) : ConnectResultEnum.NORMAL;
    }

    public boolean[][] getHorizontal() {
        return horizontal;
    }

    public boolean[][] getVertical() {
        return vertical;
    }

    public int[][] getSquare() {
        return square;
    }

    private boolean checkSquare(int playNo) {
        for (int i = 0; i < square.length; ++i) {
            for (int j = 0; j < square[0].length; ++j) {
                if (square[i][j] == 0 && horizontal[i][j] && horizontal[i + 1][j] && vertical[i][j] && vertical[i][j + 1]) {
                    square[i][j] = playNo;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean done() {
        for (int[] row : square) {
            for (int j = 0; j < square[0].length; ++j) {
                if (row[j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
