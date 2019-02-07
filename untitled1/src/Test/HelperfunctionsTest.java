package Test;

import com.exaple.Helperfunctions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperfunctionsTest {
    @Test
    public void testRoomdescription() throws Exception{
        assertEquals("You are on Matthews, outside the Siebel Center",
                Helperfunctions.getRoomdescription("MatthewsStreet"));
        assertEquals("You are in the west entry of Siebel Center.  " +
                        "You can see the elevator, the ACM office, and hallways to the north and east.",
                Helperfunctions.getRoomdescription("SiebelEntry"));
        assertEquals("", Helperfunctions.getRoomdescription("skdmf"));
    }
    @Test
    public void GetFowardDirections() throws Exception {
        assertEquals("East", Helperfunctions.GetFowardDirections("MatthewsStreet").get(0));
        assertEquals("West", Helperfunctions.GetFowardDirections("SiebelEntry").get(0));
        assertEquals("Northeast", (Helperfunctions.GetFowardDirections("SiebelEntry").get(1)));
        assertEquals(null, Helperfunctions.GetFowardDirections("MathewsStree"));
    }
    @Test
    public void ToCheckInputOk()  throws  Exception {
        assertTrue(Helperfunctions.ifCheckInputOk("east", "SiebelEntry"));
        assertTrue(Helperfunctions.ifCheckInputOk("west", "SiebelEntry"));
        assertTrue(!Helperfunctions.ifCheckInputOk("NJSDNJF", "MatthewsStreet"));

    }
    @Test
    public void ToGetfowardDirection() throws Exception {
        assertEquals("SiebelNorthHallway",
                Helperfunctions.toFollowupthedirectionfromtheRoom("go north", "SiebelEntry"));
    }
}