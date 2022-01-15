package peggame;

import java.util.Collection;

public interface PegGame {
    Collection<Move> getPossibleMoves();
    GameState getGameState();
    void makeMove(Move move) throws PegGameException;

    default PegGame deepCopy() {
        throw new UnsupportedOperationException("deepCopy not implemented");
    }
}
