package edu.fudan.gomoku.core;

import edu.fudan.gomoku.core.enums.ConnectResultEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaowangzi
 * @date 17-12-19
 */
public class AreaGame {

    private int currentPlayer = 0;

    private boolean[][] horizontal = new boolean[4][3];

    private boolean[][] vertical = new boolean[3][4];

    private int[][] square = new int[3][3];

    public void restart() {
        this.horizontal = new boolean[4][3];
        this.vertical = new boolean[3][4];
        this.square = new int[3][3];
        currentPlayer = 0;
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

    public int getWinner() {
        Map<Integer, Integer> squareCount = new HashMap<>(3, 1);
        for (int[] aSquare : square) {
            for (int j = 0; j < square[0].length; ++j) {
                if (aSquare[j] == 0) {
                    continue;
                }
                squareCount.compute(aSquare[j], (player, count) -> {
                    if (count == null) {
                        return 1;
                    }
                    return count + 1;
                });
            }
        }
        return squareCount.values().stream().mapToInt(a -> a).sum() == 9 ?
                squareCount.entrySet().stream().reduce((integerIntegerEntry, integerIntegerEntry2) ->
                        integerIntegerEntry.getValue() > integerIntegerEntry2.getValue() ?
                                integerIntegerEntry : integerIntegerEntry2)
                        .map(Map.Entry::getKey).orElse(0) : 0;
    }

    public boolean[][] getHorizontal() {
        return horizontal;
    }

    public boolean[][] getVertical() {
        return vertical;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int[][] getSquare() {
        return square;
    }

    private boolean checkSquare(int playNo) {
        boolean newSquare = false;
        for (int i = 0; i < square.length; ++i) {
            for (int j = 0; j < square[0].length; ++j) {
                if (square[i][j] == 0 && horizontal[i][j] && horizontal[i + 1][j] && vertical[i][j] && vertical[i][j + 1]) {
                    square[i][j] = playNo;
                    newSquare = true;
                }
            }
        }
        currentPlayer = newSquare ? currentPlayer : 1 - currentPlayer;
        return newSquare;
    }

    private boolean done() {
        for (int[] row : square) {
            for (int j = 0; j < square[0].length; ++j) {
                if (row[j] == 0) {
                    return false;
                }
            }
        }
        currentPlayer = 2;
        return true;
    }
}
