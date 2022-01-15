package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backtracker.*;

public class GameConfig implements Configuration{
    private PegGame game;
    protected List<Move> previousMoves;

    public GameConfig(PegGame game) {
        this(game, new ArrayList<>());
    }

    /**
     * overloaded constructor used for creating successors
     * @param game
     * @param previousMoves
     */
    private GameConfig(PegGame game, List<Move> previousMoves) {
        this.game = game;
        this.previousMoves = previousMoves;
    }

    /**
     * gets the list of successors from the current board configuration
     */
    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();

        Collection<Move> nextMove = game.getPossibleMoves();

        for (Move move : nextMove) {
            PegGame copy = game.deepCopy();
            List<Move> newMoves = new ArrayList<>();
            for (Move previousMove : previousMoves) {
                newMoves.add(previousMove);
            }
            try {
            copy.makeMove(move);
            newMoves.add(move);
            successors.add(new GameConfig(copy,newMoves));
            }
            catch (PegGameException pge) {
                System.out.println("Invalid move: " + move + ", not adding to successors");
            }
        }

        return successors;
    }

    /**
     * returns true if the game is the goal config or in progress
     */
    @Override
    public boolean isValid() {
        if (game.getPossibleMoves().size() > 0 || isGoal())
            return true;
        return false;
    }

    /**
     * returns true if the game's state is won
     */
    @Override
    public boolean isGoal() {
        if (game.getGameState() == GameState.WON)
            return true;
        return false;
    }
    
    /**
     * neat output for a game config
     */
    @Override
    public String toString() {
        return previousMoves.toString() + "\n" + game.toString();
    }

    /**
     * uses backtracker to solve a given PegGame
     * @param game
     * @return the winning configuration
     */
    public static GameConfig solveGame(PegGame game) {
        Backtracker backtracker = new Backtracker(false);
        Configuration solved = backtracker.solve(new GameConfig(game));
        GameConfig sol = (GameConfig)solved;
        return sol;
    }
    
}
