package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class TestMove {
    @Test
    public void testCreateMove() {
        Location from = new Location(0, 0);
        Location to = new Location(0, 3);
        Move move = new Move(from,to);
        assertEquals(move.getFrom(), from);
        assertEquals(move.getTo(), to);
    }
}
