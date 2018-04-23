/**
 *
 */
package com.brobert;

/**
 * @author brobert
 *
 */
public class Coordinate {

	int x, y;



	/**
	 * @param i
	 * @param j
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}



	@Override
	public String toString() {
		return x + "," + y;
	}



	public String huamnFriendlyString() {
		return (x + 1) + "," + (y + 1);
	}
}
