package Test;

import com.example.Gamedriver;
import com.example.Helperfunctions;
//import org.junit.jupiter.api.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Tests {


    @Test
    public void testRoomdescription() throws Exception{
        Gamedriver.toJsonParsFile();
        assertEquals("You are on Matthews, outside the Siebel Center",
                Helperfunctions.getRoomdescription("MatthewsStreet"));
        assertEquals("You are in the west entry of Siebel Center.  " +
                        "You can see the elevator, the ACM office, and hallways to the north and east.",
                Helperfunctions.getRoomdescription("SiebelEntry"));
        assertEquals("", Helperfunctions.getRoomdescription("skdmf"));
    }
    @Test
    public void testGetFowardDirections() throws Exception {
        Gamedriver.toJsonParsFile();
        assertEquals("East", Helperfunctions.GetFowardDirections("MatthewsStreet").get(0));
        assertEquals("West", Helperfunctions.GetFowardDirections("SiebelEntry").get(0));
        assertEquals("Northeast", (Helperfunctions.GetFowardDirections("SiebelEntry").get(1)));
        assertEquals(null, Helperfunctions.GetFowardDirections("MathewsStree"));
    }
    @Test
    public void testToCheckInputOk()  throws  Exception {
        Gamedriver.toJsonParsFile();
        assertTrue(!Helperfunctions.ifCheckInputOk("NJSDNJF", "MatthewsStreet"));

    }
    @Test
    public void testToGetfowardDirection() throws Exception {
        Gamedriver.toJsonParsFile();
        assertEquals("SiebelNorthHallway",
                Helperfunctions.toFollowupthedirectionfromtheRoom("go north", "SiebelEntry"));
    }
    @Test
    public void testgetRoomIndex() {
        Gamedriver.toJsonParsFile();
        assertEquals(1, Helperfunctions.getRoomIndex("Siebelentry"));
    }
    @Test
    public void testGetDirectionIndex() {
        Gamedriver.toJsonParsFile();
        assertEquals(0, Helperfunctions.getDirectionIndex("east", "mathewsstreet"));
    }
    @Test
    public void testTogetDirectionFromString() {
        Gamedriver.toJsonParsFile();
        assertEquals("east", Helperfunctions.togetThedirectionFromAstring("go east"));
    }
    @Test
    public void testTocheckPlayerItem() {
        Gamedriver.toJsonParsFile();
        assertFalse(Helperfunctions.toCheckifPlayerhasItem("Key to Siebel"));
    }
    @Test
    public void testTocheckIfDirecttionIsEnabled() {
        Gamedriver.toJsonParsFile();
        assertTrue(Helperfunctions.toCheckifDirectionisEnabled("east", "MathewssStreet"));
    }

}