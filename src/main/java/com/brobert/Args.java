/**
 *
 */
package com.brobert;

import com.beust.jcommander.Parameter;
import com.brobert.Board.Token;

/**
 * @author brobert
 *
 */
public class Args {

	@Parameter(names = { "--huamn", "-h" }, required = false)
	private Token human = Token.X;

	@Parameter(names = { "-w", "--width" }, required = false)
	private int width = 3;



	public Token getHuman() {
		return human;
	}



	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}
}
