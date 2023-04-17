package it.unibo.dna.common;

public class Position2d implements java.io.Serializable {

    public double x, y;

    public Position2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position2d sum(Vector2d v) {
        return new Position2d(x + v.getX(), y + v.getY());
    }

    public Vector2d sub(Position2d v) {
        return new Vector2d(x - v.x, y - v.y);
    }

    public String toString() {
        return "Position2d(" + x + "," + y + ")";
    }

}
