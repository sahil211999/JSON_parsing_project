package com.exaple;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import java.net.URL;
import java.util.Scanner;

/**
 * This class contains the code to navigate through the game.
 */

public class Mainclass {

    public static String jSONpath = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";

    /**
     * Main method runs the algorithim which dictates the game.
     * @param args is the arguement to intake the URL.
     * @throws Exception if incorrect URL is input by the user.
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
        boolean ifGameEnd = false;
        String StartingRoom = toJsonParsFile().startingRoom;
        boolean ifInitialItera = true;
        String cuurentroom = StartingRoom;
        /**
         * While loop to continuesly prompt the user to give input.
         */
        while (ifGameEnd == false) {
            if (ifInitialItera) {
                System.out.print(Helperfunctions.getRoomdescription(StartingRoom) + ". Your journey begins here.");
                System.out.print(" From here you can go: " + Helperfunctions.GetFowardDirections(StartingRoom).get(0));
                ifInitialItera = false;
            }
            // block of code to take input from the user.
            Scanner Inputvalue = new Scanner(System.in);
            String l = Inputvalue.nextLine();// to take input from the following line
            String original = Inputvalue.nextLine();
            String lCaseInput = original.toLowerCase();



            if (lCaseInput.equals("exit") || lCaseInput.equals("quit") ) {
                System.exit(0);
            }

            if (lCaseInput.contains("go")) {
                if (Helperfunctions.ifCheckInputOk(lCaseInput, cuurentroom)) {
                    System.out.print(Helperfunctions.ToFollowupthedirectionfromtheRoom(lCaseInput, cuurentroom));
                    cuurentroom = (Helperfunctions.ToFollowupthedirectionfromtheRoom(lCaseInput, cuurentroom));


                    if (cuurentroom.equals(Mainclass.toJsonParsFile().endingRoom)) {
                        System.out.print(" You have reached your final destination");
                        System.exit(0);
                    }

                    System.out.print(" " + Helperfunctions.getRoomdescription(cuurentroom));
                    System.out.print("From here you can go: ");

                    for (int i = 0; i < toJsonParsFile().getRooms().length; i++) {
                        if (toJsonParsFile().getRooms()[i].name.equals(cuurentroom)) {
                            for (int j = 0; j < toJsonParsFile().getRooms()[i].directions.length; j++) {
                                System.out.print(" " + toJsonParsFile().getRooms()[i].directions[j].directionName);
                            }
                        }

                    }
                    continue;
                } else {
                    String direction = lCaseInput.substring(3);
                    System.out.print("I can't go " + direction);
                    continue;
                }
            }
            else {
                System.out.print("I can't understand " + lCaseInput);
                continue;
            }
        }
    }
    /**
     *This function parses the JSON file
     * @return it returns a Layout object.
     * @throws Exception if the URL given by the user is incorrect.
     */
    public static Layout toJsonParsFile() throws Exception {
        try {
            new URL(jSONpath).toURI();
            HttpResponse<JsonNode> JSON = Unirest.get(jSONpath).asJson();
            String txt =JSON.getBody().toString();
            Gson GsonObject = new Gson();
            return GsonObject.fromJson(txt, Layout.class);

        } catch (Exception e) {
            //nothing
        }
        return null;

    }
}

