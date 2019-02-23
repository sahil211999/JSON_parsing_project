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
    public static String jSONpath = "https://gist.githubusercontent.com/sahil211999/681eee1380ddb43135be1ec6eee1655b/raw/41c1eee44269556d0443e4b7b53fbdd334214ddb/Adventure2";
    //Player[]

    public static List<Item> itemList = new ArrayList<>();
    public static String previous;

    /**
     * Main method runs the algorithim which dictates the game.
     *@param args is the arguement to intake the URL.
     * @throws Exception if incorrect URL is input by the user.
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            jSONpath = "https://gist.githubusercontent.com/sahil211999/681eee1380ddb43135be1ec6eee1655b/raw/41c1eee44269556d0443e4b7b53fbdd334214ddb/Adventure2";
        } else {
            if (jSONpath.substring(-5).equals(".json")) {
                jSONpath = args[0];
            } else {
                System.out.print("Invalid URL");
            }
        }
        toJsonParsFile();
        gameDriver();

    }

    /**
     * This function parses the JSON file.
     *@return it returns a Layout object.
     * @throws Exception if the URL given by the user is incorrect.
     */
    public static void toJsonParsFile() throws Exception {
        try {
            new URL(jSONpath).toURI();
            HttpResponse<JsonNode> JSON = Unirest.get(jSONpath).asJson();
            String txt = JSON.getBody().toString();
            Gson GsonObject = new Gson();
            returnLayout = GsonObject.fromJson(txt, Layout.class);
            previous = returnLayout.startingRoom;
            //return returnLayout;

        } catch (Exception e) {
            return;

        }

    }

    /**
     * This is the main function which dictates the game.
     */
    public static void gameDriver() throws Exception {
        System.out.print("hello");
        boolean ifGameEnd = false;
        String StartingRoom = returnLayout.startingRoom;
        boolean ifInitialItera = true;
        String cuurentroom = StartingRoom;
        int counter;
        while (ifGameEnd == false) {


            if (ifInitialItera) {
                System.out.print(Helperfunctions.getRoomdescription(StartingRoom) + ". Your journey begins here.");
                System.out.println(" From here you can go: " + Helperfunctions.GetFowardDirections(StartingRoom).get(0));
                ifInitialItera = false;
            }
            Scanner Inputvalue = new Scanner(System.in);
            String original = Inputvalue.nextLine();
            String lCaseInput = original.toLowerCase();
            if (lCaseInput.equals("exit") || lCaseInput.equals("quit")) {
                System.exit(0);
            }
            if (lCaseInput.contains("go")) {
                if (Helperfunctions.ifCheckInputOk(lCaseInput, cuurentroom)) {

                    if (Helperfunctions.toCheckifDirectionisEnabled(Helperfunctions.togetThedirectionFromAstring(lCaseInput), cuurentroom)) {
                        cuurentroom = (Helperfunctions.toFollowupthedirectionfromtheRoom(lCaseInput, cuurentroom));

                        if (cuurentroom.equals(Gamedriver.returnLayout.endingRoom)) {
                            System.out.print(" You have reached your final destination");
                            System.exit(0);
                        }

                        System.out.print(" " + Helperfunctions.getRoomdescription(cuurentroom));
                        printDirectionsFromARoom(cuurentroom);

                        ListAllitem(cuurentroom);

                        for (int i = 0; i < 2; i++) {
                            if (i == 0 & returnLayout.rooms[getCurrentRoomIndex(cuurentroom)].items.length > 0) {
                                System.out.println("Write the slot at which you need the item to be at (1 or 2). Any other input will be considered wrong");
                                Scanner inputFromuser = new Scanner(System.in);
                                int userInput = inputFromuser.nextInt();
                                System.out.print("Item at which place needs to be added ?");
                                Scanner inputFromUser2 = new Scanner(System.in);
                                System.out.print("" +cuurentroom);
                                System.out.print("" + Helperfunctions.getRoomIndex(cuurentroom));
                                int secondInput;
                                secondInput = inputFromUser2.nextInt();

                                returnLayout.player.items[userInput - 1].name = returnLayout.rooms[Helperfunctions.getRoomIndex(cuurentroom)].items[secondInput - 1].name;
                                System.out.print("You acquired " + returnLayout.player.items[userInput - 1].name);

                                continue;
                            }
                            if (returnLayout.rooms[getCurrentRoomIndex(cuurentroom)].items.length > 1) {
                                System.out.println("Do you want to add any other items? Enter yes or no");
                                Scanner input1 = new Scanner(System.in);
                                String instring = input1.nextLine();

                                if (instring.toLowerCase().equals("yes")) {
                                    System.out.println("Write the slot at which you need the item to be at (1 or 2). Any other input will be considered wrong");
                                    Scanner inputFromuser7 = new Scanner(System.in);
                                    int userInput5 = inputFromuser7.nextInt();

                                    System.out.print("Item at which place needs to be added ?");
                                    Scanner inputFromUser2 = new Scanner(System.in);
                                    System.out.print("" +cuurentroom);
                                    System.out.print("" + Helperfunctions.getRoomIndex(cuurentroom));
                                    int secondInput;
                                    secondInput = inputFromUser2.nextInt();

                                    returnLayout.player.items[userInput5 - 1].name = returnLayout.rooms[Helperfunctions.getRoomIndex(cuurentroom)].items[secondInput - 1].name;
                                    System.out.print("You acquired " + returnLayout.player.items[userInput5 - 1].name);

                                }

                            }

                        }

                        System.out.print(" " + Helperfunctions.getRoomdescription(cuurentroom));
                        System.out.println("From here you can go: ");
                        printDirectionsFromARoom(cuurentroom);
                        continue;

                    } else {
                        System.out.println("The direction is not enabled");
                        System.out.println("To enable the directtion use the key");
                        Scanner input4 = new Scanner(System.in);
                        String userinput = input4.nextLine();

                        if (userinput.toLowerCase().contains("use")) {
                            int i = userinput.toLowerCase().indexOf("use");
                            int j = userinput.toLowerCase().indexOf("with");
                            String tocheck = userinput.substring(i + 3, j);
                            String jk;
                            jk = tocheck.trim();
                            if (Helperfunctions.toCheckifPlayerhasItem(jk)) {
                                //System.out.println("The player had the key");
                                if (Helperfunctions.toCheckIfTheItem(jk)) {
                                    //System.out.println("The player had the key2");
                                    System.out.println("use" + tocheck + "with " + Helperfunctions.togetThedirectionFromAstring(lCaseInput));

                                    if (userinput.equals("use" + tocheck + "with " + Helperfunctions.togetThedirectionFromAstring(lCaseInput))) {
                                        System.out.println("executed");
                                        System.out.println("" + jk);
                                        System.out.println("" + lCaseInput);
                                        System.out.println("" + cuurentroom);
                                        Helperfunctions.toEnableDirection(Helperfunctions.togetThedirectionFromAstring(lCaseInput), cuurentroom, jk);
                                        continue;
                                    }
                                    System.out.println("The control moved out");
                                }
                            } else {
                                System.out.print("The player doesn't have" + tocheck);
                            }
                        }
                    }


                } else {
                    String direction = lCaseInput.substring(3);
                    System.out.println("I can't go " + direction);
                    continue;
                }
            } else {
                System.out.println("I can't understand " + lCaseInput);
                continue;
            }
        }
    }

    /**
     * The function prints the possible directions for a particular room.
     * @param cuurentroom the current room as the arguement.
     */
    public static void printDirectionsFromARoom(String cuurentroom) {
        if (cuurentroom.equals(returnLayout.startingRoom)) {
            System.out.println(returnLayout.rooms[0].directions[0].directionName + ".");
            return;
        }
        for (int i = 0; i < returnLayout.getRooms().length; i++) {
            if (returnLayout.getRooms()[i].name.equals(cuurentroom)) {
                for (int j = 0; j < returnLayout.getRooms()[i].directions.length; j++) {
                    if (j == 0) {
                        System.out.print(" " + returnLayout.getRooms()[i].directions[j].directionName);
                    }
                    if (j > 0 && j < returnLayout.getRooms()[i].directions.length - 1)
                        System.out.print(", " + returnLayout.getRooms()[i].directions[j].directionName);

                    if (j == returnLayout.getRooms()[i].directions.length - 1) {
                        System.out.println(", " + returnLayout.getRooms()[i].directions[j].directionName + ". ");


                    }

                }
            }
        }

    }

    /**
     * The function lists all the items a particular room has.
     * @param currentRoom
     */


    public static void ListAllitem(String currentRoom) {
        String lCaseCurrentRoom = currentRoom.toLowerCase();
        for (int i = 0; i < returnLayout.rooms.length; i++) {
            if (returnLayout.rooms[i].name.toLowerCase().equals(lCaseCurrentRoom)) {
                for (Item items : returnLayout.rooms[i].items) {
                    System.out.println(" This room has the following items: " + items.name + ":");
                }
            }
        }
    }

    /**
     * This function gives the index of the rooom
     * @param inputRoom takes input of the room for which index is needed.
     * @return returns the index of the room in the arguement.
     */

    public static  int getCurrentRoomIndex(String inputRoom) {
        for (int i = 0; i < returnLayout.rooms.length; i++) {
            if (returnLayout.rooms[i].name.toLowerCase().equals(inputRoom.toLowerCase())) {
                return i;

            }
        }
        return 0;

    }




}









