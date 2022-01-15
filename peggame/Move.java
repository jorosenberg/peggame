package peggame;

/**
 * This class represents a peg move, given two locations which are
 * expressed as "from" and "to"
 * 
 * @author Michael Ambrose
 * @author Graham Rogozinski
 * @author Jonah Rosenberg
 */
public class Move {
    private Location to;
    private Location from;

/**
 * Move Constructor
 * @param from Location
 * @param to Location
 */
    public Move(Location from, Location to) {
        this.to = to;
        this.from = from;
    }

    public Location getFrom() {return from;}
    public Location getTo() {return to;}

    @Override
    public String toString() {
        return "Move from: " + from + ", to: " + to;
    }

    @Override
    public int hashCode() {
        return to.getRow() * 31 + from.getRow() + from.getCol() * to.getCol();
    }

/**
 * Determines if two moves are the same
 */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Move) {
            Move other = (Move) o;
            return this.to == other.to && this.from == other.from;
        }
        return false;
    }

}
