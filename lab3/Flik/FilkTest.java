import static org.junit.Assert.*;
import org.junit.Test;


public class FilkTest {
    @Test
    public void testIsSameNumber(){
        assertTrue(Flik.isSameNumber(1,1));
        assertTrue(Flik.isSameNumber(130,130));
        assertTrue(Flik.isSameNumber(500,500));
    }
}
