package com.exaple;
import java.util.ArrayList;
/**
 * This class contains all the helper functions for the main class.
 */

public class Helperfunctions {

    /**
     * The function provides description for a particular room.
     * @param roomname for which description is required
     * @return the description of the particular room.
     */
    public static String getRoomdescription (String roomname) throws Exception {
        String ReturnRoomDesc = "";
        for (int i = 0; i < Mainclass.toJsonParsFile().getRooms().length; i++) {
            if (roomname.equals(Mainclass.toJsonParsFile().getRooms()[i].name)) {
                ReturnRoomDesc = Mainclass.toJsonParsFile().getRooms()[i].description;
                return ReturnRoomDesc;
            }
        }
        return "";
    }
    /**
     * The function provides an array of possible directions from a point.
     * @param room takes the name of the room.
     * @return returns an array of directions that are possible from the room.
     */
    public static ArrayList<String> GetFowardDirections(String room) throws Exception {
        for (int i = 0; i < Mainclass.toJsonParsFile().getRooms().length; i++) {
            if (room.equals(Mainclass.toJsonParsFile().getRooms()[i].name)) {
                ArrayList DirectionList = new ArrayList();
                for (int j = 0; j < Mainclass.toJsonParsFile().getRooms()[i].directions.length; j++) {
                    DirectionList.add(Mainclass.toJsonParsFile().getRooms()[i].directions[j].directionName);
                }
                return DirectionList;
            }
        }
        return null;
    }
    /**
     * Checks if a particular direction would be correct for a room.
     * @param direction  input direction.
     * @param room input for which the direction is to be checked.
     * @return returns a boolean values depending if the direction exists for the given room.
`     * @throws Exception
     */
    public static boolean ifCheckInputOk  (String direction, String room) throws Exception{
        String Lcase = direction.toLowerCase();
        for (int i = 0; i < Mainclass.toJsonParsFile().getRooms().length; i++) {
            if (room.equals(Mainclass.toJsonParsFile().getRooms()[i].name)) {
                for (int j = 0; j < Mainclass.toJsonParsFile().getRooms()[i].directions.length; j++){
                    if (Lcase.contains(Mainclass.toJsonParsFile().getRooms()[i].directions[j].directionName.toLowerCase())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     *
     * @param followUp
     * @param room
     * @return
     * @throws Exception
     */
    public static String ToFollowupthedirectionfromtheRoom(String followUp, String room) throws Exception {
        for (int i = 0; i < Mainclass.toJsonParsFile().getRooms().length; i++) {
            if (room.equals(Mainclass.toJsonParsFile().getRooms()[i].name)) {
                for (int j = 0; j < Mainclass.toJsonParsFile().getRooms()[i].directions.length; j++){
                    if (followUp.contains(" " + Mainclass.toJsonParsFile().getRooms()[i].directions[j].directionName.toLowerCase())) {
                        return Mainclass.toJsonParsFile().getRooms()[i].directions[j].room;
                    }
                }
            }
        }
        return null;
    }
}
