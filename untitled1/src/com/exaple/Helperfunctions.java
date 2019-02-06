package com.exaple;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class Helperfunctions {

    /**
     *
     * @param roomname
     * @return
     */
    public static String getRoomdescription (String roomname) throws Exception {
        String ReturnRoomDesc = "";
        for (int i = 0; i < Mainclass.JsonParsedFile().getRooms().length; i++) {
            if (roomname.equals(Mainclass.JsonParsedFile().getRooms()[i].name)) {
                ReturnRoomDesc = Mainclass.JsonParsedFile().getRooms()[i].description;
                return ReturnRoomDesc;
            }

        }
        return "";


    }

    /**
     *
     * @param room
     * @return
     */
    public static ArrayList<String> GetFowardDirections(String room) throws Exception {
        //String AllPossibleDirections = "";
        for (int i = 0; i < Mainclass.JsonParsedFile().getRooms().length; i++) {
            if (room.equals(Mainclass.JsonParsedFile().getRooms()[i].name)) {
                //room = gson.fromJson(txt, Layout.class).getRooms()[i].name;
                ArrayList DirectionList = new ArrayList();
                for (int j = 0; j < Mainclass.JsonParsedFile().getRooms()[i].directions.length; j++) {
                    DirectionList.add(Mainclass.JsonParsedFile().getRooms()[i].directions[j].directionName);
                }
                return DirectionList;
            }


        }
        return null;
    }

    /**
     *
     * @param directionType
     * @param room
     * @return
     *
    public static boolean ToCheckIfdirectionOk (String directionType, String room) throws Exception  {
        String LcaseDirec = directionType.toLowerCase();
        for (int i = 0; i < Mainclass.JsonParsedFile().getRooms().length; i++) {
            if (room.equals(Mainclass.JsonParsedFile().getRooms()[i].name)) {
                ArrayList DirectionList = new ArrayList();
                for (int j = 0; j < Mainclass.JsonParsedFile().getRooms()[i].directions.length; j++){
                    if (Mainclass.JsonParsedFile().getRooms()[i].directions[j].directionName.toLowerCase().equals(LcaseDirec)) {
                        return true;
                    }
                }
            }
        }
        return false;

    */

    /**
     *
     * @param Userinput
     * @param room
     * @return
     * @throws Exception
     */

    public static boolean ToCheckInputOk  (String Userinput, String room) throws Exception{
        String Lcase = Userinput.toLowerCase();
        for (int i = 0; i < Mainclass.JsonParsedFile().getRooms().length; i++) {
            if (room.equals(Mainclass.JsonParsedFile().getRooms()[i].name)) {
                for (int j = 0; j < Mainclass.JsonParsedFile().getRooms()[i].directions.length; j++){
                    if (Lcase.contains(Mainclass.JsonParsedFile().getRooms()[i].directions[j].directionName.toLowerCase())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param q
     * @param room
     * @return
     * @throws Exception
     */
    public static String ToFollowupthedirectionfromtheRoom(String q, String room) throws Exception{
        for (int i = 0; i < Mainclass.JsonParsedFile().getRooms().length; i++) {
            if (room.equals(Mainclass.JsonParsedFile().getRooms()[i].name)) {
                for (int j = 0; j < Mainclass.JsonParsedFile().getRooms()[i].directions.length; j++){
                    if (q.contains(" " + Mainclass.JsonParsedFile().getRooms()[i].directions[j].directionName.toLowerCase())) {
                        return Mainclass.JsonParsedFile().getRooms()[i].directions[j].room;
                    }
                }
            }
        }
        return null;

    }



}
