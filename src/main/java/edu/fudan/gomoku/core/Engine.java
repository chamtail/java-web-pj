package edu.fudan.gomoku.core;

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

    private boolean canEnter(List<String> playerNames) {
        return playerNames.size() >= 2;
    }
}
