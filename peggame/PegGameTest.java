package peggame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class PegGameTest {
    @Test
    public void testDeepCopy() throws PegGameException{

        PegGame game1 = new PegBoard(3, 3);
        PegGame game2 = game1.deepCopy();

        Object[] moves = game2.getPossibleMoves().toArray();
        game2.makeMove((Move)moves[0]);
        assertNotEquals(game1.toString(), game2.toString());
    }

 }
