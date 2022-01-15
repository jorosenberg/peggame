package peggame;

/**
 * This class creates a Location given a row and column
 * 
 * @author Michael Ambrose
 * @author Graham Rogozinski
 * @author Jonah Rosenberg
 */
public class Location {
    private int row;
    private int col;

/**
 * Location Constructor
 * 
 * @param row
 * @param col
 */
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {return row;}
    public int getCol() {return col;}

    @Override
    public int hashCode() {
        return row * 31 + col;
    }
/**
 * Determines if two Locations are the same by using their rows and columns
 * 
 * @param o Object 
 */
    @Override
    public boolean equals(Object o) {
        if(o instanceof Location) {
            Location other = (Location) o;
            return (this.row == other.row) && (this.col == other.col);
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")"; 
    }
}