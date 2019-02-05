package com.exaple;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class Helperfunctions {
    static String txt = data.getFileContentsAsString("roompath.json");
    static Gson gson = new Gson();

    /**
     *
     * @param roomname
     * @return
     */
    public static String getRoomdescription (String roomname) {
        String ReturnRoomDesc = "";
        for (int i = 0; i < gson.fromJson(txt, Layout.class).getRooms().length; i++) {
            if (roomname.equals(gson.fromJson(txt, Layout.class).getRooms()[i].name)) {
                ReturnRoomDesc = gson.fromJson(txt, Layout.class).getRooms()[i].description;
                return ReturnRoomDesc;
            }

        }
        return null;


    }

    /**
     *
     * @param room
     * @return
     */
    public static ArrayList<String> GetFowardDirections(String room) {
        //String AllPossibleDirections = "";
        for (int i = 0; i < gson.fromJson(txt, Layout.class).getRooms().length; i++) {
            if (room.equals(gson.fromJson(txt, Layout.class).getRooms()[i].name)) {
                //room = gson.fromJson(txt, Layout.class).getRooms()[i].name;
                ArrayList DirectionList = new ArrayList();
                for (int j = 0; j < gson.fromJson(txt, Layout.class).getRooms()[i].directions.length; j++) {
                    DirectionList.add(gson.fromJson(txt, Layout.class).getRooms()[i].directions[j].directionName);
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
     */
    public static boolean ToCheckIfdirectionOk (String directionType, String room) {
        String LcaseDirec = directionType.toLowerCase();
        for (int i = 0; i < gson.fromJson(txt, Layout.class).getRooms().length; i++) {
            if (room.equals(gson.fromJson(txt, Layout.class).getRooms()[i].name)) {
                //room = gson.fromJson(txt, Layout.class).getRooms()[i].name;
                ArrayList DirectionList = new ArrayList();
                for (int j = 0; j < gson.fromJson(txt, Layout.class).getRooms()[i].directions.length; j++){
                    if (gson.fromJson(txt, Layout.class).getRooms()[i].directions[j].directionName.toLowerCase().equals(LcaseDirec)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public static boolean ToCheckInputOk(String Userinput, String room) {
        String Lcase = Userinput.toLowerCase();
        for (int i = 0; i < gson.fromJson(txt, Layout.class).getRooms().length; i++) {
            if (room.equals(gson.fromJson(txt, Layout.class).getRooms()[i].name)) {
                //room = gson.fromJson(txt, Layout.class).getRooms()[i].name;
                //ArrayList DirectionList = new ArrayList();
                for (int j = 0; j < gson.fromJson(txt, Layout.class).getRooms()[i].directions.length; j++){
                    if (Lcase.contains(" " + gson.fromJson(txt, Layout.class).getRooms()[i].directions[j].directionName.toLowerCase())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static String ToFollowupthedirectionfromtheRoom(String q, String room) {
        for (int i = 0; i < gson.fromJson(txt, Layout.class).getRooms().length; i++) {
            if (room.equals(gson.fromJson(txt, Layout.class).getRooms()[i].name)) {
                //room = gson.fromJson(txt, Layout.class).getRooms()[i].name;

                for (int j = 0; j < gson.fromJson(txt, Layout.class).getRooms()[i].directions.length; j++){
                    if (q.contains(gson.fromJson(txt, Layout.class).getRooms()[i].directions[j].directionName.toLowerCase())) {
                        return gson.fromJson(txt, Layout.class).getRooms()[i].directions[j].room;
                    }
                }
            }
        }
        return null;

    }



}
