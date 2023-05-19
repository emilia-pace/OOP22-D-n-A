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

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Vector2d)) {
            return false;
        }
        final Vector2d vec = (Vector2d) obj;
        return (Double.compare(vec.x, this.x) == 0 && Double.compare(vec.y, this.y) == 0);
    }
}
