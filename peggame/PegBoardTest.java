package peggame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class PegBoardTest {
    
    @Test
    public void distanceFromHorizontalTest() {
        //setup
        int rows = 5;
        int cols = 4;
        PegBoard board = new PegBoard(rows, cols);

        Location shouldJumpTo = new Location(0, 2);
        Location shouldJumpFrom = new Location(0,0);
        Location shouldNotJumpTo = new Location(0, 3);
        Location shouldNotJumpFrom = new Location(0, 0);

        boolean shouldExpect = true; 
        boolean shouldNotExpect = false;
        //invoke
        boolean shouldActual = board.distanceFrom(shouldJumpTo, shouldJumpFrom);
        boolean shouldNotActual = board.distanceFrom(shouldNotJumpTo, shouldNotJumpFrom);
        //analyze
        assertEquals(shouldExpect, shouldActual);
        assertEquals(shouldNotExpect, shouldNotActual);
    }

    @Test
    public void distanceFromVerticalTest() {
        //setup
        int rows = 5;
        int cols = 4;
        PegBoard board = new PegBoard(rows, cols);

        Location shouldJumpTo = new Location(1, 3);
        Location shouldJumpFrom = new Location(1,1);
        Location shouldNotJumpTo = new Location(2, 3);
        Location shouldNotJumpFrom = new Location(2, 0);

        boolean shouldExpect = true; 
        boolean shouldNotExpect = false;
        //invoke
        boolean shouldActual = board.distanceFrom(shouldJumpTo, shouldJumpFrom);
        boolean shouldNotActual = board.distanceFrom(shouldNotJumpTo, shouldNotJumpFrom);
        //analyze
        assertEquals(shouldExpect, shouldActual);
        assertEquals(shouldNotExpect, shouldNotActual);
    }

    @Test
    public void distanceFromDiagonalTest() {
        //setup
        int rows = 5;
        int cols = 4;
        PegBoard board = new PegBoard(rows, cols);

        Location shouldJumpTo = new Location(2, 2);
        Location shouldJumpFrom = new Location(0,0);
        Location shouldNotJumpTo = new Location(3, 3);
        Location shouldNotJumpFrom = new Location(0, 0);

        boolean shouldExpect = true; 
        boolean shouldNotExpect = false;
        //invoke
        boolean shouldActual = board.distanceFrom(shouldJumpTo, shouldJumpFrom);
        boolean shouldNotActual = board.distanceFrom(shouldNotJumpTo, shouldNotJumpFrom);
        //analyze
        assertEquals(shouldExpect, shouldActual);
        assertEquals(shouldNotExpect, shouldNotActual);
    }
}
