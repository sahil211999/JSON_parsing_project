package com.example;
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
        for (int i = 0; i < Gamedriver.returnLayout.getRooms().length; i++) {
            if (roomname.equals(Gamedriver.returnLayout.getRooms()[i].name)) {
                ReturnRoomDesc = Gamedriver.returnLayout.getRooms()[i].description;
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
        for (int i = 0; i < Gamedriver.returnLayout.getRooms().length; i++) {
            if (room.equals(Gamedriver.returnLayout.getRooms()[i].name)) {
                ArrayList DirectionList = new ArrayList();
                for (int j = 0; j < Gamedriver.returnLayout.getRooms()[i].directions.length; j++) {
                    DirectionList.add(Gamedriver.returnLayout.getRooms()[i].directions[j].directionName);
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
        for (int i = 0; i < Gamedriver.returnLayout.getRooms().length; i++) {
            if (room.equals(Gamedriver.returnLayout.getRooms()[i].name)) {
                for (int j = 0; j < Gamedriver.returnLayout.getRooms()[i].directions.length; j++){
                    if (Helperfunctions.togetThedirectionFromAstring(Lcase).toLowerCase().equals(Gamedriver.returnLayout.getRooms()[i].directions[j].directionName.toLowerCase())) {

                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * Gives a reference to the next room. From a given room.
     * @param followUp The direction to which the player has chosen to move.
     * @param room The current room the player is in.
     * @return The next room the player enters.
     * @throws Exception
     */
    public static String toFollowupthedirectionfromtheRoom(String followUp, String room) throws Exception {
        for (int i = 0; i < Gamedriver.returnLayout.getRooms().length; i++) {
            if (room.equals(Gamedriver.returnLayout.getRooms()[i].name)) {
                for (int j = 0; j < Gamedriver.returnLayout.getRooms()[i].directions.length; j++){
                    if (followUp.contains(" "
                            + Gamedriver.returnLayout.getRooms()[i].directions[j].directionName.toLowerCase())) {
                        return Gamedriver.returnLayout.getRooms()[i].directions[j].room;
                    }
                }
            }
        }
        return null;
    }
    /**
     * Checks if the direction is enabled
     * @param direction The direction requested.
     * @param rooms The current room in which the player is.
     * @return The boolean value for if the direction is enabled
     */

    public static boolean  toCheckifDirectionisEnabled(String direction, String rooms) {
        if ( Gamedriver.returnLayout.rooms[getRoomIndex(rooms)].directions[getDirectionIndex(direction, rooms)].enabled.equals("true ") ) {
            return true;
        }
        return false;
    }

    /**
     * The function returns direction from the command input from the user.
     * @param userInput the arguement accepted from the user from which the direction is extracted.
     * @return returns the direction
     */
    public static String togetThedirectionFromAstring(String userInput) {
        int l = userInput.toLowerCase().indexOf("go");
        String returnStr = userInput.substring(l+2);
         String ls;
        ls = returnStr.trim();
        return ls.toLowerCase();

    }

    /**
     * The function checks if the player has a particular item.
     * @param s the item for which the check has to be made.
     * @return the boolean value depending on the result.
     */

    public static boolean toCheckIfTheItem(String s) {
        for (int i = 0; i < Gamedriver.returnLayout.player.items.length; i++) {
            if (s.toLowerCase().equals(Gamedriver.returnLayout.player.items[i].name.toLowerCase())) {
                return true;
            }
        }
        return false;

    }

    /**
     * The function checks if a particular player has the item.
     * @param ls to check the irem for which the check has to be made.
     * @return the boolean value depending if the player has the item.
     */

    public static boolean toCheckifPlayerhasItem(String ls) {
        for (int i = 0; i < Gamedriver.returnLayout.player.items.length; i++) {
            if (Gamedriver.returnLayout.player.items[i].name.toLowerCase().equals(ls.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * The function returns the index of the room when passed as an arguement.
     * @param currentroom the room for which the index is required.
     * @return the index of the room
     */

    public static int getRoomIndex(String currentroom) {
        for (int j = 0; j < Gamedriver.returnLayout.rooms.length; j++) {
            if (Gamedriver.returnLayout.rooms[j].name.toLowerCase().equals(currentroom.toLowerCase())) {
                return j;
            }
        }
        return 0;
    }

    /**
     * The function helps enabling the direction for which the user has the item for
     * @param directions the direction in which the user wants to go
     * @param currentroom the current room in which the user is present.
     * @param key checks if the user has that particular key.
     */

    public static void toEnableDirection(String directions, String currentroom, String key) {
        for (int i = 0; i < Gamedriver.returnLayout.rooms[getRoomIndex(currentroom)].directions[getDirectionIndex(directions, currentroom)].validKeyNames.length; i++) {
            if (key.toLowerCase().equals(Gamedriver.returnLayout.rooms[getRoomIndex(currentroom)].directions[getDirectionIndex(directions, currentroom)].validKeyNames[i].toLowerCase())) {
                System.out.print("The direction" + directions + " enabled");
                Gamedriver.returnLayout.rooms[getRoomIndex(currentroom)].directions[getDirectionIndex(directions, currentroom)].enabled = "true ";
            }
        }

    }

    /**
     * This function returns the index of a particular direction in a particular room
     * @param direction The direction for which the index is to be found.
     * @param currentroom The room in which the player is present.
     * @return the index at which the direction is present in a particular room.
     */

    public static int getDirectionIndex(String direction, String currentroom) {
        for (int i = 0; i < Gamedriver.returnLayout.rooms[getRoomIndex(currentroom)].directions.length; i++) {
            if (direction.toLowerCase().equals(Gamedriver.returnLayout.rooms[getRoomIndex(currentroom)].directions[i].directionName.toLowerCase())) {
                return i;
            }


        }
        return 0;
    }
    public static void initializePlayerAttacksArray() {
        for (int i = 0; i < Gamedriver.returnLayout.player.Attacks.length; i++) {
            if (i == 0) {
                Gamedriver.returnLayout.player.Attacks[i] = "indenation thrower";
            }
            if (i == 1) {
                Gamedriver.returnLayout.player.Attacks[i] = "procrastinate";
            }
            if (i == 2) {
                Gamedriver.returnLayout.player.Attacks[i] = "sleep";
            }
            if (i == 3) {
                Gamedriver.returnLayout.player.Attacks[i] = "write good code";

            }


        }
    }

    public static void printArrayElements(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print( " " + array[i]);
        }

    }
}
