package edu.fudan.gomoku.core;

import edu.fudan.gomoku.core.enums.ConnectResultEnum;
import edu.fudan.gomoku.response.DisplayAreaGameResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaowangzi
 * @date 17-12-18
 */
@Component
public class Engine {

    private Map<String, Room> roomNameRoomMap = new ConcurrentHashMap<>();

    public List<Room> listRooms() {
        return new ArrayList<>(roomNameRoomMap.values());
    }

    public Boolean createRoom(String roomName) {
        if (roomNameRoomMap.containsKey(roomName)) {
            return false;
        } else {
            final Room value = new Room();
            value.setName(roomName);
            value.setPlayerNames(Collections.synchronizedList(new ArrayList<>(2)));
            roomNameRoomMap.put(roomName, value);
            return true;
        }
    }

    public Boolean enterRoom(String roomName, String userName) {
        final Room room = roomNameRoomMap.get(roomName);
        if (room == null) {
            return false;
        }
        final List<String> playerNames = room.getPlayerNames();
        if (canEnter(playerNames)) {
            return false;
        }
        synchronized (this) {
            if (canEnter(playerNames)) {
                return false;
            }
            playerNames.add(userName);
        }
        return true;
    }

    public void leaveRoom(String roomName, String userName) {
        final Room room = roomNameRoomMap.get(roomName);
        if (room == null) {
            return;
        }
        final List<String> playerNames = room.getPlayerNames();
        playerNames.remove(userName);
        if (playerNames.size() == 0) {
            roomNameRoomMap.remove(roomName);
        }
    }

    public void startGame(String roomName) {
        final Room room = roomNameRoomMap.get(roomName);
        if (room == null) {
            return;
        }
        room.setAreaGame(new AreaGame());
    }

    public void restartGame(String roomName) {
        final Room room = roomNameRoomMap.get(roomName);
        if (room == null) {
            return;
        }
        room.getAreaGame().restart();
    }

    public ConnectResultEnum connect(String userName, String roomName, boolean isHorizontal, int row, int col) {
        final Room room = roomNameRoomMap.get(roomName);
        if (room == null) {
            return null;
        }
        final int playerNo = room.getPlayerNames().indexOf(userName) + 1;
        final AreaGame areaGame = room.getAreaGame();
        return areaGame.connect(playerNo, isHorizontal, row, col);
    }

    public DisplayAreaGameResponse displayGame(String roomName) {
        final Room room = roomNameRoomMap.get(roomName);
        if (room == null) {
            return null;
        }
        final AreaGame areaGame = room.getAreaGame();
        DisplayAreaGameResponse displayAreaGameResponse = new DisplayAreaGameResponse();
        displayAreaGameResponse.setHorizontal(areaGame.getHorizontal());
        displayAreaGameResponse.setSquare(areaGame.getSquare());
        displayAreaGameResponse.setVertical(areaGame.getVertical());
        return displayAreaGameResponse;
    }

    private boolean canEnter(List<String> playerNames) {
        return playerNames.size() >= 2;
    }
}
