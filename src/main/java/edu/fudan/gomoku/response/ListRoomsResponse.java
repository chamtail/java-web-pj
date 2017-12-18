package edu.fudan.gomoku.response;

import edu.fudan.gomoku.core.Room;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaowangzi
 * @date 17-12-18
 */
public class ListRoomsResponse implements Serializable {

    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "ListRoomsResponse{" +
                "rooms=" + rooms +
                '}';
    }
}
