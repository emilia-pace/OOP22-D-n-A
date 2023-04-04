package it.unibo.dna.common;

public class Vector2d implements java.io.Serializable {

    private double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Vector2d sumX(double d) {
        return new Vector2d(this.x + d, this.y);
    }

    public Vector2d sumY(double d) {
        return new Vector2d(this.x, this.y + d);
    }

    public Vector2d sum(Vector2d v) {
        return new Vector2d(x + v.x, y + v.y);
    }

    public double module() {
        return (double) Math.sqrt(x * x + y * y);
    }

    public Vector2d mul(double fact) {
        return new Vector2d(x * fact, y * fact);
    }

    public String toString() {
        return "Vector2d(" + x + "," + y + ")";
    }
}
