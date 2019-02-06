package Test;

import com.exaple.Helperfunctions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperfunctionsTest {
    @Test
    public void testRoomdescription() throws Exception{
        assertEquals("You are on Matthews, outside the Siebel Center", Helperfunctions.getRoomdescription("MatthewsStreet") );
    }
    @Test
    public void GetFowardDirections() throws Exception {
        assertEquals("East", Helperfunctions.GetFowardDirections("MatthewsStreet").get(0));
    }
    @Test
    public void ToCheckInputOk()  throws  Exception {
        assertTrue(Helperfunctions.ifCheckInputOk("East", "SiebelEntry"));

    }
    @Test
    public void ToGetfowardDirection() throws Exception {
        assertEquals("SiebelEntry", Helperfunctions.ToFollowupthedirectionfromtheRoom("North" , "MatthewsStreet"));
    }



}