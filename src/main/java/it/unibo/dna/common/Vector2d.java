package it.unibo.dna.common;

public class Vector2d implements java.io.Serializable {

    public double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void sumX(double d) {
        x += d;
    }

    public void sumY(double d) {
        y += d;
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
