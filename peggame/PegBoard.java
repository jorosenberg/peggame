package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * This class instantiates a playable board where a move could be made, and with 
 * various methods check the validity of each move
 */
public class PegBoard implements PegGame{
    private int rows;
    private int cols;
    private Map<Location, String> Board;
    private int moveCounter = 0;

/**
 * PegBoard Constructor
 * 
 * @param rows int
 * @param cols int
 */
    public PegBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        Board = new LinkedHashMap<>();
        createBoard(); 
    }

    /**
     * overloaded constructor for reading boards from files
     * @param rows
     * @param cols
     * @param Board
     */
    public PegBoard(int rows, int cols, LinkedHashMap<Location, String> Board) {
        this.Board = Board;
        this.rows = rows;
        this.cols = cols;
    }

    private void createBoard() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                Location location = new Location(i, j);
                Board.put(location, "o");
            }
        }
        

        Random empty = new Random();
        //empty.setSeed(1);
        
        int row = empty.nextInt(rows);
        int col = empty.nextInt(cols);

        Location newLocation = new Location(row, col);

        Board.replace(newLocation, "-");
    }

    /**
     * creates and returns a deep copy of the PegBoard
     */
    @Override
    public PegGame deepCopy() {
        int rows = this.rows;
        int cols = this.cols;
        LinkedHashMap<Location, String> newBoard = new LinkedHashMap<>();

        for (Location location : this.Board.keySet()) {
            newBoard.put(location, Board.get(location));
        }
        return new PegBoard(rows, cols, newBoard);
    }

/**
 * Returns a Collection of possible moves the player can make
 * 
 * @return Collection<Move>
 */
    @Override
    public Collection<Move> getPossibleMoves() {
        List<Move> possibleMoves = new ArrayList<>();

        for(Location location : Board.keySet()) {
            if(Board.get(location).equals("o")) {
                for(Location newLocation : Board.keySet()) {
                    Move move = new Move(location, newLocation);

                    if(isValidMove(move) == true) {
                        possibleMoves.add(move);
                    }
                }
            }
        }
        return possibleMoves;
    }
/**
 * Returns the status of the game
 * 
 * @return GameState
 */
    @Override
    public GameState getGameState() {
        if(moveCounter == 0) {
            return GameState.NOT_STARTED;
        }
        else if(getPossibleMoves().size() > 0 && moveCounter > 0 && moveCounter < (rows * cols) - 3) {
            return GameState.IN_PROGRESS;
        }
        else if(getPossibleMoves().size() == 0) {
            int pegs = 0;
            for (String peg : Board.values()) {
                if (peg.equals("o"))
                    pegs++;
            }
            if (pegs > 1)
                return GameState.STALEMATE;
            return GameState.WON;
        }
        return null;

    }

/**
 * Extra layer of validity to check if a move jumps over one space only
 * 
 * @param to Location
 * @param from Location
 * @return boolean
 */
    public boolean distanceFrom(Location to, Location from) {
        int rowDiff = (to.getRow() - from.getRow());
        int colDiff = (to.getCol() - from.getCol());

        //                      Vertical && Horizontal                 or                     Diagonal
        return Math.sqrt(rowDiff * rowDiff + colDiff * colDiff) == 2.0 ||  Math.sqrt(rowDiff * rowDiff + colDiff * colDiff) == Math.sqrt(8);
    }

/**
 * Determines if a move is valid by comparing the Location of the move against the boundaries of the board and
 * the status of each space (occupied or empty)
 * 
 * @param move Move
 * @return boolean
 */
    public boolean isValidMove(Move move) {
        Location to = move.getTo();
        Location from = move.getFrom();
        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;

        Location middleSpace = new Location(middleRow, middleCol);

        // Validates the move is within the constraints of the board
        if(to.getCol() >= cols || to.getRow() >= rows || from.getCol() >= cols || from.getRow() >= rows ||
        to.getCol() < 0 || to.getRow() < 0 || from.getCol() < 0 || from.getRow() < 0) {
            return false;
        }

        // Check if the space where the peg jumps to is occupied or if there is a peg in the "from" location
        if(Board.get(to).equals("o") || Board.get(from).equals("-")) {
            return false;
        }

        // Validates that the space the peg jumps over is not empty
        if(Board.get(middleSpace).equals("-")) {
            return false;
        }

        if(distanceFrom(to, from) == false) {
            return false;
        }

        return true;
    }

    /**
     * Attemps to make a move from one space to another
     * throws an exception if the move is invalid
     */
    @Override
    public void makeMove(Move move) throws PegGameException {
        Location to = move.getTo();
        Location from = move.getFrom();

        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;
        
        Location middleSpace = new Location(middleRow, middleCol);

        if(isValidMove(move) == false) {
            throw new PegGameException("Invalid");
        }

        Board.replace(middleSpace, "-");
        Board.replace(from, "-");
        Board.replace(to, "o");

        moveCounter++;
    }

    @Override
    public String toString() {
        String rStr = "";
        for (Location location : Board.keySet()) {
            rStr += Board.get(location);
            if (location.getCol() == cols-1) {
                rStr += "\n";
            }
        }

        return rStr;
    }

}
