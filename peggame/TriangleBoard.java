package peggame;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Random;

public class TriangleBoard implements PegGame{
    private int rows;
    private int cols;
    private LinkedHashMap<Location, String> board;
    private int moveCounter = 0;

    public TriangleBoard(int rows) {
        this.rows = rows;
        this.cols = rows;
        board = new LinkedHashMap<>();
        createBoard();
    }

    public TriangleBoard(int rows, LinkedHashMap<Location, String> board) {
        this.board = board;
        this.rows = rows;
        this.cols = rows;
    }

    private void createBoard() {
        int pegCount = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < pegCount; j++) {
                Location location = new Location(i, j);
                board.put(location, "o");
            }
            pegCount++;
        }

        Random empty = new Random();
        //empty.setSeed(1);
        
        int row = empty.nextInt(rows);

        Location newLocation = new Location(row, empty.nextInt(row + 1));

        board.replace(newLocation, "-");
    }

    @Override
    public PegGame deepCopy() {
        int rows = this.rows;
        LinkedHashMap<Location, String> newBoard = new LinkedHashMap<>();

        for (Location location : this.board.keySet()) {
            newBoard.put(location, board.get(location));
        }
        return new TriangleBoard(rows, newBoard);
    }



    @Override
    public Collection<Move> getPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }
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
            for (String peg : board.values()) {
                if (peg.equals("o"))
                    pegs++;
            }
            if (pegs > 1)
                return GameState.STALEMATE;
            return GameState.WON;
        }
        return null;
    }
    @Override
    public void makeMove(Move move) throws PegGameException {
        // TODO Auto-generated method stub
        
    }

    public boolean distanceFrom(Location to, Location from) {
        int rowDiff = (to.getRow() - from.getRow());
        int colDiff = (to.getCol() - from.getCol());

        //                  Vertical(not needed) && Horizontal         or                     Diagonal
        return Math.sqrt(rowDiff * rowDiff + colDiff * colDiff) == 2.0 ||  Math.sqrt(rowDiff * rowDiff + colDiff * colDiff) == Math.sqrt(8);
    }

    public boolean isValidMove(Move move) {
        Location to = move.getTo();
        Location from = move.getFrom();
        int middleRow = (from.getRow() + to.getRow()) / 2;
        int middleCol = (from.getCol() + to.getCol()) / 2;

        Location middleSpace = new Location(middleRow, middleCol);

        // Validates the move is within the constraints of the board
        if(to.getCol() >= to.getRow() || to.getRow() >= rows || from.getCol() >= from.getRow() || from.getRow() >= rows ||
        to.getCol() < 0 || to.getRow() < 0 || from.getCol() < 0 || from.getRow() < 0) {
            return false;
        }

        // Check if the space where the peg jumps to is occupied or if there is a peg in the "from" location
        if(board.get(to).equals("o") || board.get(from).equals("-")) {
            return false;
        }

        // Validates that the space the peg jumps over is not empty
        if(board.get(middleSpace).equals("-")) {
            return false;
        }

        if(distanceFrom(to, from) == false) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String rStr = "";
        int counter = 1;
        int otherCounter = 1;

        for (Location location : board.keySet()){
            if (otherCounter == 1) {
                for (int i = 0; i < (rows - counter); i++) {
                    rStr += " ";
                }
            }

            rStr += board.get(location) + " ";

            if (otherCounter == counter) {
                rStr += "\n";
                counter++;
                otherCounter = 1;
            }
            else {
                otherCounter++;
            }
        }
        return rStr;
    }

    public static void main(String[] args) {
        TriangleBoard board = new TriangleBoard(4);

        System.out.println(board);
    }
}
