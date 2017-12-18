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

    private String currentPlayer;

    private String player1Name;

    private String player2Name;

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

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    @Override
    public String toString() {
        return "DisplayAreaGameResponse{" +
                "horizontal=" + Arrays.toString(horizontal) +
                ", vertical=" + Arrays.toString(vertical) +
                ", square=" + Arrays.toString(square) +
                ", currentPlayer='" + currentPlayer + '\'' +
                ", player1Name='" + player1Name + '\'' +
                ", player2Name='" + player2Name + '\'' +
                '}';
    }
}
