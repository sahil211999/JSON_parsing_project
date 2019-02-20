package com.example;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * This class contains the code to navigate through the game.
 */




public class Gamedriver {


    public static Layout returnLayout = new Layout();
    public static String jSONpath = "https://gist.githubusercontent.com/sahil211999/3a96aea52d524b9786a126d6e119ab7a/raw/86a98f64cd2f29f626dbfa75738d1a5e418509fa/JsonAdventure2";
    //Player[]

    public static List<Item> itemList = new ArrayList<>();
    public static String previous = ;
    /**
     * Main method runs the algorithim which dictates the game.
     * @param args is the arguement to intake the URL.
     * @throws Exception if incorrect URL is input by the user.
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            jSONpath = "https://gist.githubusercontent.com/sahil211999/3a96aea52d524b9786a126d6e119ab7a/raw/86a98f64cd2f29f626dbfa75738d1a5e418509fa/JsonAdventure2";
        } else {
            if (jSONpath.substring(-5).equals(".json")) {
                jSONpath = args[0];
            } else {
                System.out.print("Invalid URL");
            }
        }



        //Scanner numOfPlayers = new Scanner(System.in);
        //System.out.print("Enter number of players");
        toJsonParsFile();
        //System.out.print("" + previous);
        //System.out.print("" + returnLayout.startingRoom);
        gameDriver();

    }
    /**
     *This function parses the JSON file.
     * @return it returns a Layout object.
     * @throws Exception if the URL given by the user is incorrect.
     */
    public static void toJsonParsFile() throws Exception {
        try {
            new URL(jSONpath).toURI();
            HttpResponse<JsonNode> JSON = Unirest.get(jSONpath).asJson();
            String txt =JSON.getBody().toString();
            Gson GsonObject = new Gson();
            returnLayout = GsonObject.fromJson(txt, Layout.class);
            //return returnLayout;

        } catch (Exception e) {
            return;
            //nothing
        }
        //return null;
    }
    /**
     *
     *
     */
    public static void gameDriver() throws Exception {
        boolean ifGameEnd = false;
        String StartingRoom = returnLayout.startingRoom;
        boolean ifInitialItera = true;
        String cuurentroom = StartingRoom;
        while (ifGameEnd == false) {
            if (ifInitialItera) {
                System.out.print(Helperfunctions.getRoomdescription(StartingRoom) + ". Your journey begins here.");
                System.out.println(" From here you can go: " + Helperfunctions.GetFowardDirections(StartingRoom).get(0));
                ifInitialItera = false;
            }
            Scanner Inputvalue = new Scanner(System.in);
            String l = Inputvalue.nextLine();
            String original = Inputvalue.nextLine();
            String lCaseInput = original.toLowerCase();

            // to take input from the following line.
            if (checkForItems(previous) == 1) {
                checkForItems(previous);
                System.out.print(" " + previous );

                System.out.println("Do you want to add keep all the items." + " answer yes or no.");
                Scanner Inputvalue2 = new Scanner(System.in);
                String q = Inputvalue2.nextLine();

                if (q.toLowerCase().equals("yes")) {
                    addItemsToPlayer(previous);
                    System.out.print("The items were added");
                }
            }



            if (lCaseInput.equals("exit") || lCaseInput.equals("quit") ) {
                System.exit(0);
            }

            if (lCaseInput.contains("go")) {
                if (Helperfunctions.ifCheckInputOk(lCaseInput, cuurentroom)) {
                    previous = cuurentroom;

                    cuurentroom = (Helperfunctions.toFollowupthedirectionfromtheRoom(lCaseInput, cuurentroom));
                    if (cuurentroom.equals(Gamedriver.returnLayout.endingRoom)) {
                        System.out.print(" You have reached your final destination");
                        System.exit(0);
                    }
                    System.out.print(" " + Helperfunctions.getRoomdescription(cuurentroom));
                    System.out.println("From here you can go: ");
                    printDirectionsFromARoom(cuurentroom);
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
    public static void printDirectionsFromARoom(String cuurentroom) {
        for (int i = 0; i < returnLayout.getRooms().length; i++) {
            if (returnLayout.getRooms()[i].name.equals(cuurentroom)) {
                for (int j = 0; j < returnLayout.getRooms()[i].directions.length; j++) {
                    if (j == 0) {
                        System.out.print(" " + returnLayout.getRooms()[i].directions[j].directionName);
                    }
                    if (j > 0 && j < returnLayout.getRooms()[i].directions.length - 1)
                    System.out.print(", " +  returnLayout.getRooms()[i].directions[j].directionName );

                    if (j == returnLayout.getRooms()[i].directions.length - 1) {
                        System.out.println(", " + returnLayout.getRooms()[i].directions[j].directionName + ". " );


                    }

                }
            }
        }

    }
    public static int checkForItems(String cureentroom) {
        String lCaseCurrentRoom = cureentroom.toLowerCase();
        for (int i = 0; i < returnLayout.rooms.length; i++) {
            if (returnLayout.rooms[i].name.equals(lCaseCurrentRoom)) {
                if (returnLayout.rooms[i].items.length == 0) {
                    return 0;
                }
            }
            else {
                return 1;
            }

        }
        return 0;
    }
    public static void ListAllitem(String currentRoom) {
        String lCaseCurrentRoom = currentRoom.toLowerCase();
        for (int i = 0; i < returnLayout.rooms.length; i++) {
            if (returnLayout.rooms[i].name.equals(lCaseCurrentRoom)) {
                for (Item items: returnLayout.rooms[i].items) {
                    System.out.println("This room has the following: " + items.Name);
                }
            }

        }
    }

    public static void addItemsToPlayer(String currentRoom) {
        //String lCaseItem = itemToBeAdded.toLowerCase();
        for (int i = 0; i < returnLayout.rooms.length; i++) {
            if (returnLayout.rooms[i].name.equals(currentRoom)) {
                for (int j = 0; j < returnLayout.rooms[i].items.length; j++) {
                    returnLayout.player.arrayListofItem.add(returnLayout.rooms[i].items[j]);
                }

            }
        }
    }


    public static void toCheckifPlayerCanEnable(String direction, String currentroom) {

        for (int i = 0; i < returnLayout.rooms.length; i++) {
            if (returnLayout.rooms[i].equals(currentroom)) {
                for (int j = 0; j < returnLayout.rooms[j].directions.length; j++) {
                    for (int l = 0; l < returnLayout.rooms[l].directions[j].validKeyNames.length; i++) {
                        for (int q = 0; q < returnLayout.player.arrayListofItem.size(); q++) {
                            if (returnLayout.player.arrayListofItem.get(q).Name.equals(returnLayout.rooms[l].directions[j].validKeyNames[l])) {
                                returnLayout.rooms[l].directions[j].enabled = "true";
                            }
                        }
                    }
                }
            }
        }
    }

    //public static


}



