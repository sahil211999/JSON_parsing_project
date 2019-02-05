package com.exaple;
import com.google.gson.Gson;

import javax.crypto.AEADBadTagException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mainclass {



    public static void main(String[] args) {
        String txt = data.getFileContentsAsString("roompath.json");
        Gson gson = new Gson();

        boolean GameEnd = false;
        String startingRoom = gson.fromJson(txt, Layout.class).startingRoom;
        boolean initial = true;
        String cuurentroom = startingRoom;

        while (GameEnd == false) {
            if (initial) {
                System.out.print(Helperfunctions.getRoomdescription(startingRoom) + ". Your journey begins here.");
                System.out.print(" From here you can go: " + Helperfunctions.GetFowardDirections(startingRoom).get(0));
                initial = false;
            }

                Scanner Inputvalue = new Scanner(System.in);
            String l = Inputvalue.nextLine();


            String q = Inputvalue.nextLine();
            String LcaseInput = q.toLowerCase();
            int indexofcurrentroom;
            if (LcaseInput.contains("go")) {
                if (Helperfunctions.ToCheckInputOk(LcaseInput, cuurentroom)) {
                    //cuurentroom = (Helperfunctions.ToFollowupthedirectionfromtheRoom(LcaseInput, cuurentroom));
                    System.out.print(Helperfunctions.ToFollowupthedirectionfromtheRoom(LcaseInput, cuurentroom));
                    cuurentroom = (Helperfunctions.ToFollowupthedirectionfromtheRoom(LcaseInput, cuurentroom));


                    if (cuurentroom.equals(gson.fromJson(txt, Layout.class).endingRoom)) {
                        System.out.print(" You have reached your final destination");
                        System.exit(0);
                    }

                    System.out.print(" " + Helperfunctions.getRoomdescription(cuurentroom));
                    System.out.print("From here you can go: ");

                    for (int i = 0; i < gson.fromJson(txt, Layout.class).getRooms().length; i++) {
                        if (gson.fromJson(txt, Layout.class).getRooms()[i].name.equals(cuurentroom)) {
                            for (int j = 0; j < gson.fromJson(txt, Layout.class).getRooms()[i].directions.length; j++) {
                                System.out.print(" " + gson.fromJson(txt, Layout.class).getRooms()[i].directions[j].directionName);
                            }
                        }

                    }
                    continue;
                } else {
                    String direction = LcaseInput.substring(3);
                    System.out.print("I can't go " + direction);
                    continue;
                }
            }
            else {
                System.out.print("I can't understand " + LcaseInput);
                continue;
            }





        }













        /**

        //int i = 1;
        Scanner Inputvalue = new Scanner(System.in);
        System.out.print(" "+ gson.fromJson(txt, Layout.class).getRooms()[0].description  +
                    "\n Your journey begins here." + " From here you can go:" + gson.fromJson(txt, Layout.class).getRooms()[0].directions[0].directionName);
                String q = "";
                       q = Inputvalue.next();
                       if (q.equals(gson.fromJson(txt, Layout.class).getRooms()[0].directions[0].directionName)) {

            OUTER:
            for (int i = 1; i < gson.fromJson(txt, Layout.class).getRooms().length; i++) {

                System.out.print("\n" + gson.fromJson(txt, Layout.class).getRooms()[i].description + " From here you can go:");
                //for (int p = 0; p < gson.fromJson(txt, Layout.class).getRooms()[i].directions.length; i++) {
                 //   System.out.print(" "+ gson.fromJson(txt, Layout.class).getRooms()[i].directions[p].directionName);
                //}
                Scanner Inputvalue2 = new Scanner(System.in);
                String Inputvalue3 = Inputvalue2.next();

                for (int l = 0; l < gson.fromJson(txt, Layout.class).getRooms()[i].directions.length; i++) {
                    if (Inputvalue3.equals(gson.fromJson(txt, Layout.class).getRooms()[i].directions[l].directionName)) {
                        System.out.print("l"+ gson.fromJson(txt, Layout.class).getRooms()[i].description+". From here you can go:");
                        //for (int p = 0; p < gson.fromJson(txt, Layout.class).getRooms()[i].directions.length; i++) {
                         //   System.out.print(" "+ gson.fromJson(txt, Layout.class).getRooms()[i].directions[p].directionName);
                        //}
                        if (Inputvalue3.equals(gson.fromJson(txt, Layout.class).getRooms()[i].directions[l].room.equals(q.equals(gson.fromJson(txt, Layout.class).getRooms()[i + 1].name)))){
                            break;
                        } else {
                            System.out.print("\n You have reached your final destination");
                            break OUTER;
                        }
                    } else {
                        System.out.print("You can't go this direction");
                    }
                }
            }

        } else {
                           System.out.print("You can't go this direction");
                       }


    }
         */
}
}

