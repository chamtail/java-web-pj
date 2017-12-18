package edu.fudan.gomoku.core;

import java.util.List;

/**
 * @author xiaowangzi
 * @date 17-12-18
 */
public class Room {

    private String name;

    private List<String> playerNames;

    private AreaGame areaGame;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public AreaGame getAreaGame() {
        return areaGame;
    }

    public void setAreaGame(AreaGame areaGame) {
        this.areaGame = areaGame;
    }
}
