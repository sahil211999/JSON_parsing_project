package Test;

import com.exaple.Helperfunctions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperfunctionsTest {
    @Test
    public void testRoomdescription() throws Exception {
        try {
            assertEquals("You are on Matthews, outsidedfvknkf Siebel Center", Helperfunctions.getRoomdescription("MathewsStreet"));
        } catch (NullPointerException e) {

        }
    }
    @Test
    public void GetFowardDirections() throws Exception {
        try {
            assertEquals("st", Helperfunctions.GetFowardDirections("MathewsStreet").get(0));
        } catch (NullPointerException e) {

        }
    }
    @Test
    public void ToCheckInputOk() throws Exception {
        try {
            assertEquals(true, Helperfunctions.ToCheckInputOk("MathewsStreet", "East"));
        } catch (NullPointerException e) {

        }
    }
    //@Test
    //public void ToFollowupthedirectionfromtheroom() {

    //}

}