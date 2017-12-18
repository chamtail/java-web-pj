package edu.fudan.gomoku.controller;

import edu.fudan.gomoku.core.Engine;
import edu.fudan.gomoku.response.ListRoomsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaowangzi
 * @date 17-12-18
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private Engine engine;

    @RequestMapping("/")
    public ListRoomsResponse listRooms() {
        ListRoomsResponse listRoomsResponse = new ListRoomsResponse();
        listRoomsResponse.setRooms(engine.listRooms());
        return listRoomsResponse;
    }

    @RequestMapping("/create")
    public Boolean createRoom(String roomName, String userName) {
        return engine.createRoom(roomName) && engine.enterRoom(roomName, userName);
    }

    @RequestMapping("/enter")
    public Boolean enterRoom(String roomName, String userName) {
        return engine.enterRoom(roomName, userName);
    }

    @RequestMapping("/leave")
    public void leave(String roomName, String userName) {
        engine.leaveRoom(roomName, userName);
    }
}
