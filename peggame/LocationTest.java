package peggame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class LocationTest {
    
    @Test
    public void locationTest() {
        //setup
        Location location = new Location(2,4);
        int expectedRow = 2;
        int expectedCol = 4;
        //invoke
        int actualRow = location.getRow();
        int actualCol = location.getCol();
        //analyze
        assertEquals(expectedRow, actualRow);
        assertEquals(expectedCol, actualCol);
    }
}
