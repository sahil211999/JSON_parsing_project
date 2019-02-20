package com.example;

/**
 * Class contains getter methods for starting and ending rooms.
 */

public class Layout {
    String startingRoom;
    String endingRoom;
    Room[] rooms;
    Player player;


    public String getStartingroom() {
        return startingRoom;
    }
    public String getEndingroom(){
        return endingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }
}
