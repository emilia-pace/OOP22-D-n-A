package it.unibo.dna.common;

/*
 * A standard generic Pair<X,Y> saving couple of element 
 * @param <X> the type of the first element
 * @param <Y> the type of the second element
 */
public class Pair<X, Y> {

	private X x;
	private Y y;

	/**
	 * @param x the first element
	 * @param y the second element
	 */
	public Pair(final X x, final Y y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the first element
	 */
	public X getX() {
		return x;
	}

	/**
	 * @return the second element
	 */
	public Y getY() {
		return y;
	}

	/**
	 * @param x the first element
	 */
	public void setX(final X x) {
		this.x = x;
	}

	/**
	 * @param y the second element
	 */
	public void setY(final Y y) {
		this.y = y;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pair other = (Pair) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}

}
