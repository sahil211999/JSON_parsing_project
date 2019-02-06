package com.exaple;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;


import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */

public class Mainclass {
    /**
     *
     */
    public static String jSONpath;

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        if (args.length == 0) {
            jSONpath = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
        } else {
            if (jSONpath.substring(-5).equals(".json")) {
                jSONpath = args[0];
            } else {
                System.out.print("Invalid URL");
            }
        }


        boolean GameEnd = false;
        String StartingRoom = JsonParsedFile().startingRoom;
        boolean initial = true;
        String cuurentroom = StartingRoom;
        /**
         *
         */
        while (GameEnd == false) {
            if (initial) {
                System.out.print(Helperfunctions.getRoomdescription(StartingRoom) + ". Your journey begins here.");
                System.out.print(" From here you can go: " + Helperfunctions.GetFowardDirections(StartingRoom).get(0));
                initial = false;
            }

                Scanner Inputvalue = new Scanner(System.in);
            String l = Inputvalue.nextLine();
            String q = Inputvalue.nextLine();
            String LcaseInput = q.toLowerCase();


            if (LcaseInput.equals("exit") || LcaseInput.equals("quit") ) {
                System.exit(0);
            }

            if (LcaseInput.contains("go")) {
                if (Helperfunctions.ToCheckInputOk(LcaseInput, cuurentroom)) {
                    System.out.print(Helperfunctions.ToFollowupthedirectionfromtheRoom(LcaseInput, cuurentroom));
                    cuurentroom = (Helperfunctions.ToFollowupthedirectionfromtheRoom(LcaseInput, cuurentroom));


                    if (cuurentroom.equals(Mainclass.JsonParsedFile().endingRoom)) {
                        System.out.print(" You have reached your final destination");
                        System.exit(0);
                    }

                    System.out.print(" " + Helperfunctions.getRoomdescription(cuurentroom));
                    System.out.print("From here you can go: ");

                    for (int i = 0; i < JsonParsedFile().getRooms().length; i++) {
                        if (JsonParsedFile().getRooms()[i].name.equals(cuurentroom)) {
                            for (int j = 0; j < JsonParsedFile().getRooms()[i].directions.length; j++) {
                                System.out.print(" " + JsonParsedFile().getRooms()[i].directions[j].directionName);
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
    }
    /**
     *
     * @return
     * @throws Exception
     */
    public static Layout JsonParsedFile() throws Exception {
        HttpResponse<JsonNode> JSON = Unirest.get(jSONpath).asJson();
        String txt =JSON.getBody().toString();
        Gson GsonObject = new Gson();
        return GsonObject.fromJson(txt, Layout.class);
    }
}

