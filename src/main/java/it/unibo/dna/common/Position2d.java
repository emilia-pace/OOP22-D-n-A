package it.unibo.dna.common;

public class Position2d implements java.io.Serializable {

    public double x, y;

    public Position2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position2d sum(Vector2d v) {
        return new Position2d(x + v.x, y + v.y);
    }

    public Vector2d sub(Position2d v) {
        return new Vector2d(x - v.x, y - v.y);
    }

    public String toString() {
        return "Position2d(" + x + "," + y + ")";
    }

    
    @Override
    public boolean equals(final Object obj) {

        if (!(obj instanceof Position2d)) {
            return false;
        }
        final Position2d pos = (Position2d) obj;
        return (Double.compare(pos.x, this.x) == 0 && Double.compare(pos.y, this.y) == 0);
    }

}
